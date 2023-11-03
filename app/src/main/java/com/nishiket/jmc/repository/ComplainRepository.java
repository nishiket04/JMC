package com.nishiket.jmc.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.nishiket.jmc.model.ComplainModel;

import java.util.ArrayList;
import java.util.List;

public class ComplainRepository {
    private Application application;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = (CollectionReference) db.collectionGroup("complain");
    Query query = collectionReference.whereEqualTo("status", "Active");
    public ComplainRepository(Application application) {
        this.application = application;
    }

    public LiveData<List<ComplainModel>> getActiveComplain(){
        MutableLiveData<List<ComplainModel>> complainLiveData = new MutableLiveData<>();
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<ComplainModel> complainList = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    ComplainModel complain = document.toObject(ComplainModel.class);
                    complainList.add(complain);
                }
                complainLiveData.setValue(complainList);
            } else {
                Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return complainLiveData;
    }
}
