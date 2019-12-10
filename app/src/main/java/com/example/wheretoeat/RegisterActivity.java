package com.example.wheretoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText RegisterEmail, RegisterPassword;
    Button RegisterButton, RegisterReturntoLogin;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterEmail = findViewById(R.id.RegisterEmail);
        RegisterPassword = findViewById(R.id.RegisterPassword);
        RegisterButton = findViewById(R.id.RegisterButton);
        RegisterReturntoLogin = findViewById(R.id.RegisterReturnToLogin);


        RegisterButton.setOnClickListener(this);
        RegisterReturntoLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {

        if (view == RegisterButton) {

            makeNewUsers(RegisterEmail.getText().toString(), RegisterPassword.getText().toString());


        } else if (view == RegisterReturntoLogin) {

            Intent Register = new Intent(this,MainActivity.class);
            startActivity(Register);


        }
    }

    public void makeNewUsers(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this, "User Registration Successful", Toast.LENGTH_SHORT).show();


                        } else {
                            // If registering fails, display a message to the user.

                            Toast.makeText(RegisterActivity.this, "Account Registration Failed", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
}
