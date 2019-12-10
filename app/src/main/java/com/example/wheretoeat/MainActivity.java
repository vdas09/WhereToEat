package com.example.wheretoeat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Email, Password;
    Button SignIn, Register, ReactApp;
    CallbackManager callbackManager;
    Button FacebookButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        SignIn = findViewById(R.id.SignIn);
        Register = findViewById(R.id.Register);
        ReactApp = findViewById(R.id.ReactApp);
        FacebookButton = findViewById(R.id.facebookButton);

        SignIn.setOnClickListener(this);
        Register.setOnClickListener(this);
        ReactApp.setOnClickListener(this);
        FacebookButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        // Sign out of FB
        mAuth.signOut();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        if (view == ReactApp) {
            String reactAppURL = "https://wheretoeat-webapp.rishipr.now.sh/";
            openURL(reactAppURL);
        } else if (view == Register || view == SignIn){
            if (Email.getText().toString().isEmpty() || Password.getText().toString().isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();

                return;
            }

            if (view == Register) {
                registerUser(Email.getText().toString(), Password.getText().toString());
            }
            else if (view == SignIn) {
                loginUser(Email.getText().toString(), Password.getText().toString());
            }

        } else if (view == FacebookButton) {
            callbackManager = CallbackManager.Factory.create();
            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            Intent mainIntent = new Intent(MainActivity.this,SearchActivity.class);
                            startActivity(mainIntent);
                        }

                        @Override
                        public void onCancel() {
                            // App code
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                        }
                    });

            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList(
                    "email", "public_profile"));
        }
    }

    public void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent mainIntent = new Intent(MainActivity.this,SearchActivity.class);
                            startActivity(mainIntent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "User Registration Successful", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Account Registration Failed", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }

    public void openURL(String url) {
        String urlString = URLUtil.guessUrl(url);

        try {
            Intent openURL = new Intent(android.content.Intent.ACTION_VIEW);
            openURL.setData(Uri.parse(urlString));
            startActivity(openURL);
        } catch (ActivityNotFoundException exception) {
            Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}