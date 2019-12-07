package com.example.planningpoker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button SignUp;
    private TextView Info;
    private  int counter = 5;
    private FirebaseDatabase database;
    private DatabaseReference myRef2;
    private String username,password;
    private Button lAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        SignUp = (Button)findViewById(R.id.btnSignUp);
        Info = (TextView)findViewById(R.id.tvInfo);
        lAdmin = (Button)findViewById(R.id.bLoginAdmin);

        lAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginasadmin();
            }
        });

        Info.setText("Number of attempts: 5");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(validate())
                {
                    username = Name.getText().toString();
                    password = Password.getText().toString();

                    userCheck(username);
                }
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useroradmin();
            }
        });

    }

    private void useroradmin ()
    {
        Intent intent = new Intent(MainActivity.this, UserOrAdmin.class);
        startActivity(intent);
    }

    private void loginasadmin ()
    {
        Intent intent = new Intent(MainActivity.this, LoginAsAdmin.class);
        startActivity(intent);
    }

    private void userCheck(final String username){
        // Read from the database
        database = FirebaseDatabase.getInstance();
        myRef2 = database.getReference("Users").child(username).child("Password");

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String kod = dataSnapshot.getValue().toString();
                //Toast.makeText(MainActivity.this,kod, Toast.LENGTH_SHORT).show();

                if(!kod.equals(password)){
                    Toast.makeText(MainActivity.this,"Password or Username is incorrect", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("Number of attempts: " + String.valueOf(counter));
                    if (counter == 0)
                    {
                        Login.setEnabled(false);
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Successful login",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private boolean validate()
    {
        Boolean result = false;

        String name = Name.getText().toString();
        String passw = Password.getText().toString();

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
}
