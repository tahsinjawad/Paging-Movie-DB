package com.darkray.pagingmoviedb.ui.activity.mainActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.darkray.pagingmoviedb.R;
import com.darkray.pagingmoviedb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.adapter.setViewModel(viewModel);
        binding.setViewModel(viewModel);
        observeData();
    }

    private void observeData() {
        viewModel.moviePagedList.observe(this, movies -> {
            if (movies != null) {
                Log.d(TAG, "onChanged: " + movies.size());
                viewModel.adapter.submitList(movies);
            }
        });

        viewModel.stateObserver.observe(this, state -> {
            if (state != null) {
                Log.d(TAG, "onChanged: " + state);
                binding.setState(state);
                if (!viewModel.listEmpty()) {
                    viewModel.adapter.setState(state);
                }
            }
        });
    }

}
