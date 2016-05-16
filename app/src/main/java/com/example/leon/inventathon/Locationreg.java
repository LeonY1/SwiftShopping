package com.example.leon.inventathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.HashSet;

/**
 * Created by leon on 5/14/16.
 */
public class Locationreg extends Activity{
    public final static String s = "locations.txt";
    public LatLng loc;
    public HashSet<product> prods;
    public Locationreg(){

    }
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        Button b2 = (Button) findViewById(R.id.button2);
        prods = new HashSet<>();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText) findViewById(R.id.edittext);
                EditText et2 = (EditText) findViewById(R.id.edittext2);
                String locat = et.getText().toString();
                String [] arr = locat.split(" ");
                if(locat.length() > 0)
                loc = new LatLng(Double.parseDouble(arr[0]),Double.parseDouble(arr[1]));
                String product = et2.getText().toString();
                String [] arr2 = product.split(" ");
                prods.add(new product(arr2[0],arr2[1],Integer.parseInt(arr2[2])));
                productdb.add(loc,prods);
                saveLocation();
                Intent i = new Intent(view.getContext(),Locationact.class);
                startActivity(i);
            }
        });
    }
    public void saveLocation(){
        try{
            BufferedReader in = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+s));
            productdb.add(loc,prods);
            String str = "'";
            PrintStream output = new PrintStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+s));
            HashSet<LatLng> set = (HashSet<LatLng>) productdb.map.keySet();
            for(LatLng l:set){
                output.println(l.latitude+" "+l.longitude+" "+productdb.map.get(l));
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
