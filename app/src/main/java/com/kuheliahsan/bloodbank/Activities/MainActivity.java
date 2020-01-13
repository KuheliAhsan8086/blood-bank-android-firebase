package com.kuheliahsan.bloodbank.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.ProviderQueryResult;
import com.kuheliahsan.bloodbank.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText emailEt,passwordEt;
    private Button submit;
    private TextView register;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailEt=findViewById(R.id.emailID);
        passwordEt=findViewById(R.id.passID);
        submit=findViewById(R.id.submitID);
        register=findViewById(R.id.regID);
        progressBar=findViewById(R.id.progress);

        mAuth=FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email= emailEt.getText().toString().trim();
                final String password = passwordEt.getText().toString().trim();
                if(email.isEmpty()){
                    emailEt.setError("Enter email");
                    emailEt.requestFocus();
                    return;
                }
                if (password.isEmpty()){
                    passwordEt.setError("Enter Password");
                    passwordEt.requestFocus();
                    return;
                }


                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEt.setError("Enter valid email");
                    emailEt.requestFocus();
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                        boolean c=task.getResult().getProviders().isEmpty();

                        if (!c){
                            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        finish();
                                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(MainActivity.this,Home.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(i);

                                    }
                                    else{
                                        Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "Email not register", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });



    }
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(MainActivity.this,Home.class));
        }
    }



}
