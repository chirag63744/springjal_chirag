package com.example.springjal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    RelativeLayout loginBtn;
    private FirebaseAuth mAuth;
    private EditText emailInput, passwordInput;
    private TextView forgotPasswordTxt;
    String emailtxtinput;
    private loading loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailInput = findViewById(R.id.emailinput);
        passwordInput = findViewById(R.id.passwdinput);

        loginBtn = findViewById(R.id.loginbtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        forgotPasswordTxt = findViewById(R.id.forgotpsswdtxt);
        forgotPasswordTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your forgot password logic here
            }
        });

        loadingDialog = new loading(LoginActivity.this);
    }

    private void loginUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        loadingDialog.show(); // Show loading dialog

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(user.getUid());
                                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            String userType = dataSnapshot.child("role").getValue(String.class);
                                            if (userType != null) {
                                                switch (userType) {
                                                    case "Data Collector":
                                                        startActivity(new Intent(LoginActivity.this, DataCollector_Home.class));
                                                        Toast.makeText(LoginActivity.this, "Logged In successfully!", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case "Data Approver":
                                                        startActivity(new Intent(LoginActivity.this, DataApprover_Home.class));
                                                        Toast.makeText(LoginActivity.this, "Logged In  successfully!", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    case "Admin":
                                                        startActivity(new Intent(LoginActivity.this, Admin_Home.class));
                                                        Toast.makeText(LoginActivity.this, "Logged In  successfully!", Toast.LENGTH_SHORT).show();
                                                        break;
                                                    default:
                                                        Toast.makeText(LoginActivity.this, "Unknown user role", Toast.LENGTH_SHORT).show();
                                                        break;
                                                }
                                                finish();
                                            } else {
                                                Toast.makeText(LoginActivity.this, "User role not found", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        loadingDialog.dismiss(); // Dismiss loading dialog
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(LoginActivity.this, "Database error", Toast.LENGTH_SHORT).show();
                                        loadingDialog.dismiss(); // Dismiss loading dialog
                                    }
                                });
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            loadingDialog.dismiss(); // Dismiss loading dialog
                        }
                    }
                });
    }
}
