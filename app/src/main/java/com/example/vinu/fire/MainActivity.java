package com.example.vinu.fire;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public FirebaseDatabase mfirebase;
    private DatabaseReference mdatabase;
    public   String userid;
    private ListView list1;
    public Button read;
    public EditText et1;
    public EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      getSupportActionBar().setTitle("Catch my bus");


        list1=(ListView)findViewById( R.id.listdata);
        mfirebase=FirebaseDatabase.getInstance();
        mdatabase=mfirebase.getReference("bus");
        read=(Button)findViewById(R.id.button5);
        //all declarations and initialisations

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Toast.makeText(getApplicationContext(),"datachange", Toast.LENGTH_SHORT).show();
                        showdata(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });





    }

    public void showdata(DataSnapshot dataSnapshot) {
        //Toast.makeText(getApplicationContext(),"datachange1111", Toast.LENGTH_SHORT).show();
        ArrayList<String> array=new ArrayList<>();
        for (DataSnapshot ds:dataSnapshot.getChildren())
        {

            userid=ds.getKey().toString();
            Toast.makeText(getApplicationContext(),userid, Toast.LENGTH_SHORT).show();

            Userinformation userinformation=ds.getValue(Userinformation.class);

            // ArrayList<String> array=new ArrayList<>();
           // array.add(a1);
             array.add(userinformation.getNo());
            //  array.add(user.getStart());

        }
        ArrayAdapter adpter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        list1.setAdapter(adpter);
    }
}

