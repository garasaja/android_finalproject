package com.example.pproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pproject.model.Theme;

import java.util.List;

public class ThemeDetailViewModel extends AndroidViewModel {

    private LiveData<List<Theme>> listLiveData;
    private ThemeRepository themeRepository;

    public ThemeDetailViewModel(@NonNull Application application) {
        super(application);
        themeRepository = new ThemeRepository();
        listLiveData = themeRepository.initData();
    }

    public void initLiveData() {
        themeRepository.getTheme();
    }

    public LiveData<List<Theme>> subscribe() {
        return listLiveData;
    }
}
