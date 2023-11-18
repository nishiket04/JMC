package com.nishiket.jmc.repository;

import android.app.Application;
import android.util.Log;
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
    private firebaseComplte firebaseComplte;
    private Application application;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Query query = db.collectionGroup("complain").whereEqualTo("status","Active");
    Query querySolved = db.collectionGroup("complain").whereEqualTo("status","Solved");
    Query queryDeleyed = db.collectionGroup("complain").whereEqualTo("status","Deleyed");
    public ComplainRepository(firebaseComplte firebaseComplte) {
        this.firebaseComplte = firebaseComplte;
//        this.application = application;
    }

    public void getActiveComplain(){
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot result = task.getResult();
                    if (result!=null) {
                        List<ComplainModel> complainModelList = task.getResult().toObjects(ComplainModel.class);
                        if (firebaseComplte != null) {
                            firebaseComplte.onComplete(complainModelList);
                        }
                        Log.d("data", "getActiveComplain: " + task.getResult().getMetadata());
                    }
                }else {
                    Log.e("data", "Error getting active complains", task.getException());
                }
            }
        });
    }

    public void getSolvedComplain(){
        querySolved.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot result = task.getResult();
                    if (result!=null) {
                        List<ComplainModel> complainModelList = task.getResult().toObjects(ComplainModel.class);
                        if (firebaseComplte != null) {
                            firebaseComplte.onComplete(complainModelList);
                        }
                        Log.d("data", "getActiveComplain: " + task.getResult().getMetadata());
                    }
                }else {
                    Log.e("data", "Error getting active complains", task.getException());
                }
            }
        });
    }

    public void getDeleyedComplain(){
        queryDeleyed.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot result = task.getResult();
                    if (result!=null) {
                        List<ComplainModel> complainModelList = task.getResult().toObjects(ComplainModel.class);
                        if (firebaseComplte != null) {
                            firebaseComplte.onComplete(complainModelList);
                        }
                        Log.d("data", "getActiveComplain: " + task.getResult().getMetadata());
                    }
                }else {
                    Log.e("data", "Error getting active complains", task.getException());
                }
            }
        });
    }

    public interface firebaseComplte{
        void onComplete(List<ComplainModel> complainModelList);
    }
}
