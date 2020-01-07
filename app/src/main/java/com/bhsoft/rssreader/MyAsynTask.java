package com.bhsoft.rssreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bhsoft.rssreader.model.Item;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAsynTask extends AsyncTask<Void, Void, Void> {
    Context context;
    String link;
    ListView lv;
    String chuoi;
    HashMap<String, Object> hm ;
   ArrayList<Item> items = new ArrayList<>();
    List<HashMap<String,Object>> ds = new ArrayList<>();
//    ArrayList<String> ds = new ArrayList<>();

    public MyAsynTask(Context context, String link, ListView lv) {
        this.context = context;
        this.link = link;
        this.lv = lv;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(link);
            URLConnection connection = url.openConnection();
            InputStream is = connection.getInputStream();
            items =(ArrayList<Item>) MySaxParser.xmlParser(is);
            for (int i = 0; i < items.size(); i++) {
             //   chuoi = "";
                hm = new HashMap<>();
                chuoi  += items.get(i).getTitle() + " ";
                chuoi += items.get(i).getPubDate() + "\n";
                hm.put("title",items.get(i).getTitle());
                hm.put("description",items.get(i).getDescription());
                hm.put("pubDate",items.get(i).getPubDate());
                ds.add(hm);
              //  ds.add(chuoi);
            }
            Log.d("dữ liệu", chuoi);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
//        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, ds);
//        lv.setAdapter(adapter);
        String[] from = {"title","description","pubdate"};
        int[] to = {R.id.tvTitle,R.id.tvDescription,R.id.tvpubDate};
        SimpleAdapter adapter = new SimpleAdapter(context,ds,R.layout.news_cell,from,to);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String link = items.get(position).getLink();
                Intent intent = new Intent(context,NewDetailActivity.class);
                intent.putExtra("link",link);
                ((Activity)context).startActivity(intent);
            }
        });


    }
}
