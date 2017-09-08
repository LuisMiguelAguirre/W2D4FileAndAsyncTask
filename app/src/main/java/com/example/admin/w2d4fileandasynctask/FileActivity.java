package com.example.admin.w2d4fileandasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.admin.w2d4fileandasynctask.R.id.text;
import static com.example.admin.w2d4fileandasynctask.R.id.tvAge_id;
import static com.example.admin.w2d4fileandasynctask.R.id.tvId_id;
import static com.example.admin.w2d4fileandasynctask.R.id.tvLastName_id;
import static com.example.admin.w2d4fileandasynctask.R.id.tvName_id;
import static com.example.admin.w2d4fileandasynctask.R.id.tvPhone_id;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
    }

    public void readFromFile(View view) {
        DFO dfo = new DFO();
        String result = dfo.readFromFile(this);

        TextView textView = (TextView) findViewById(R.id.tvFileContent);
        textView.setText(result);
        Log.d("TAG", "readFromFile: " + result);
        Toast.makeText(this, "Reading from file", Toast.LENGTH_SHORT).show();
    }

    public void writeToFromFile(View view) {
        DFO dfo = new DFO();
        TextView textView = (TextView) findViewById(R.id.tvIdFile_id);
        dfo.writeToFile(textView.getText().toString(), this);
        Log.d("TAG", "writeToFromFile: " + textView.getText().toString());
        Toast.makeText(this, "Writing to file", Toast.LENGTH_SHORT).show();
    }
}
