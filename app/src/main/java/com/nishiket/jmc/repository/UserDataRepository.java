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

// this class upload and retrive user data in fireStore
public class UserDataRepository {
    private Application application; // application context variable
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); // firebase firestore instance
    private CollectionReference refrence= db.collection("userData"); // now collection refrence here our collection refrence is userData
    private String name,email,mobile,addhar; // keys as in FireStore
    private Map<String,Object> map;
    // this is an constructor which accept application context
    public UserDataRepository(Application application){
        this.application=application;
        map = new HashMap<>();
    }

    // this mwthod is used for getting data from its email because in our firestore email is key of any document
    public Map getUserData(String email){
        // to collection refrence with documentid as email get that data
        refrence.document(email).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                  DocumentSnapshot documentSnapshot= task.getResult();
                  name=documentSnapshot.getString("name");
                  mobile = documentSnapshot.getString("mobile");
                  addhar = documentSnapshot.getString("addhar");
                    map.put("name",name);
                    map.put("mobile",mobile);
                    map.put("addhar",addhar);
                }
            }
        });
        return map;
    }

    // to upload data of an user when register
    public void setUserData(String email,String name,String mobile,String addhar){ // getting all the strings
        // now crated a hashMap and upend all the data in key value pair
        Map<String, Object> data = new HashMap<>();
        data.put("name",name);
        data.put("email",email);
        data.put("mobile",mobile);
        data.put("addhar",addhar);

        // to collection refrence and set document id as email and set the data
        refrence.document(email).set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() { // if its success then
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
