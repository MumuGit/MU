package com.mu.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mTabFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    private void initTabFilter(View view) {
//        mTabFilter = (RecyclerView) view.findViewById(R.id.tab_filter);
//        mTabFilter.setLayoutManager(new GridLayoutManager(getContext(), 1,
//                OrientationHelper.HORIZONTAL, false));
//        List<String> data = new ArrayList<>();
//        data.add("综合");
//        data.add("销量");
//        data.add("上新");
//        data.add("价格");
//        AscDescAdapter adapter = new AscDescAdapter(getContext(), data, R.layout.item_asc_desc_button);
//        mTabFilter.setAdapter(adapter);
//    }

}
