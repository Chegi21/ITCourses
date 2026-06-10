package com.bilev.data.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CoursesResponse {

    @SerializedName("courses")
    private List<CourseDto> courses;

    public List<CourseDto> getCourses() { return courses; }
}
