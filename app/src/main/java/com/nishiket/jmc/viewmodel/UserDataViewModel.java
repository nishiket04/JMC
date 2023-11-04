package com.nishiket.jmc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.nishiket.jmc.repository.UserDataRepository;

import java.util.HashMap;
import java.util.Map;

public class UserDataViewModel extends AndroidViewModel {
    private String name,mobile,addhar;
   private Map<String,Object> map;

    private UserDataRepository userDataRepository;
    public UserDataViewModel(@NonNull Application application) {
        super(application);
        userDataRepository = new UserDataRepository(application);
        map = new HashMap<>();
    }

    public void getUserData(String email){
     map = userDataRepository.getUserData(email);
     name= (String) map.get("name");
     mobile = (String) map.get("mobile");
     addhar = (String) map.get("addhar");
    }
}
