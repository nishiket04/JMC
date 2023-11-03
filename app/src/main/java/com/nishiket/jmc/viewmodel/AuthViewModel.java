package com.nishiket.jmc.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.nishiket.jmc.repository.AuthRepository;
import com.nishiket.jmc.repository.UserDataRepository;

// This is an viewModel which is mediator between UI and database
public class AuthViewModel extends AndroidViewModel {
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData; // mutable live data as an firebasuser
    private FirebaseUser currentUser; // variable of firebase current user
    private AuthRepository repository; // object of an database class
    private UserDataRepository userDataRepository; // object of an databse class

    // this is an constructor which accept an aplication context
    public AuthViewModel(@NonNull Application application) {
        super(application);

        repository = new AuthRepository(application); // crate object of an Auth
        userDataRepository=new UserDataRepository(application); // crate object of an userdata
        currentUser = repository.getCurrentUser(); // get the current user
        firebaseUserMutableLiveData = repository.getFirebaseUserMutableLiveData(); // get the firebase mutable live data
    }

    // when signup is called
    public void signUp(String email,String pass,String name,String addhar,String mobile){
        repository.signUp(email, pass); // in auth call its signUp
        if(!repository.getFlag()) { // if ther account is created then upload the data to firestore
            userDataRepository.setUserData(email, name, mobile, addhar);
        }
    }
    // call the signIn of an Auth class
    public void signIn(String email, String pass){
        repository.signIn(email, pass);
    }

    // call the SignOut of an Auth class
    public void signOut(){
        repository.signOut();
    }
    // this will return mutable live data to infrom UI that there is an user in list or user is logged in
    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    // this will return current user means if user loged in before then there is user in currentUser and it will directly go to home page
    public FirebaseUser getCurrentUser() {
        return currentUser;
    }
}
