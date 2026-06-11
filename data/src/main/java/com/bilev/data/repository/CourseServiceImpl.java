package com.bilev.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.bilev.data.api.CourseApi;
import com.bilev.data.dto.CourseDto;
import com.bilev.data.dto.CoursesResponse;
import com.bilev.data.local.dao.FavoriteCourseDao;
import com.bilev.data.local.entity.FavoriteCourseEntity;
import com.bilev.data.mapper.CourseMapper;
import com.bilev.domain.model.Course;
import com.bilev.domain.repository.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseServiceImpl implements CourseService {

    private final CourseApi courseApi;
    private final FavoriteCourseDao favoriteCourseDao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    private List<Integer> cachedFavoriteIds = new ArrayList<>();
    private List<CourseDto> cachedDtos = new ArrayList<>();

    private final MutableLiveData<List<Course>> coursesLiveData = new MutableLiveData<>();

    public CourseServiceImpl(CourseApi courseApi, FavoriteCourseDao favoriteCourseDao) {
        this.courseApi = courseApi;
        this.favoriteCourseDao = favoriteCourseDao;

        favoriteCourseDao.getAllFavorites().observeForever(entities -> {
            List<Integer> ids = new ArrayList<>();

            if (entities != null) {
                for (FavoriteCourseEntity e : entities) {
                    ids.add(e.getId());
                }
            }

            cachedFavoriteIds = ids;
            rebuildCourses();
        });
    }

    @Override
    public LiveData<List<Course>> getCourses() {
        courseApi.getCourses().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<CoursesResponse> call,
                                   @NonNull Response<CoursesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    cachedDtos = response.body().getCourses();
                    rebuildCourses();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CoursesResponse> call,
                                  @NonNull Throwable t) {
                coursesLiveData.postValue(new ArrayList<>());
            }
        });

        return coursesLiveData;
    }

    @Override
    public LiveData<List<Course>> getFavoriteCourses() {
        return Transformations.map(
                favoriteCourseDao.getAllFavorites(),
                CourseMapper::fromEntityList
        );
    }

    @Override
    public void addToFavorites(Course course) {
        executor.execute(() ->
                favoriteCourseDao.insert(CourseMapper.toEntity(course))
        );
    }

    @Override
    public void removeFromFavorites(int courseId) {
        executor.execute(() ->
                favoriteCourseDao.deleteById(courseId)
        );
    }

    @Override
    public LiveData<Boolean> isFavorite(int courseId) {
        return favoriteCourseDao.isFavorite(courseId);
    }

    private void rebuildCourses() {
        if (cachedDtos == null) {
            return;
        }

        List<Course> courses = CourseMapper.fromDtoList(
                cachedDtos,
                cachedFavoriteIds
        );

        coursesLiveData.postValue(courses);
    }
}
