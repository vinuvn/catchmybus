package com.example.vinu.fire;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by vinu on 10/21/2018.
 */

public class Viewdata  extends AppCompatActivity{
    public FirebaseDatabase mfirebase;
    private DatabaseReference mdatabase;
    public   String userid;
    private ListView list1;
    public Button read;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdata_layout);
        getSupportActionBar().setTitle("Fetch my bus");
        list1=(ListView)findViewById( R.id.listdata);
        mfirebase=FirebaseDatabase.getInstance();
        mdatabase=mfirebase.getReference("bus");
        read=(Button)findViewById(R.id.button5);
        //Toast.makeText(getApplicationContext(),"datasnap", Toast.LENGTH_SHORT).show();
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
           Userinformation user=new Userinformation();
            userid=ds.getKey().toString();
            Toast.makeText(getApplicationContext(),userid, Toast.LENGTH_SHORT).show();
           // Toast.makeText(getApplicationContext(),userid, Toast.LENGTH_SHORT).show();
           // String a1=  ds.child(userid).child("start").getValue(String.class);
            Toast.makeText(getApplicationContext(),userid, Toast.LENGTH_SHORT).show();
          //**  user.setNo(ds.child(userid).getValue(com.example.vinu.fire.Userinformation.class).getNo());
          //**  user.setStart(ds.child(userid).getValue(com.example.vinu.fire.Userinformation.class).getStart());
           // Log.d(TAG,"name: "+user.getName());
            //Log.d(TAG,"name: "+user.getClasses());
            //Log.d()
           // ArrayList<String> array=new ArrayList<>();
            array.add(userid);
           // array.add(user.getNo());
          //  array.add(user.getStart());

        }
        ArrayAdapter adpter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        list1.setAdapter(adpter);
    }
}

