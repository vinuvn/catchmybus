package com.example.vinu.fire;

import android.app.NotificationManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by vinu on 11/22/2018.
 */

public class Myservice extends JobService
{

BackgroundTask backgroundTask;
    public FirebaseDatabase mfirebase;

    public DatabaseReference mdatabase,distloc;
    NotificationCompat.Builder nb;
    NotificationManager nm;


    String s1,s4,s3,s2;
    Double dd1,dd2,dd3,dd4;
    String[] separated;
    LocationManager locationManager;
    @Override
    public boolean onStartJob(final JobParameters job) {
        mfirebase=FirebaseDatabase.getInstance();

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        nb = new NotificationCompat.Builder(Myservice.this).setDefaults(NotificationCompat.DEFAULT_ALL);
        final String a1=job.getTag();
        separated = a1.split(" ");
        dd3=Double.parseDouble(separated[1]);
        dd4=Double.parseDouble(separated[2]);


        //distloc = mfirebase.getReference("loc");
        //String currentString = "Fruit: they taste good";

        //separated[0]; // this will contain "Fruit"
        //separated[1];




        backgroundTask=new BackgroundTask()

        {


            @Override
            protected void onPostExecute(String s) {
                float results[] = new float[10];
               // Location.distanceBetween(dd1,dd2,dd3,dd4,results);
                //if (results[0]<=3000)
               // {}


               // ad();

              /*  if (s.equalsIgnoreCase("no"))
                {
                    nb.setContentText(s1);
                    nb.setContentTitle(s2);
                    nb.setSmallIcon(R.mipmap.ic_launcher);
                    nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(1, nb.build());
                }
                else {

                    nb.setContentText(separated[0]);
                    nb.setContentTitle(separated[1]);
                    nb.setSmallIcon(R.mipmap.ic_launcher);
                    nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(1, nb.build());


                }*/
              //  super.onPostExecute(s);
               // Toast.makeText(getApplicationContext(),s1, Toast.LENGTH_SHORT).show();
                jobFinished(job,false);

            }
        };
        backgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }
public class BackgroundTask extends AsyncTask<Void,Void,String>
{

    @Override
    protected String doInBackground(Void... params) {


            mdatabase = mfirebase.getReference("track");
            mdatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot d1 : dataSnapshot.getChildren()) {
                        if (d1.getKey().equalsIgnoreCase(separated[0])) {
                            s1 = d1.child("lat").getValue().toString();
                            s2 = d1.child("long").getValue().toString();
                            dd1 = Double.parseDouble(s1);
                            dd2 = Double.parseDouble(s2);
                            //Toast.makeText(getApplicationContext(), s1+s2, Toast.LENGTH_SHORT).show();

                            float results[] = new float[10];
                            Location.distanceBetween(dd1,dd2,dd3,dd4,results);
                            if (results[0]<=3000)
                            {
                                nb.setContentText("too close");
                                nb.setContentTitle("time up");
                                nb.setSmallIcon(R.mipmap.ic_launcher);
                                nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(1, nb.build());
                            }
                            else
                            {
                                nb.setContentText("wait");
                                nb.setContentTitle("rest");
                                nb.setSmallIcon(R.mipmap.ic_launcher);
                                nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                                nm.notify(1, nb.build());

                            }




                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



           /* distloc = mfirebase.getReference("loc");

            distloc.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot d2 : dataSnapshot.getChildren()) {
                        if (d2.getKey().equalsIgnoreCase(separated[1])) {
                            s3 = d2.child("lat").getValue().toString();
                            s4 = d2.child("long").getValue().toString();
                            dd3 = Double.parseDouble(s3);
                            dd4 = Double.parseDouble(s4);
                            Toast.makeText(getApplicationContext(), s1+s2, Toast.LENGTH_SHORT).show();
                            nb.setContentText(s1);
                           nb.setContentTitle(s3);
                            nb.setSmallIcon(R.mipmap.ic_launcher);
                            nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            nm.notify(1, nb.build());

                        }

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
*/



        /*float results[] = new float[10];
        Location.distanceBetween(dd1,dd2,dd3,dd4,results);
        if (results[0]<=3000)
        {
            return "you are 3 kms away from your destination";
        }

        else{return "no";}*/

       return  "no";



    }
}

/*public void ad()
{
    Toast.makeText(getApplicationContext(), s1+s2, Toast.LENGTH_SHORT).show();

}*/


}

///////////////////////////////////////////////////////////////////////////