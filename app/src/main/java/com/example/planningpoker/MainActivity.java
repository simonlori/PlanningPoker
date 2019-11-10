package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button SignUp;
    private TextView Info;
    private  int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        SignUp = (Button)findViewById(R.id.btnSignUp);
        Info = (TextView)findViewById(R.id.tvInfo);

        Info.setText("Number of remaining attempts: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondactivity();
            }
        });

    }

    private void validate(String userName, String userPassword)
    {
        if((userName.equals("Admin")) && (userPassword.equals("12345")))
        {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else
        {
            counter--;
            Info.setText("Number of remaining attempts: " + String.valueOf(counter));
            if (counter == 0)
            {
                Login.setEnabled(false);
            }
        }
    }

    private void secondactivity ()
    {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
