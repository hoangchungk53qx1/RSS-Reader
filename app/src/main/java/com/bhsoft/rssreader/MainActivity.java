package com.bhsoft.rssreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    String link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        link = "https://vnexpress.net/rss/suc-khoe.rss";
        lv = findViewById(R.id.lvNews);
        MyAsynTask myAsynTask = new MyAsynTask(MainActivity.this,link,lv);
        myAsynTask.execute();
        // Listview setOnItem




    }
}
