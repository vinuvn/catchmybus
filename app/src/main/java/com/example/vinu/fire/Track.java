package com.example.vinu.fire;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Track extends AppCompatActivity {
    public FirebaseDatabase mfirebase;
    private DatabaseReference mdatabase;
    String s1,s2;
    Double dd1,dd2;
    Geocoder geocoder;
    List<Address> addressList;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        mfirebase=FirebaseDatabase.getInstance();
        mdatabase=mfirebase.getReference("track");
        geocoder = new Geocoder(this, Locale.getDefault());
        t1=(TextView)findViewById(R.id.textView);

        Intent intent=getIntent();
        final String id=intent.getStringExtra("id");

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
                        Toast.makeText(getApplicationContext(),s1, Toast.LENGTH_SHORT).show();
                        try {

                            addressList = geocoder.getFromLocation(dd1,dd2,1);

                            String addressStr = addressList.get(0).getAddressLine(0);
                            String areaStr = addressList.get(0).getLocality();
                            String cityStr = addressList.get(0).getAdminArea();
                            String countryStr = addressList.get(0).getCountryName();
                            String postalcodeStr = addressList.get(0).getPostalCode();

                            String fullAddress = addressStr+", "+areaStr+", "+cityStr+", "+countryStr+", "+postalcodeStr;
                            t1.setText(addressStr+" "+areaStr);

                            Toast.makeText(getApplicationContext(),fullAddress, Toast.LENGTH_SHORT).show();
                            //result.setText(fullAddress);


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
