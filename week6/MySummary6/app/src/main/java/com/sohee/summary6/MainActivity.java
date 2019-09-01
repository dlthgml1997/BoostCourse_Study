package com.sohee.summary6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDBAndCreateTable();
    }

    private void openDBAndCreateTable() {
        DBHelper.openDatabase(getApplicationContext(),"movie");
        DBHelper.createTable("outline");
    }
}
