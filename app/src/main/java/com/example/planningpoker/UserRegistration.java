package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistration extends AppCompatActivity {

    private EditText uUsername;
    private EditText uPassword;
    private EditText uDepartment;
    private Button uRegister;
    private TextView uInfos;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String Dusername,Dpassword,Ddepartment;
    public int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        setupViews();

        uInfos.setText("Complete fields!");

        uRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    Dusername = uUsername.getText().toString();
                    Dpassword = uPassword.getText().toString();
                    Ddepartment = uDepartment.getText().toString();

                    registerToDatabase();
                    if(i==1)
                    {
                        Toast.makeText(UserRegistration.this,"Registration successfull",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(UserRegistration.this, "Registration failed",Toast.LENGTH_SHORT).show();
                    }
                    mainactivity();
                }
            }
        });
    }
    private void setupViews()
    {
        uUsername = (EditText)findViewById(R.id.etUName);
        uPassword = (EditText)findViewById(R.id.etUPassword);
        uDepartment = (EditText)findViewById(R.id.etUDepartment);
        uRegister = (Button)findViewById(R.id.bURegister);
        uInfos = (TextView)findViewById(R.id.tvUInfo);
    }
    private boolean validate()
    {
        Boolean result = false;

        String name = uUsername.getText().toString();
        String passw = uPassword.getText().toString();
        String depart = uDepartment.getText().toString();

        if(name.isEmpty() || passw.isEmpty() || depart.isEmpty())
        {
            Toast.makeText(this, "Please complete all fields!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }

    private void mainactivity ()
    {
        Intent intent = new Intent(UserRegistration.this, MainActivity.class);
        startActivity(intent);
    }

    private void registerToDatabase(){

        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("Users").child(Dusername).child("Password").setValue(Dpassword);
        myRef.child("Users").child(Dusername).child("Department").setValue(Ddepartment);
        i = 1;
    }
}
