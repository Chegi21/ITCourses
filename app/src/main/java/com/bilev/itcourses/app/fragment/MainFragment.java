package com.bilev.itcourses.app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bilev.itcourses.app.App;
import com.bilev.itcourses.app.adapter.CoursesAdapter;
import com.bilev.itcourses.app.viewmodel.MainViewModel;
import com.bilev.itcourses.app.viewmodelfactory.MainViewModelFactory;
import com.bilev.itcourses.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private MainViewModel viewModel;
    private CoursesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this,
                new MainViewModelFactory(App.getInstance().getCourseRepository())
        ).get(MainViewModel.class);

        setupRecyclerView();
        setupSortButton();
        observeViewModel();
    }

    private void setupRecyclerView() {
        adapter = new CoursesAdapter(course -> viewModel.onFavoriteClicked(course));
        binding.rvCourses.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvCourses.setAdapter(adapter);
    }

    private void setupSortButton() {
        binding.ivFilter.setOnClickListener(v -> viewModel.onSortClicked());
    }

    private void observeViewModel() {
        viewModel.getDisplayCourses().observe(getViewLifecycleOwner(), courses -> {
            if (courses != null) adapter.setItems(courses);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
