package com.bilev.itcourses.app.adapter;

import android.annotation.SuppressLint;

import com.bilev.domain.model.Course;
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter;

import java.util.List;

public class CoursesAdapter extends ListDelegationAdapter<List<Course>> {

    public CoursesAdapter(CourseAdapterDelegate.OnFavoriteClickListener listener) {
        delegatesManager.addDelegate(new CourseAdapterDelegate(listener));
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(List<Course> courses) {
        this.items = courses;
        notifyDataSetChanged();
    }
}
