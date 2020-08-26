package com.example.pproject.viewmodel.themedetail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pproject.model.Theme;
import com.example.pproject.model.dto.ThemeDetailRespDto;
import com.example.pproject.viewmodel.themedetail.ThemeDetailRepository;

public class ThemeDetailViewModel extends AndroidViewModel {

    private LiveData<ThemeDetailRespDto> liveData;
    private ThemeDetailRepository themeDetailRepository;

    public ThemeDetailViewModel(@NonNull Application application) {
        super(application);
        themeDetailRepository = new ThemeDetailRepository();
        liveData = themeDetailRepository.initData();
    }

    public void initLiveData(int id) {
        themeDetailRepository.getTheme(id);
    }

    public LiveData<ThemeDetailRespDto> subscribe() {
        return liveData;
    }
}
