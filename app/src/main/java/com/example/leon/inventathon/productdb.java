package com.example.leon.inventathon;

import com.google.android.gms.maps.model.LatLng;

import java.util.*;

/**
 * Created by leon on 5/14/16.
 */
public class productdb {
    public static HashMap<LatLng,HashSet<product>> map = new HashMap<LatLng, HashSet<product>>();
    public static HashMap<product,HashSet<LatLng>> map2 = new HashMap<product,HashSet<LatLng>>();
    public static void add(LatLng a, HashSet<product> list){
        if(!map.containsKey(a))
            map.put(a,list);
        for(product p:list) {
            if (map2.containsKey(p))
                map2.get(p).add(a);
            else{
                map2.put(p, new HashSet<LatLng>());
                map2.get(p).add(a);
            }
        }
    }
    public static ArrayList<LatLng> find(String s){
        return find(s,new LatLng(0,0));
    }
    public static ArrayList<LatLng> find(String s, LatLng origloc){
        product pro = null;
        for(product p:map2.keySet()){
            if(p.name.indexOf(s)!=-1||p.productno.indexOf(s)!=-1){
                pro = p;
                break;
            }
        }
        ArrayList<LatLng> list = new ArrayList<>();
        for (LatLng l:map2.get(pro)){
            if(Math.abs(l.latitude-origloc.latitude)<.5 && Math.abs(l.longitude-origloc.longitude)<.5)
                list.add(l);
        }
        Comparator<LatLng> c = new Comparator<LatLng>() {
            @Override
            public int compare(LatLng t0, LatLng t1) {
                double dist = Math.sqrt(Math.pow(t0.latitude,2)+Math.pow(t0.longitude,2));
                double dist2= Math.sqrt(Math.pow(t1.latitude,2)+Math.pow(t1.longitude,2));
                return (int)(dist-dist2);
            }
        };
        Collections.sort(list,c);
        return list;
    }
}
