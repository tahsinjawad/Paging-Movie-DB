package com.darkray.pagingmoviedb.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.darkray.pagingmoviedb.R;
import com.darkray.pagingmoviedb.adapter.MovieItemAdapter;
import com.darkray.pagingmoviedb.databinding.ActivityMainBinding;
import com.darkray.pagingmoviedb.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main_Activity";
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private MovieItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);
        initAdapter();
        initState();
    }

    private void initAdapter(){
        adapter = new MovieItemAdapter();
        adapter.setViewModel(viewModel);
        binding.rvMain.setAdapter(adapter);
        viewModel.getMoviePagedList().observe(this, movies -> {
            if(movies != null){
                Log.d(TAG, "onChanged: "+movies.size());
                adapter.submitList(movies);
            }
        });
    }

    private void initState(){
        viewModel.getState().observe(this, state -> {
            if(state != null){
                Log.d(TAG, "onChanged: "+state);
                binding.setState(state);
                if(!viewModel.listEmpty()){
                    adapter.setState(state);
                }
            }
        });

    }

}
