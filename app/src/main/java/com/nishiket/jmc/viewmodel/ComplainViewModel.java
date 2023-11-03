package com.nishiket.jmc.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.nishiket.jmc.model.ComplainModel;
import com.nishiket.jmc.repository.ComplainRepository;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComplainViewModel extends ViewModel {
    private ComplainRepository repository;
    Application application;
    private LiveData<List<ComplainModel>> activeComplainListLiveData;

    public ComplainViewModel(Application application) {
        this.application = application;
        repository = new ComplainRepository(application);
        activeComplainListLiveData = repository.getActiveComplain();
    }
    public LiveData<List<ComplainModel>> getActiveComplainListLiveData(){
        return activeComplainListLiveData;
    }

}
