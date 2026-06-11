package com.bilev.itcourses.app.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bilev.domain.model.Course;
import com.bilev.itcourses.databinding.ItemCourseBinding;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;

import java.util.List;

public class CourseAdapterDelegate extends AdapterDelegate<List<Course>> {

    private final OnFavoriteClickListener listener;

    public CourseAdapterDelegate(OnFavoriteClickListener listener) {
        this.listener = listener;
    }

    @Override
    protected boolean isForViewType(@NonNull List<Course> items, int position) {
        return true;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        ItemCourseBinding binding = ItemCourseBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new CourseViewHolder(binding, listener);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<Course> items, int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        ((CourseViewHolder) holder).bind(items.get(position));
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Course course);
    }
}
