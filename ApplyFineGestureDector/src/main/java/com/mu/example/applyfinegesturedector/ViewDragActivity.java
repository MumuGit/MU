package com.mu.example.applyfinegesturedector;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class ViewDragActivity extends AppCompatActivity {

    protected static final String TAG = "MyButton";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drag);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(ViewDragActivity.this, "button", Toast.LENGTH_SHORT).show();
//            }
//        });
//        button.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                System.out.println("sss1");
//                return false;
//            }
//        });

    }

}
