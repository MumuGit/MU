package com.mu.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.mu.example.myapplication.core.ui.slidetab.SlideTab;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SlideTab tabView;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private List<String> strings;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, SignInActivity.class));
        initView();
    }

    private void initView() {
        tabView = (SlideTab) findViewById(R.id.slideTab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        strings = new ArrayList<>();
        list = new ArrayList<>();

        list.add(new Fragment_1());
        list.add(new Fragment_2());
        list.add(new Fragment_3());
        list.add(new Fragment_4());
        list.add(new Fragment_5());
        adapter = new PagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        strings.add("TAB_1");
        strings.add("TAB_2");
        strings.add("TAB_3");
        strings.add("TAB_4");
        strings.add("TAB_5");
        tabView.initTab(strings, viewPager);
    }
}
