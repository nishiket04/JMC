package com.nishiket.jmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.nishiket.jmc.user.MainActivity;
import com.nishiket.jmc.viewmodel.AuthViewModel;

public class login extends AppCompatActivity {
   private TextView login_Register;
   private EditText login_Name,login_Pass,login_Mail;
   private AppCompatButton login_Btn;
   private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        assignId();
        Intent toReg=new Intent(this, RegisterActivity.class);
        Intent toHome=new Intent(this, MainActivity.class);

        viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);



        login_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toReg);
            }
        });
        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= login_Mail.getText().toString();
                String pass=login_Pass.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty()){
                    viewModel.signIn(email,pass);
                    Toast.makeText(login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    viewModel.getFirebaseUserMutableLiveData().observe(login.this, new Observer<FirebaseUser>() {
                        @Override
                        public void onChanged(FirebaseUser firebaseUser) {
                            if(firebaseUser != null){
                                startActivity(toHome);
                            }
                        }
                    });
                }else {
                    Toast.makeText(login.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void assignId() {
        login_Register=findViewById(R.id.login_Register);
        login_Btn=findViewById(R.id.login_Btn);
        login_Mail=findViewById(R.id.login_Mail);
        login_Name=findViewById(R.id.login_Name);
        login_Pass=findViewById(R.id.login_Pass);
    }
}