package com.example.vinu.fire;

import android.os.AsyncTask;
import android.widget.Toast;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by vinu on 11/22/2018.
 */

public class Myservice extends JobService {
BackgroundTask backgroundTask;
    String s1,s2;
    @Override
    public boolean onStartJob(final JobParameters job) {
        final String s1=job.getTag();
        backgroundTask=new BackgroundTask()
        {
            @Override
            protected void onPostExecute(String s) {
              //  super.onPostExecute(s);
                Toast.makeText(getApplicationContext(),s1, Toast.LENGTH_SHORT).show();
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