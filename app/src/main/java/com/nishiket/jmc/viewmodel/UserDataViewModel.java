package com.nishiket.jmc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.nishiket.jmc.model.UserData;
import com.nishiket.jmc.repository.UserDataRepository;


public class UserDataViewModel extends AndroidViewModel {
    private MutableLiveData<UserData> userDataMutableLiveData;

    public MutableLiveData<UserData> getUserDataMutableLiveData() {
        return userDataMutableLiveData;
    }

    private UserDataRepository userDataRepository;
    public UserDataViewModel(@NonNull Application application) {
        super(application);
        userDataRepository = new UserDataRepository(application);
        userDataMutableLiveData = new MutableLiveData<>();
    }

    public void getUserData(String email){
        userDataRepository.getUserData(email, new UserDataRepository.OnDataRetrievedListener() {
            @Override
            public void onDataRetrieved(UserData userData) {
                userDataMutableLiveData.postValue(userData);
            }
        });
    }
}
