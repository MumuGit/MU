package com.mu.example.mu;

import android.support.v4.view.ViewConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewConfiguration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test2();
    }
   private void test2(){
       println( "muq"+System.currentTimeMillis());
       println( "muq"+System.nanoTime());
   }
    private void test1() {
        Map<String,String> map=new HashMap<>();
        map.put("1","fdsfasd");
        map.put("2","fdsfasd2");
        Iterator it=map.entrySet().iterator();
        it.next();
        it.remove();
        printMap(map);
    }

    public void printMap(Map<String,String> map){
        for (Map.Entry<String,String> entry :map.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
    private void println(String s){
        System.out.println(s);
    }
}
