package com.example.vinu.fire;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
//import java.util.HashMap;..

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public FirebaseDatabase mfirebase;
    private DatabaseReference mdatabase;
    private DatabaseReference rootdata;
    public   String userid;
    private ListView list1;
    public Button read;
    public EditText et1;
    public EditText et2;
    Spinner et3;
    Spinner et4;
    int a;
    ArrayList<String> a1=new ArrayList<>();
    ArrayList<String> a2=new ArrayList<>();
    String urouteid;
    ArrayList<String> array=new ArrayList<>();
    String c1,c2,c3,c4;
   // String copy[]=new String[]{};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      getSupportActionBar().setTitle("Catch my bus");


        list1=(ListView)findViewById( R.id.listdata);
        mfirebase=FirebaseDatabase.getInstance();
        mdatabase=mfirebase.getReference("bus");
        rootdata=mfirebase.getReference("route");
        read=(Button)findViewById(R.id.button5);
        et1=(EditText)findViewById(R.id.e1);
        et2=(EditText)findViewById(R.id.e2);
        et3=(Spinner)findViewById(R.id.e3);
        et4=(Spinner)findViewById(R.id.e4);




        //all declarations and initialisations

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1=et1.getText().toString();
                c2=et2.getText().toString();
                c3=et3.getSelectedItem().toString();
                c4=et4.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),c1, Toast.LENGTH_SHORT).show();
                rootdata.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot d1:dataSnapshot.getChildren())
                        {
                            urouteid=d1.getKey().toString();
                            int start=0,end=0;
                            int flag=0;
                           // Toast.makeText(getApplicationContext(),urouteid,Toast.LENGTH_SHORT).show();
                           // et1.setText(urouteid);
                           // String te=d1.child("1").getValue().toString();
                            long len=d1.getChildrenCount();
                           // String bb=String.valueOf(len);

                           // Toast.makeText(getApplicationContext(),bb,Toast.LENGTH_SHORT).show();

                            for (int i=1;i<=len;i++)
                            {
                                String stop=String.valueOf(i);
                                String te=d1.child(stop).getValue().toString();
                                if (te.equalsIgnoreCase(et1.getText().toString()))
                                {
                                    flag++;
                                    start=i;

                                }
                                if (te.equalsIgnoreCase(et2.getText().toString()))
                                {
                                    flag++;
                                    end=i;

                                }

                            }
                            if (flag==2)
                            {
                                if (end>start)
                                {
                                  //  Toast.makeText(getApplicationContext(),urouteid,Toast.LENGTH_SHORT).show();
                                    a2.add(urouteid);
                                }
                            }





                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
               if(c3.equalsIgnoreCase("12am to 10am"))
                    a=1;
                else if (c3.equalsIgnoreCase("10am to 6pm"))
                    a=2;
                else
                    a=3;


                mdatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        showdata(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });



        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),c2, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,Display.class);
                intent.putExtra("id",a1.get(position));
                intent.putExtra("userdest",c2);
                startActivity(intent);

            }
        });

    }

    public void showdata(DataSnapshot dataSnapshot) {

        String c1=et1.getText().toString();

        String c2=et2.getText().toString();
        String c3=et3.getSelectedItem().toString();
        String c4=et4.getSelectedItem().toString();

        for (DataSnapshot ds:dataSnapshot.getChildren())
        {

            userid=ds.getKey().toString();


            Userinformation userinformation=ds.getValue(Userinformation.class);


            String check1=userinformation.getType();

            String t2=userinformation.getTime().toString();
            int result=timecheck(t2);
           if (userinformation.getType().equalsIgnoreCase(c4) && result==a) {                   //checks for bus type
               if (a2.contains(userinformation.getR_id()))
               {
                   array.add(userinformation.getNo());
                   a1.add(userid);

               }
           }
            //  array.add(user.getStart());

        }
        ArrayAdapter adpter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        list1.setAdapter(adpter);
    }
    public  int timecheck(String t1)
    {
        Float newtime= Float.parseFloat(t1);
        int time=(int)newtime.intValue();
       // Toast.makeText(getApplicationContext(),t1, Toast.LENGTH_SHORT).show();
        if(time < 9|| time==24)
        {
            return  1;
        }
        else if (time< 18 && time > 9)
            return  2;
        else
            return  3;


    }
}

