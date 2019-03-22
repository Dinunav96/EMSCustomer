package com.example.dkn.emscustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dkn.emscustomer.Model.Rider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    Toolbar toolbar;

    TextView txtName,txtEmail, txtPhone, txtAge, txtAddress;

    DatabaseReference databaseReference;
    FirebaseUser user;
    String uId;

    ImageButton btnSetting;


    Rider rider = new Rider();

    public Profile() {

        uId =FirebaseAuth.getInstance().getCurrentUser().getUid();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar=findViewById(R.id.actionbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSetting= (ImageButton) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, ProfileSetting.class);
                startActivity(intent);
            }
        });



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
                txtName= (TextView) findViewById(R.id.txtName);
                txtEmail =(TextView)findViewById(R.id.txtEmail);
                txtPhone = (TextView) findViewById(R.id.txtPhone);
                txtAge = (TextView) findViewById(R.id.txtAge);
                txtAddress = (TextView) findViewById(R.id.txtAddress);

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
