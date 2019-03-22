package com.example.dkn.emscustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileSetting extends AppCompatActivity {

    Toolbar toolbar;

    EditText txtName,txtEmail, txtPhone, txtAge, txtAddress;

    DatabaseReference databaseReference;
    FirebaseUser user;
    String uId;


    Button btnCancel,btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);

        toolbar=findViewById(R.id.actionbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSetting.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        uId = user.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("Riders");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txtName= (EditText) findViewById(R.id.txtName);
                txtEmail =(EditText)findViewById(R.id.txtEmail);
                txtPhone = (EditText) findViewById(R.id.txtPhone);
                txtAge = (EditText) findViewById(R.id.txtDob);
                txtAddress = (EditText) findViewById(R.id.txtAddress);

                String uName = dataSnapshot.child(uId).child("name").getValue(String.class);
                String uEmail = dataSnapshot.child(uId).child("email").getValue(String.class);
                String uPhone = dataSnapshot.child(uId).child("phone").getValue(String.class);
                String uDob = dataSnapshot.child(uId).child("dob").getValue(String.class);
                String uAddress = dataSnapshot.child(uId).child("address").getValue(String.class);

                txtName.setText(uName);
                txtEmail.setText(uEmail);
                txtPhone.setText(uPhone);
                txtAge.setText(uDob);
                txtAddress.setText(uAddress);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
