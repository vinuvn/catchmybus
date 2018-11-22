package com.example.vinu.fire;

import android.os.AsyncTask;
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

public class Myservice extends JobService {
BackgroundTask backgroundTask;

    public FirebaseDatabase mfirebase;
    private DatabaseReference mdatabase;
    String s3,s2;
    @Override
    public boolean onStartJob(final JobParameters job) {
        final String s1=job.getTag();
        mfirebase=FirebaseDatabase.getInstance();
        mdatabase=mfirebase.getReference("track");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d1:dataSnapshot.getChildren())
                {
                    if (d1.getKey().equalsIgnoreCase(s1))
                    {
                        s2=d1.child("lat").getValue().toString();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        backgroundTask=new BackgroundTask()
        {
            @Override
            protected void onPostExecute(String s) {
              //  super.onPostExecute(s);
                Toast.makeText(getApplicationContext(),s2, Toast.LENGTH_SHORT).show();
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
public static class BackgroundTask extends AsyncTask<Void,Void,String>
{

    @Override
    protected String doInBackground(Void... params) {

        return "haiiii";
    }
}




}

///////////////////////////////////////////////////////////////////////////