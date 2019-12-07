package com.example.planningpoker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

public class LoginAsAdmin extends AppCompatActivity {

    private Button bLoginAdmin;
    private EditText AdminName;
    private EditText AdminPass;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_admin);

        bLoginAdmin = (Button)findViewById(R.id.bAdminLogin);
        AdminName = (EditText)findViewById(R.id.etAdminName);
        AdminPass = (EditText)findViewById(R.id.etAdminPassword);

        bLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = AdminName.getText().toString();
                password = AdminPass.getText().toString();

                adminCheck(username);
            }
        });
    }

    private void adminCheck(final String username){
        // Read from the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Admins").child(username).child("Password");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String kod = dataSnapshot.getValue().toString();
                //Toast.makeText(MainActivity.this,kod, Toast.LENGTH_SHORT).show();

                if(!kod.equals(password)){
                    Toast.makeText(LoginAsAdmin.this,"Password or Username is incorrect", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginAsAdmin.this,"Successful login",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
