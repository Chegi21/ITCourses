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
import com.bilev.itcourses.app.viewmodel.FavoritesViewModel;
import com.bilev.itcourses.app.viewmodelfactory.FavoritesViewModelFactory;
import com.bilev.itcourses.databinding.FragmentFavoritesBinding;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private FavoritesViewModel viewModel;
    private CoursesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this,
                new FavoritesViewModelFactory(App.getInstance().getCourseRepository())
        ).get(FavoritesViewModel.class);

        setupRecyclerView();
        observeViewModel();
    }

    private void setupRecyclerView() {
        adapter = new CoursesAdapter(course -> viewModel.removeFromFavorites(course));
        binding.rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvFavorites.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.getFavoriteCourses().observe(getViewLifecycleOwner(), courses -> {
            boolean isEmpty = courses == null || courses.isEmpty();
            binding.rvFavorites.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
            binding.tvEmpty.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
            if (!isEmpty) adapter.setItems(courses);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
