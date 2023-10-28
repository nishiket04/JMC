package com.nishiket.jmc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.nishiket.jmc.repository.AuthRepository;
import com.nishiket.jmc.repository.UserDataRepository;

public class AuthViewModel extends AndroidViewModel {
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private FirebaseUser currentUser;
    private AuthRepository repository;
    private UserDataRepository userDataRepository;
    public AuthViewModel(@NonNull Application application) {
        super(application);

        repository = new AuthRepository(application);
        userDataRepository=new UserDataRepository(application);
        currentUser = repository.getCurrentUser();
        firebaseUserMutableLiveData = repository.getFirebaseUserMutableLiveData();
    }

    public void signUp(String email,String pass,String name,String addhar,String mobile){
        repository.signUp(email, pass);
        if(repository.getFlag()) {
            userDataRepository.setUserData(email, name, mobile, addhar);
        }
    }
    public void signIn(String email, String pass){
        repository.signIn(email, pass);
    }

    public void signOut(){
        repository.signOut();
    }

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }
}
