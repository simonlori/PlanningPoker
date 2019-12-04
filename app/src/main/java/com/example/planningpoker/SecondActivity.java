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

public class SecondActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button register;
    private TextView infos;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String Dusername,Dpassword;
    public int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setupViews();

        infos.setText("Complete fields!");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {
                    Dusername = userName.getText().toString();
                    Dpassword = password.getText().toString();

                    registerToDatabase();
                    if(i==1)
                    {
                        Toast.makeText(SecondActivity.this,"Registration successfull",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(SecondActivity.this, "Registration failed",Toast.LENGTH_SHORT).show();
                    }
                    mainactivity();
                }
            }
        });
    }
    private void setupViews()
    {
        userName = (EditText)findViewById(R.id.etUserName);
        password = (EditText)findViewById(R.id.etPassword2);
        register = (Button)findViewById(R.id.btnRegister);
        infos = (TextView)findViewById(R.id.tvInfo2);
    }

    private boolean validate()
    {
        Boolean result = false;

        String name = userName.getText().toString();
        String passw = password.getText().toString();

        if(name.isEmpty() || passw.isEmpty())
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
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void registerToDatabase(){

        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.child("Admins").child(Dusername).child("Password").setValue(Dpassword);
        i = 1;
    }
}
