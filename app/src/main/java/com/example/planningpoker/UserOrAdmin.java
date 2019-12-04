package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserOrAdmin extends AppCompatActivity {

    private TextView info;
    private Button user;
    private Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_or_admin);

        info = (TextView)findViewById(R.id.tvInfo);
        user = (Button)findViewById(R.id.bUser);
        admin = (Button)findViewById(R.id.bAdmin);

        info.setText("Select the SignUp method!");

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondactivity();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userregistration();
            }
        });
    }
    private void secondactivity ()
    {
        Intent intent = new Intent(UserOrAdmin.this,SecondActivity.class);
        startActivity(intent);
    }

    private void userregistration ()
    {
        Intent intent = new Intent(UserOrAdmin.this,UserRegistration.class);
        startActivity(intent);
    }
}
