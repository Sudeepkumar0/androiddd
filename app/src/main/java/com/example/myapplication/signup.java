package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.signin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email,pass;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email=findViewById(R.id.signupemail);
        pass=findViewById(R.id.signuppass);
        signup=findViewById(R.id.button_up);
        mAuth = FirebaseAuth.getInstance();
signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String s1=email.getText().toString();
        String s2=pass.getText().toString();
        signUpWithEmailAndPassword(s1,s2);

    }
});
        // Example usage: sign up with email and password
    }

    private void signUpWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, user is authenticated
                            Log.d("TAG", "createUserWithEmail:success");
                            Toast.makeText(signup.this, "Sign up successful.", Toast.LENGTH_SHORT).show();
                        } else {
                            // Sign up failed
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signup.this, "Sign up failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void signinbtn(View view) {
        Intent i = new Intent(signup.this, signin.class);
        startActivity(i);
    }
}

