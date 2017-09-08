package com.example.admin.w2d4fileandasynctask;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;


import java.util.List;

import static com.example.admin.w2d4fileandasynctask.R.id.tv;

public class MainActivity extends AppCompatActivity {

    EditText tvName_id;
    EditText tvLastName_id;
    EditText tvAge_id;
    EditText tvPhone_id;
    EditText tvId_id;

    private PopupWindow mPopupWindow;
    private LinearLayout mLinearLayout;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateValues();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createDataInfo(View view) {
        createInDB();
        cleanValues();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void readFromDB(View view) {
        DAO dao = new DAO(this);
        List<String> persons;
        persons = dao.Read(tvId_id.getText().toString());
        Toast.makeText(this, "Information is presented in Logd", Toast.LENGTH_SHORT).show();

        StringBuilder result = new StringBuilder("");

        int i = 0;
        for (String s:persons) {
            result.append(i);
            result.append(": ");
            result.append(s);
            result.append(Html.fromHtml("<br/>"));
            i++;
        }


        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.custom_layout,null);
        TextView tv = (TextView) customView.findViewById(R.id.tv);
        tv.setText(result.toString());
        mLinearLayout = (LinearLayout) findViewById(R.id.Linear_layoutId);

        mPopupWindow = new PopupWindow(
                customView,
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
        );

        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.showAtLocation(mLinearLayout, Gravity.CENTER,0,0);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void updateFromDB(View view) {
        String id = tvId_id.getText().toString();
        String name = tvName_id.getText().toString();
        String lastName = tvLastName_id.getText().toString();
        String age = tvAge_id.getText().toString();
        String phone = tvPhone_id.getText().toString();


        updateDataInfo(id, name, lastName, age, phone);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void deleteFromDB(View view) {
        String id = tvId_id.getText().toString();
        dropDataInfo(id);
    }

    public void populateValues(){
        tvId_id = (EditText) findViewById(R.id.tvId_id);
        tvName_id = (EditText) findViewById(R.id.tvName_id);
        tvLastName_id = (EditText) findViewById(R.id.tvLastName_id);
        tvAge_id = (EditText) findViewById(R.id.tvAge_id);
        tvPhone_id = (EditText) findViewById(R.id.tvPhone_id);

    }

    public void cleanValues(){
        tvId_id.setText("");
        tvName_id.setText("");
        tvLastName_id.setText("");
        tvAge_id.setText("");
        tvPhone_id.setText("");

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void updateDataInfo(String id, String name, String lastName, String age, String phone) {
        DAO dao = new DAO(this);
        int result = dao.Update(id, name, lastName, age, phone);
        Toast.makeText(this, "Updated record : " + result, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void dropDataInfo(String id) {
        DAO dao = new DAO(this);
        int result = dao.Drop(id);
        Toast.makeText(this, "Deleted record : " + result, Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createInDB() {

        DAO dao = new DAO(this);
        long result = dao.Create(tvName_id.getText().toString(),
                tvLastName_id.getText().toString(),
                tvAge_id.getText().toString(),
                tvPhone_id.getText().toString());

        Toast.makeText(this, "Inserted in row: " + result, Toast.LENGTH_SHORT).show();
    }



/*    public void goToFile(View view) {
        Intent intent = new Intent(this, FileActivity.class);
        startActivity(intent);
    }

    public void AsyncTask(View view) {

        Intent intent = new Intent(this, MultipleAsyncTaskActivity.class);
        startActivity(intent);

    }*/
}
