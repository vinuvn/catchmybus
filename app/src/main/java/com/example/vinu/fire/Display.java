package com.example.vinu.fire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Display extends AppCompatActivity {
    public FirebaseDatabase mfirebase;
    private  DatabaseReference track1;
    private DatabaseReference mdatabase;
    EditText e1,e2,e3;
    String s1,s2,s3,s4,tr1,tr2;
    ImageButton i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent=getIntent();
        final String id=intent.getStringExtra("id");
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();
        mfirebase=FirebaseDatabase.getInstance();
        mdatabase=mfirebase.getReference("bus");
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText3);
        e3=(EditText)findViewById(R.id.editText4);
        i1=(ImageButton)findViewById(R.id.imageButton4);
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d1:dataSnapshot.getChildren())
                {
                    if(d1.getKey().equalsIgnoreCase(id))
                    {
                        s1=d1.child("start").getValue().toString();
                        s2=d1.child("stop").getValue().toString();
                        s3=d1.child("type").getValue().toString();
                        s4=d1.child("no").getValue().toString();

                        break;
                    }

                }
                e1.setText(s1);
                e2.setText(s2);
                e3.setText(s3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
i1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       /* track1=mfirebase.getReference("track");
        track1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d1:dataSnapshot.getChildren())
                {
                    if(d1.getKey().equalsIgnoreCase(id))
                    {
                        tr1=d1.child("lat").getValue().toString();
                        tr2=d1.child("long").getValue().toString();

                        //Toast.makeText(getApplicationContext(),s1, Toast.LENGTH_SHORT).show();

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        Intent intent=new Intent(Display.this,Track.class);
        intent.putExtra("id",s4);
       // intent.putExtra("l1",tr1);
        //intent.putExtra("l2",tr2);


        startActivity(intent);
    }
});

    }
}
