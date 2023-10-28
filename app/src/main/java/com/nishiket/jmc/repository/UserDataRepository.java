package com.nishiket.jmc.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class UserDataRepository {
    private Application application;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference refrence= db.collection("userData");
    private String name,email,mobile,addhar;

    public UserDataRepository(Application application){
        this.application=application;
    }

    public void getUserData(String email){
        refrence.document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Document data is in document.getData()
                        Object data = document.getData();
                        Log.d("FirestoreData", "Document data: " + data);
                    } else {
                        Log.d("FirestoreData", "No such document");
                    }
                }
            }
        });
    }

    public void setUserData(String email,String name,String mobile,String addhar){
        Map<String, Object> data = new HashMap<>();
        data.put("name",name);
        data.put("email",email);
        data.put("mobile",mobile);
        data.put("addhar",addhar);

        refrence.document(email).set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("user","success");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(application, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
