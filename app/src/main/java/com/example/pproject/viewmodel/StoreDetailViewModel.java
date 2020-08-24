package com.example.pproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pproject.model.Review;
import com.example.pproject.model.Store;
import com.example.pproject.model.Theme;

import java.util.List;

public class StoreDetailViewModel extends AndroidViewModel {

    private LiveData<List<Theme>> listLiveData1;
    private LiveData<List<Review>> listLiveData2;
    private StoreDetailRepository storeDetailRepository;

    public StoreDetailViewModel(@NonNull Application application) {
        super(application);
        storeDetailRepository = new StoreDetailRepository();
        listLiveData1 = storeDetailRepository.initData1();
        listLiveData2 = storeDetailRepository.initData2();
    }

    public void initLiveData1(int id) {
        storeDetailRepository.getStore(id);
    }
    public void initLiveData2(int id) {
        storeDetailRepository.getReview(id);
    }

    public LiveData<List<Store>> subscribe1() {
        return listLiveData1;
    }
    public LiveData<List<Review>> subscribe2() {
        return listLiveData2;
    }
}
