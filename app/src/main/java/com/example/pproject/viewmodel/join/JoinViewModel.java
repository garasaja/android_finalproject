package com.example.pproject.viewmodel.join;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pproject.model.Store;
import com.example.pproject.model.User;
import com.example.pproject.viewmodel.store.StoreRepository;

import java.util.List;

public class JoinViewModel extends AndroidViewModel {

  //  private LiveData<User> listLiveData;
    private JoinRepository joinRepository;
    private String username,password,email;


    public JoinViewModel(@NonNull Application application) {
        super(application);
        joinRepository = new JoinRepository();
       // listLiveData = joinRepository.initData();
    }

    public void 회원가입하기(String username,String email,String password) {
        joinRepository.getUserJoin(username,email,password);
    }

//    public void initLiveData() {
//        joinRepository.getUser(username,email,password);
//    }

//    public LiveData<User> subscribe() {
//        return listLiveData;
//    }


}
