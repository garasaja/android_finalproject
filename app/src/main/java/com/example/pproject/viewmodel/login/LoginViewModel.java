package com.example.pproject.viewmodel.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pproject.model.User;
import com.example.pproject.viewmodel.join.JoinRepository;

public class LoginViewModel extends AndroidViewModel {

    private LiveData<User> listLiveData;
    private LoginRepository loginRepository;
    private String username,password,email;


    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository();
       // listLiveData = loginRepository.initData();
    }

//    public void initLiveData() {
//        joinRepository.getUser(username,email,password);
//    }

    public LiveData<User> subscribe() {
        return listLiveData;
    }

    public void 로그인하기(String username,String password) {
        loginRepository.getUser(username,password);
    }
}
