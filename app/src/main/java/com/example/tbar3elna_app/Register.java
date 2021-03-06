package com.example.tbar3elna_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tbar3elna_app.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText Name, Email, Password,Localisation,GroupeSang;
    Button RegisterBtn;
    TextView LoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.passworrd);
        GroupeSang = findViewById(R.id.groupe_sang);
        Localisation = findViewById(R.id.localisation);
        RegisterBtn = findViewById(R.id.register_btn);
        LoginBtn = findViewById(R.id.login_btn);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String name = Name.getText().toString();
                String groupe = GroupeSang.getText().toString();
                String local = Localisation.getText().toString();

                db = FirebaseDatabase.getInstance();

                reference = db.getReference().child("Users").child(name);

                UserHelperClass helperClass = new UserHelperClass(name, email, password, groupe, local);

                HashMap<String , String> userMap = new HashMap<>();
                userMap.put("Nom_et_pr??nom" , name);
                userMap.put("Email" , email);
                userMap.put("Password" , password);
                userMap.put("Groupe_sanguin" , groupe);
                userMap.put("Localisation" , local);
                userMap.put("need","false");

                reference.setValue(userMap);

                if(TextUtils.isEmpty(email)){
                    Email.setError("???????? ??????????");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Password.setError("???????? ???????? ????????");
                    return;
                }

                if (password.length() < 6 ){
                    Password.setError("?????? ???? ???????? ???????? ???????? ???????? ???? 6 ??????????");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)

                                    .build();

                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                            }
                                        }
                                    });


                            Toast.makeText(Register.this, "???? ?????????? ???????????????? ??????????", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "?????? ???????????? ??????????" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }

}