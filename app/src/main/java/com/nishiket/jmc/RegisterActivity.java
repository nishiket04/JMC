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

public class RegisterActivity extends AppCompatActivity {
   private TextView login_Register;
   private EditText reg_Name,reg_Mail,reg_Mobile,reg_Addhar,reg_Pass,reg_Con_Pass;
   private AppCompatButton reg_Btn;
    private AuthViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        assignId();
        Intent toLogin= new Intent(this, login.class);
        Intent toHome=new Intent(this, MainActivity.class);

        viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);

        login_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toLogin);
            }
        });
        reg_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= reg_Mail.getText().toString();
                String pass=reg_Pass.getText().toString();
                String conPass=reg_Con_Pass.getText().toString();
                String name=reg_Name.getText().toString();
                String mobile=reg_Mobile.getText().toString();
                String addhar=reg_Addhar.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty() && !conPass.isEmpty() && !name.isEmpty() && !mobile.isEmpty() && !addhar.isEmpty()) {
                    if (pass.equals(conPass)) {
                        viewModel.signUp(email, pass,name,addhar,mobile);
                        viewModel.getFirebaseUserMutableLiveData().observe(RegisterActivity.this, new Observer<FirebaseUser>() {
                            @Override
                            public void onChanged(FirebaseUser firebaseUser) {
                                if (firebaseUser != null) {
                                    Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(toHome);
                                    finish();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "Enter All Fileds", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void assignId() {
        login_Register=findViewById(R.id.login_Register);
        reg_Addhar=findViewById(R.id.reg_Addhar);
        reg_Btn=findViewById(R.id.reg_Btn);
        reg_Mail=findViewById(R.id.reg_Mail);
        reg_Name=findViewById(R.id.reg_Name);
        reg_Mobile=findViewById(R.id.reg_Mobile);
        reg_Pass=findViewById(R.id.reg_Pass);
        reg_Con_Pass=findViewById(R.id.reg_Con_Pass);
    }
}