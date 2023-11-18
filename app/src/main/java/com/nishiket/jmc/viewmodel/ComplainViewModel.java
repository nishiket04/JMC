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

public class ComplainViewModel extends ViewModel implements ComplainRepository.firebaseComplte {

    private ComplainRepository repository = new ComplainRepository(this);
    Application application;
    private MutableLiveData<List<ComplainModel>> activeComplainListLiveData = new MutableLiveData<>();

    public ComplainViewModel(){}

    public ComplainViewModel(Application application) {
        this.application = application;

    }

    public MutableLiveData<List<ComplainModel>> getActiveComplainListLiveData() {
        return activeComplainListLiveData;
    }
    public void getData(){
        repository.getActiveComplain();
    }
    public void getSolvedData(){
        repository.getSolvedComplain();
    }
    public void getDeleyedData(){
        repository.getDeleyedComplain();
    }

    @Override
    public void onComplete(List<ComplainModel> complainModelList) {
        activeComplainListLiveData.setValue(complainModelList);
    }


}
