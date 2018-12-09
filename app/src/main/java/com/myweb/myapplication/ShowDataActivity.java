package com.myweb.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class ShowDataActivity extends AppCompatActivity {
    private ListView dataView;
    private MySQLConnect mySQLConnect;
    private List<String> items;
    private ArrayAdapter<String> adt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        init();
        update();

    }
    public void update(){
        items = mySQLConnect.getData();
        adt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        dataView.setAdapter(adt);
    }

    public void checkList(){
        Intent refresh = new Intent(ShowDataActivity.this,ShowDataActivity.class);
        startActivity(refresh);
        finish();
    }
    public void init(){

        dataView = (ListView)findViewById(R.id.dataView);
        mySQLConnect = new MySQLConnect(ShowDataActivity.this);
    }

}
