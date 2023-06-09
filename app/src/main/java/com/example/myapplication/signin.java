package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signin extends AppCompatActivity {

    EditText email,pass;
    Button bt1;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email=findViewById(R.id.signinemail);
        pass=findViewById(R.id.signinpass);

        bt1=findViewById(R.id.button);
        mAuth=FirebaseAuth.getInstance();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String em, pas;
                    em=email.getText().toString();
                    pas=pass.getText().toString();

                    if(TextUtils.isEmpty(em)){
                        Toast.makeText(signin.this, "Enter valid emial id!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(pas)){
                        Toast.makeText(signin.this, "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    mAuth.signInWithEmailAndPassword(em, pas)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(signin.this, "Logged In!", Toast.LENGTH_SHORT).show();
                                        Intent i =
                                                new Intent(signin.this,upload.class);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(signin.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                }
        });

    }
}