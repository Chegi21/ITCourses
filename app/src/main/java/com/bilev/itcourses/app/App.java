package com.bilev.itcourses.app;

import android.app.Application;

import androidx.room.Room;

import com.bilev.data.api.CourseApi;
import com.bilev.data.api.MockInterceptor;
import com.bilev.data.local.AppDatabase;
import com.bilev.data.repository.CourseServiceImpl;
import com.bilev.domain.repository.CourseService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static App instance;

    private CourseService courseService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDependencies();
    }

    private void initDependencies() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new MockInterceptor(this))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mock.local/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CourseApi courseApi = retrofit.create(CourseApi.class);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "itcourses.db")
                .fallbackToDestructiveMigration()
                .build();

        courseService = new CourseServiceImpl(courseApi, database.favoriteCourseDao());
    }

    public static App getInstance() {
        return instance;
    }

    public CourseService getCourseRepository() {
        return courseService;
    }
}
