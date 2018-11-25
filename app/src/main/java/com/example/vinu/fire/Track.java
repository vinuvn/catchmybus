package com.example.vinu.fire;

import android.*;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.EnvironmentalReverb;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.jar.*;

public class Track extends AppCompatActivity {
    public FirebaseDatabase mfirebase;
    private DatabaseReference mdatabase,distloc;
    String s1, s2,s3,s4;
    Double dd1, dd2,dd3,dd4;
    Geocoder geocoder;
    List<Address> addresslist;
    LocationManager locationManager;
    Button b3;

    TextView t1,t2,t3;
    String userdest, r_id;
    String id;
   // LocationListener locationListener;
    ////////////////////////
    // String job_tag;;
    // private FirebaseJobDispatcher jobDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        mfirebase = FirebaseDatabase.getInstance();
        mdatabase = mfirebase.getReference("track");
        distloc = mfirebase.getReference("loc");
        geocoder = new Geocoder(Track.this, Locale.getDefault());
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3=(TextView) findViewById(R.id.textView3);
        b3=(Button)findViewById(R.id.button3) ;
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);


        Intent intent=getIntent();
          id=intent.getStringExtra("id");
          r_id=intent.getStringExtra("id2");
        userdest=intent.getStringExtra("userdest");

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d1:dataSnapshot.getChildren())
                {
                    if(d1.getKey().equalsIgnoreCase(id))
                    {
                        s1=d1.child("lat").getValue().toString();
                        s2=d1.child("long").getValue().toString();
                        dd1= Double.parseDouble(s1);
                        dd2=Double.parseDouble(s2);
                        //job_tag=d1.getKey();
                       // b_id=d1.getKey();
                        //        key from intent
                        try {
                            addresslist=geocoder.getFromLocation(dd1,dd2,1);
                            String addressStr = addresslist.get(0).getAddressLine(0);
                            Toast.makeText(getApplicationContext(),addressStr, Toast.LENGTH_SHORT).show();
                            t1.setText(addressStr);

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
       distloc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d2:dataSnapshot.getChildren())
                {
                    if (d2.getKey().equalsIgnoreCase(userdest))
                    {
                        s3=d2.child("lat").getValue().toString();
                        s4=d2.child("long").getValue().toString();
                        dd3= Double.parseDouble(s3);
                        dd4=Double.parseDouble(s4);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float a1,a2,a3,a4;
                a1=Float.parseFloat(s1);
                a2=Float.parseFloat(s2);
                a3=Float.parseFloat(s3);
                a4=Float.parseFloat(s4);

                float results[] = new float[10];
                Location.distanceBetween(dd1,dd2,dd3,dd4,results);
                Toast.makeText(getApplicationContext(), String.valueOf(results[0]), Toast.LENGTH_SHORT).show();
                float totdist= (float) (results[0]/1000.0);
                float time= (float) (totdist/40.0);
                t2.setText(totdist+" km");
                 t3.setText(time+"Hrs");

            }
        });




        //Intent intent1=new Intent(Track.this,Myservice.class);
        //intent.putExtra("id",s4);
        //jobDispatcher=new FirebaseJobDispatcher(new GooglePlayDriver(this));
//getLocation();
    }
  /*  void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, (LocationListener) this);
            Toast.makeText(getApplicationContext(),, Toast.LENGTH_SHORT).show();

        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }*/




    public  void startjob(View view)


{
    Intent i=new Intent(Track.this,Remainder.class);
    i.putExtra("id",id);
    i.putExtra("id2",r_id);
    i.putExtra("userdest",s3+" "+s4);

    startActivity(i);
   /* Job job=jobDispatcher.newJobBuilder().setService(Myservice.class).setLifetime(Lifetime.FOREVER).setRecurring(true).setTag(job_tag).setTrigger(Trigger.executionWindow(10,10)).setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL).setReplaceCurrent(false).setConstraints(Constraint.ON_ANY_NETWORK).build();
    jobDispatcher.mustSchedule(job);*/
}
public  void  stopjob(View view)
{
/*jobDispatcher.cancel(job_tag);
    Toast.makeText(getApplicationContext(),"cancelled", Toast.LENGTH_SHORT).show();*/
}
}
