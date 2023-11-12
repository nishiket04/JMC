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
import com.nishiket.jmc.model.UserData;
import com.nishiket.jmc.user.MainActivity;
import com.nishiket.jmc.viewmodel.AuthViewModel;

public class login extends AppCompatActivity {
   private TextView login_Register;
   private EditText login_Name,login_Pass,login_Mail;
   private AppCompatButton login_Btn;
   private AuthViewModel viewModel;
   private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        assignId(); // assigning Id to the all Views
        userData = new UserData();

        // passing Intent
        Intent toReg=new Intent(this, RegisterActivity.class);
        Intent toHome=new Intent(this, MainActivity.class);
        // all Intent Ends

        // Setup viewModel
        viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);


        // If the user click on Register it will redirect to that activity
        login_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toReg);
            }
        }); // Ends

        // If the user click on Login Button it checks user details and user can LogIn
        login_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= login_Mail.getText().toString(); // email and convert it to string
                String pass=login_Pass.getText().toString(); // password and convert it to string

                if(!email.isEmpty() && !pass.isEmpty()){ // if email and password is not empty then it will call viewModel signIn Method
                    viewModel.signIn(email,pass); // signIn method of viewModel
                    userData.setEmail(email);


                    // now checking in viewModel MutableLiveData is changed or not
                    viewModel.getFirebaseUserMutableLiveData().observe(login.this, new Observer<FirebaseUser>() {
                        @Override
                        public void onChanged(FirebaseUser firebaseUser) { // if its changed
                            if(firebaseUser != null){ // and firebaseUser its not Null then
                                Toast.makeText(login.this, "Login Successfully", Toast.LENGTH_SHORT).show(); // make a toast that its Success
                                startActivity(toHome); // and redirect to Home activity
                                finish(); // and kill this activity from stack
                            }
                        }
                    });
                }else { // else user name or password is empty then make a toast
                    Toast.makeText(login.this, "Enter Email and Password", Toast.LENGTH_SHORT).show();
                }
            }
        });// Ends

    } // onCreate Ends

    // Assign Id method
    private void assignId() {
        login_Register=findViewById(R.id.login_Register);
        login_Btn=findViewById(R.id.login_Btn);
        login_Mail=findViewById(R.id.login_Mail);
        login_Name=findViewById(R.id.login_Name);
        login_Pass=findViewById(R.id.login_Pass);
    } //assignId ends
} // loginActivity ends