package com.example.admin.w2d4fileandasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.admin.w2d4fileandasynctask.R.id.firstBar;

/**
 * Created by Luis Aguirre on 9/6/2017.
 */

public class TestAsyncTask extends AsyncTask<Integer,Integer, Integer> {

    private ProgressBar progressBar;
    public TestAsyncTask(ProgressBar progressBar)
    {
        this.progressBar = progressBar;
    }

    public static final String TAG = "AsyncTaskTag";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setProgress(0);
        Log.d(TAG, "onPreExecute: " + Thread.currentThread());
    }

    @Override
    protected Integer doInBackground(Integer... strings) {
        Log.d(TAG, "doInBackground: " + Thread.currentThread());
        Log.d(TAG, "doInBackground: " + strings[0]);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 150; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            publishProgress(i);

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG, "onProgressUpdate: "+ values[0 ]+ " " + Thread.currentThread());
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Integer s) {
        Log.d(TAG, "onPostExecute: " + Thread.currentThread());
        super.onPostExecute(s);
    }
}
