package com.example.vinu.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class Main2Activity extends AppCompatActivity {
    public Button save,test,receive;
    public EditText e1;
    public EditText e2;
    public EditText e3;
    public EditText e4;
    public EditText e9;
    public EditText e10;
    RadioButton radioButton;
    RadioGroup radioGroup;
    String ampm;


    public FirebaseDatabase mfirebase=FirebaseDatabase.getInstance();
    private DatabaseReference mdatabase=mfirebase.getReference("bus");
    // private   DatabaseReference m1;//=mdatabase.child("new");
    private DatabaseReference m11;//=m1.child("name");
    private    DatabaseReference m12;
    private    DatabaseReference m13;
    private    DatabaseReference m14;
    private    DatabaseReference m15;//=m1.child("class");
    private DatabaseReference m16;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle("Catch my bus");
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e9=(EditText)findViewById(R.id.editText9);
        e10=(EditText)findViewById(R.id.routeid);
        save=(Button)findViewById(R.id.button2);
        receive=(Button)findViewById(R.id.button3);
        test=(Button)findViewById(R.id.readdata);
        radioGroup=(RadioGroup)findViewById(R.id.rgroup);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                String s3=e3.getText().toString();
                String s4=e4.getText().toString();
                String s9=e9.getText().toString();
                String s10=e10.getText().toString();
                Float newtime= Float.parseFloat(s4);
                if(ampm.equalsIgnoreCase("pm"))
                {
                    if (newtime!=12)
                        newtime=newtime+12;
                }
                s4=String.valueOf(newtime);

                // m1=mdatabase.child(s3);
                String id= mdatabase.push().getKey();
                m11=mdatabase.child(id).child("no");
                m12=mdatabase.child(id).child("start");
                m13=mdatabase.child(id).child("stop");
                m14=mdatabase.child(id).child("time");
                m15=mdatabase.child(id).child("type");
                m16=mdatabase.child(id).child("r_id");
                // m1.setValue("best student");
                m11.setValue(s1);
                m12.setValue(s2);
                m13.setValue(s3);
                m14.setValue(s4);
                m15.setValue(s9);
                m16.setValue(s10);
                // mdatabase.addValueEventListener(new V)



            }
        });

        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast.makeText(getApplicationContext(), "und", Toast.LENGTH_SHORT).show();
                        if(dataSnapshot.getValue()!=null)
                        { Toast.makeText(getApplicationContext(), "und", Toast.LENGTH_SHORT).show();
                            String c1 =dataSnapshot.child("class").getValue().toString();
                            String c2 =dataSnapshot.child("name").getValue().toString();

                            e1.setText(c1);
                            e2.setText(c2);
                            Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
                        }



                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
    public void oncheck(View view)
    {
    int radioid=radioGroup.getCheckedRadioButtonId();
        radioButton= (RadioButton) findViewById(radioid);
        ampm=radioButton.getText().toString();
        Toast.makeText(getApplicationContext(),ampm, Toast.LENGTH_SHORT).show();

    }
}
