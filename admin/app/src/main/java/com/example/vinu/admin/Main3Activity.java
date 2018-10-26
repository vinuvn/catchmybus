package com.example.vinu.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    Button r1;
    Button r2;
    Button r3;
    EditText r4;
    ListView r5;
    String routeid;
    String id;
    ArrayList<String> array=new ArrayList<>();
    // List<String> fruits_list ;= new ArrayList<String>(Arrays.asList(fruits));
    public FirebaseDatabase mfirebase;
    private DatabaseReference mdatabase;
    private DatabaseReference m11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        r1=(Button)findViewById(R.id.addroute);
        r2=(Button)findViewById(R.id.cancel);
        r3=(Button)findViewById(R.id.finish);
        r4=(EditText) findViewById(R.id.routename);
        r5=(ListView)findViewById( R.id.routelist);
        mfirebase=FirebaseDatabase.getInstance();
        mdatabase=mfirebase.getReference("route");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Toast.makeText(getApplicationContext(),"datachange", Toast.LENGTH_SHORT).show();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                     routeid = ds.getKey().toString();


                }
               // Toast.makeText(getApplicationContext(),routeid, Toast.LENGTH_SHORT).show();
               // r4.setText(routeid);
                int newid= Integer.parseInt(routeid);
                newid++;
                 id=String.valueOf(newid);
                 Toast.makeText(getApplicationContext(),id, Toast.LENGTH_SHORT).show();
                 //r4.setText(id);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array.add(r4.getText().toString());
                //fruits_list = new ArrayList<String>(Arrays.asList(array));

               // ArrayAdapter adpter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
               // r5.setAdapter(adpter);

            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len=array.size();
                for (int i=0;i<len;i++)

                {    int val=i+1;
                    String value=String.valueOf(val);
                    m11=mdatabase.child(id).child(array.get(i));
                    m11.setValue(value);
                }
            }
        });




    }
}
