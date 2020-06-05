package com.example.task2;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button btnviewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdata);
        myDb = new DatabaseHelper(this);
        viewAll();
    }
    public void viewAll() {

        ListView listView = (ListView) findViewById(R.id.listview);
        myDb = new DatabaseHelper(this);
        //populate an List<HashMap<String,String>> from the database and then view it
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            // show message
            showMessage("Error","Nothing found");
            return;
        }else{
            while(res.moveToNext()){
                HashMap<String, String> hm = new HashMap<String,String>();
                hm.put("txt1","ID : " + res.getString(0));
                hm.put("txt2","Name : " + res.getString(1));
                hm.put("txt3","Age : " + res.getString(2));
                aList.add(hm);
                String[] from = { "txt1","txt2","txt3" };
                // Ids of views in listlayout
                int[] to = { R.id.textView1,R.id.textView2,R.id.textView3};
                SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList , R.layout.listlayout, from, to);
                listView.setAdapter(adapter);
            }
        }


    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
