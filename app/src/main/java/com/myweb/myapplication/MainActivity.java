package com.myweb.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;



import java.util.List;

public class MainActivity extends AppCompatActivity {
    private  Button page2;

    private EditText txtStdid;
    private EditText txtStdName;
    private EditText txtStdTel;
    private EditText txtStdEmail;
    private EditText genae;

    private Button btnSave;
    private Button btnShow;
    private ListView dataView;
    private ListView clickView;
    private MySQLConnect mySQLConnect;
    private List<String> items;
    private ArrayAdapter<String> adt;
    private String dataStdId = null;
    private String dataStdName = null;
    private String dataStdTel = null;
    private String dataStdEmail = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        update();

        clickView = findViewById(R.id.dataView);
        page2 = findViewById(R.id.page2);
        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowDataActivity.class);

                startActivity(intent);

            }
        });
        clickView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {

                String selectedFromList = (clickView.getItemAtPosition(pos).toString() + " ");
                int indextel;
                String caltel = "";
                dataStdId = selectedFromList.substring(0,11);
                caltel = selectedFromList.substring(12);
                indextel = caltel.indexOf("0");
                dataStdName = caltel.substring(0,indextel-1);
                dataStdTel = caltel.substring(indextel,indextel+10);
                dataStdEmail = caltel.substring(indextel+11);

                return true;
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySQLConnect.sentData(txtStdid.getText().toString(),txtStdName.getText().toString(),txtStdTel.getText().toString(),txtStdEmail.getText().toString(),genae.getText().toString());
                items.add(txtStdid.getText().toString()+"\n"+txtStdName.getText().toString()+"\n"+txtStdTel.getText().toString()+"\n"+txtStdEmail.getText().toString()+"\n"+genae.getText().toString());
                adt.notifyDataSetChanged();
                txtStdid.setText("");
                txtStdName.setText("");
                txtStdTel.setText("");
                txtStdEmail.setText("");
                genae.setText("");

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkList();
            }
        });
    }


    public void update(){
        items = mySQLConnect.getData();
        adt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        dataView.setAdapter(adt);
    }

    public void checkList(){
        Intent refresh = new Intent(MainActivity.this,MainActivity.class);
        startActivity(refresh);
        finish();
    }
    public void init(){
        txtStdid = (EditText)findViewById(R.id.txtId);
        txtStdName = findViewById(R.id.txtName);
        txtStdTel = findViewById(R.id.txtTel);
        txtStdEmail = findViewById(R.id.txtEmail);
        genae = findViewById(R.id.genae);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        dataView = (ListView)findViewById(R.id.dataView);
        mySQLConnect = new MySQLConnect(MainActivity.this);
    }
}
