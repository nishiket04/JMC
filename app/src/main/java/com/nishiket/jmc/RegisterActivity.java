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
import com.nishiket.jmc.user.VerificationActivity;
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

        assignId(); // assigning Id to the all Views

        // passing Intent
        Intent toLogin= new Intent(this, login.class);
        Intent toHome=new Intent(this, VerificationActivity.class);
        // all Intent Ends

        // Setup viewModel
        viewModel =new ViewModelProvider((ViewModelStoreOwner) this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(AuthViewModel.class);

        // If the user click on LogIn it will redirect to that activity
        login_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toLogin);
            }
        }); // Ends

        // If the user click on Register Button it checks user details and user can Register
        reg_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= reg_Mail.getText().toString(); // email and convert it to string
                String pass=reg_Pass.getText().toString(); // password and convert it to string
                String conPass=reg_Con_Pass.getText().toString(); // confirm password and convert it to string
                String name=reg_Name.getText().toString(); // name and convert it to string
                String mobile=reg_Mobile.getText().toString(); // mobile and convert it to string
                String addhar=reg_Addhar.getText().toString(); // addhar and convert it to string

                if(!email.isEmpty() && !pass.isEmpty() && !conPass.isEmpty() && !name.isEmpty() && !mobile.isEmpty() && !addhar.isEmpty()) { // if any of the fields are not empty then it will call viewModel signUn Method
                    if (pass.equals(conPass)) { // if the password equals to confirm password
                        viewModel.signUp(email, pass,name,addhar,mobile); // signUp method of viewModel

                        // now checking in viewModel MutableLiveData is changed or not
                        viewModel.getFirebaseUserMutableLiveData().observe(RegisterActivity.this, new Observer<FirebaseUser>() {
                            @Override
                            public void onChanged(FirebaseUser firebaseUser) { // if its changed
                                if (firebaseUser != null) {  // and firebaseUser its not Null then
                                    Toast.makeText(RegisterActivity.this, "Register Successfully", Toast.LENGTH_SHORT).show(); // make a toast that its Success
                                    startActivity(toHome); // and redirect to Home activity
                                    finish(); // and kill this activity from stack
                                }
                            }
                        });
                    }else { // else make a toast that password doesn't match
                        Toast.makeText(RegisterActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }else { // else toast to enter all fields
                    Toast.makeText(RegisterActivity.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        }); // Ends

    } // onCreate Ends

    // Assign Id method
    private void assignId() {
        login_Register=findViewById(R.id.login_Register);
        reg_Addhar=findViewById(R.id.reg_Addhar);
        reg_Btn=findViewById(R.id.reg_Btn);
        reg_Mail=findViewById(R.id.reg_Mail);
        reg_Name=findViewById(R.id.reg_Name);
        reg_Mobile=findViewById(R.id.reg_Mobile);
        reg_Pass=findViewById(R.id.reg_Pass);
        reg_Con_Pass=findViewById(R.id.reg_Con_Pass);
    }//assignId ends

}// RegisterActivity ends