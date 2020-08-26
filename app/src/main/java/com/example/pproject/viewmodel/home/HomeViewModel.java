package com.example.pproject.viewmodel.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pproject.model.Store;
import com.example.pproject.model.Theme;
import com.example.pproject.model.dto.IndexRespDto;
import com.example.pproject.viewmodel.home.HomeRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

//    private LiveData<List<Store>> listLiveData1;
//    private LiveData<List<Theme>> listLiveData2;
    private LiveData<IndexRespDto> listLiveData3;
    private HomeRepository homeRepository1,homeRepository2,homeRepository3;

    public HomeViewModel(@NonNull Application application) {
        super(application);
//        homeRepository1 = new HomeRepository();
//        homeRepository2 = new HomeRepository();
        homeRepository3 = new HomeRepository();
//        listLiveData1 = homeRepository1.initStoreData();
//        listLiveData2 = homeRepository2.initThemeData();
        listLiveData3 = homeRepository3.initIndexRespDtoData();
    }

//    public void initLiveData1() {
//        homeRepository1.getStore();
//    }
//    public void initLiveData2() {
//        homeRepository2.getTheme();
//    }
    public void initLiveData3() {
        homeRepository3.getHome();
    }

//    public LiveData<List<Store>> subscribe1() {
//        return listLiveData1;
//    }
//    public LiveData<List<Theme>> subscribe2() {
//        return listLiveData2;
//    }
    public LiveData<IndexRespDto> subscribe3() {
        return listLiveData3;
    }
}
