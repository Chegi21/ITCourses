package com.bilev.itcourses.app.viewmodel;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bilev.domain.model.Course;
import com.bilev.domain.usercase.GetCoursesUseCase;
import com.bilev.domain.usercase.ToggleFavoriteUseCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainViewModel extends ViewModel {

    @SuppressLint("ConstantLocale")
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private final GetCoursesUseCase getCoursesUseCase;
    private final ToggleFavoriteUseCase toggleFavoriteUseCase;

    private final MutableLiveData<Boolean> sortDescending = new MutableLiveData<>(false);

    private final MediatorLiveData<List<Course>> displayCourses = new MediatorLiveData<>();

    private LiveData<List<Course>> rawCourses;

    public MainViewModel(
            GetCoursesUseCase getCoursesUseCase,
            ToggleFavoriteUseCase toggleFavoriteUseCase
    ) {
        this.getCoursesUseCase = getCoursesUseCase;
        this.toggleFavoriteUseCase = toggleFavoriteUseCase;

        initSources();
    }

    private void initSources() {
        rawCourses = getCoursesUseCase.execute();
        displayCourses.addSource(rawCourses, courses -> recalculate());
        displayCourses.addSource(sortDescending, value -> recalculate());
    }

    public LiveData<List<Course>> getDisplayCourses() {
        return displayCourses;
    }

    public void onSortClicked() {
        Boolean current = sortDescending.getValue();
        sortDescending.setValue(current == null || !current);
    }

    public void onFavoriteClicked(Course course) {
        toggleFavoriteUseCase.execute(course, course.isHasLike());

        List<Course> current = displayCourses.getValue();
        if (current == null) {
            return;
        }

        List<Course> updated = new ArrayList<>();
        for (Course c : current) {
            if (c.getId() == course.getId()) {
                c.setHasLike(!c.isHasLike());
            }
            updated.add(c);
        }

        displayCourses.setValue(updated);
    }

    private void recalculate() {
        List<Course> courses = rawCourses.getValue();
        if (courses == null) {
            return;
        }

        boolean descending = Boolean.TRUE.equals(sortDescending.getValue());

        displayCourses.setValue(sortCourses(courses, descending));
    }

    private List<Course> sortCourses(
            List<Course> courses,
            boolean descending
    ) {

        List<Course> sorted = new ArrayList<>(courses);
        if (!descending) {
            return sorted;
        }

        sorted.sort((a, b) -> {
            try {

                Date dateA = DATE_FORMAT.parse(a.getPublishDate());
                Date dateB = DATE_FORMAT.parse(b.getPublishDate());

                if (dateA == null || dateB == null) {
                    return 0;
                }

                return dateB.compareTo(dateA);

            } catch (ParseException e) {
                return 0;
            }
        });

        return sorted;
    }
}
