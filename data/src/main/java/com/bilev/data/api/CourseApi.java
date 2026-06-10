package com.bilev.data.api;

import com.bilev.data.dto.CoursesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseApi {
    @GET("courses.json")
    Call<CoursesResponse> getCourses();
}
