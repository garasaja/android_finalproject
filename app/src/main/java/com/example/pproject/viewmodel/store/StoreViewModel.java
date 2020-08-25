package com.example.pproject.viewmodel.store;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pproject.model.Store;
import com.example.pproject.viewmodel.store.StoreRepository;

import java.util.List;

public class StoreViewModel extends AndroidViewModel {

    private LiveData<List<Store>> listLiveData;
    private StoreRepository storeRepository;

    public StoreViewModel(@NonNull Application application) {
        super(application);
        storeRepository = new StoreRepository();
        listLiveData = storeRepository.initData();
    }

    public void initLiveData() {
        storeRepository.getStore();
    }

    public LiveData<List<Store>> subscribe() {
        return listLiveData;
    }
}
