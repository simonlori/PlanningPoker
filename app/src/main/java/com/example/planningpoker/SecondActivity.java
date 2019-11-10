package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private EditText repassword;
    private Button register;
    private TextView infos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupViews();
        infos.setText("Passwords must match!");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    private void setupViews()
    {
        userName = (EditText)findViewById(R.id.etUserName);
        password = (EditText)findViewById(R.id.etPassword2);
        repassword = (EditText)findViewById(R.id.etPassword3);
        register = (Button)findViewById(R.id.btnRegister);
        infos = (TextView)findViewById(R.id.tvInfo2);
    }

    private boolean validate()
    {
        Boolean result = false;

        String name = userName.getText().toString();
        String passw = password.getText().toString();
        String repassw = repassword.getText().toString();

        if(name.isEmpty() && passw.isEmpty() && repassw.isEmpty())
        {
            Toast.makeText(this, "Please complete all fields!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return result;
    }
}
