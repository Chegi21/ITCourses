package com.bilev.itcourses.app.adapter;

import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;

import com.bilev.domain.model.Course;
import com.bilev.itcourses.databinding.ItemCourseBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class CourseViewHolder extends RecyclerView.ViewHolder {

    private final ItemCourseBinding binding;
    private final CourseAdapterDelegate.OnFavoriteClickListener listener;

    public CourseViewHolder(ItemCourseBinding binding,
                            CourseAdapterDelegate.OnFavoriteClickListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(Course course) {
        binding.tvTitle.setText(course.getTitle());
        binding.tvDescription.setText(course.getText());
        binding.tvPrice.setText(String.format("%s ₽", course.getPrice()));
        binding.tvRate.setText(course.getRate());
        binding.tvStartDate.setText(String.format("c %s", formatDate(course.getStartDate())));

        if (course.isHasLike()) {
            binding.ivFavorite.setColorFilter(Color.parseColor("#12B956"));
        } else {
            binding.ivFavorite.setColorFilter(Color.parseColor("#BDBDBD"));
        }

        binding.ivFavorite.setOnClickListener(v -> listener.onFavoriteClick(course));
    }

    private String formatDate(String raw) {
        if (raw == null || raw.isEmpty()) return "";

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru"));

        LocalDate date = LocalDate.parse(raw, inputFormatter);

        return date.format(outFormatter);
    }
}
