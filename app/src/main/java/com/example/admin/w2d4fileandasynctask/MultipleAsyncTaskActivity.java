package com.example.admin.w2d4fileandasynctask;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleAsyncTaskActivity extends AppCompatActivity {

    private ProgressBar firstBar = null;
    private ProgressBar secondBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_async_task);

        firstBar = (ProgressBar)findViewById(R.id.firstBar);
        firstBar.setVisibility(View.VISIBLE);
        firstBar.setMax(150);

        secondBar = (ProgressBar)findViewById(R.id.SecondBar);
        secondBar.setVisibility(View.VISIBLE);
        secondBar.setMax(150);

        TestAsyncTask testAsyncTask1 = new TestAsyncTask(firstBar);
        TestAsyncTask testAsyncTask2 = new TestAsyncTask(secondBar);
        //testAsyncTask1.execute(0);
        //testAsyncTask2.execute(0);
        StartAsyncTaskInParallel(testAsyncTask1);
        StartAsyncTaskInParallel(testAsyncTask2);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInParallel(TestAsyncTask task) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Log.d("TAG", " GREATER THAN HONEYCOMB");
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0);
        }
        else {
            Log.d("TAG", " LESS THAN HONEYCOMB");
            task.execute();
        }
    }


}
