package com.example.admin.w2d4fileandasynctask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
    }

    public void goToCRUD(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goToFile(View view) {
        Intent intent = new Intent(this, FileActivity.class);
        startActivity(intent);
    }

    public void AsyncTask(View view) {

        Intent intent = new Intent(this, MultipleAsyncTaskActivity.class);
        startActivity(intent);

    }
}
