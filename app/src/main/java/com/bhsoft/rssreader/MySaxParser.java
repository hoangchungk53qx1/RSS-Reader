package com.bhsoft.rssreader;

import android.util.Log;

import com.bhsoft.rssreader.model.Item;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParserFactory;

public class MySaxParser {
    public static ArrayList<Item> xmlParser(InputStream is) {
        ArrayList<Item> items = null;
        try {
            XMLReader xmlreader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            MySaxHandler saxhandlder = new MySaxHandler();
            xmlreader.setContentHandler(saxhandlder);
            xmlreader.parse(new InputSource(is));
            items = saxhandlder.getItems();


        }
        catch (Exception e) {
            Log.d("loi", "khong lay duoc" + e.toString());
        }


        return items;
    }

}
