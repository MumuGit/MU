package com.maziage.chaoshen.blockchain.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.maziage.chaoshen.blockchain.DataState;
import com.maziage.chaoshen.blockchain.EasyBaseActivity;
import com.maziage.chaoshen.blockchain.OnDataRequestFinishListener;
import com.maziage.R;
import com.maziage.chaoshen.blockchain.bean.BannerRes;
import com.maziage.chaoshen.blockchain.bean.HomeRes;
import com.maziage.chaoshen.blockchain.bean.ListRes;
import com.maziage.chaoshen.blockchain.bean.NoticeListRes;
import com.maziage.chaoshen.blockchain.bean.NoticeRes;
import com.maziage.chaoshen.blockchain.loader.EasyLoader;
import com.maziage.chaoshen.blockchain.notice.NoticeListActivity;
import com.maziage.chaoshen.blockchain.search.SearchActivity;
import com.maziage.chaoshen.blockchain.util.GlideImageLoader;


import com.maziage.chaoshen.blockchain.util.ScreenUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends EasyBaseActivity {
    private HomePresenter presenter;
    private Banner banner;
    private ConvenientBanner rec;
    private RecyclerView mRecyclerView;
    protected ListAdapter mAdapter;
    private LinearLayout search;
    private OnDataRequestFinishListener listener = new OnDataRequestFinishListener() {
        @Override
        public void OnDataRequestFinish(DataState dataState, Object data) {
            switch (dataState) {
                case SUCCESS:
                    fillData(data);
                    break;
                case FAIL:

                    break;
                case ERROR:


                    break;
                default:
            }
        }
    };

    private void fillData(Object data) {
        if (data != null && data instanceof HomeRes) {
            HomeRes res = (HomeRes) data;
            fillBanner(res.getBannerRes());
            fillRec(res.getRecListRes());
            fillNormal(res.getListRes());
            fillNotice(res.getNoticeRes());
        }
        if (data != null && data instanceof ListRes) {
            ListRes res = (ListRes) data;
            fillNormal(res);
            mAdapter.OnLoadComplete();

        }
        EasyLoader.stopLoading();

    }

    private void fillNoticeList(NoticeListRes noticeListRes) {

    }


    private void fillBanner(BannerRes data) {
        banner.setImages(data.data.list);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void fillRec(ListRes data) {

        rec.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.rec_layout;
            }
        }, dealResData(data.data.list));
        rec.startTurning();
    }

    private List<List> dealResData(List<ListRes.ListListItem> data) {
        List<List> result = new ArrayList();
        for (int i = 0; i < data.size(); i++) {
            if (i % 3 == 0) {
                List<ListRes.ListListItem> row = new ArrayList();
                result.add(row);
            }
            result.get(result.size() - 1).add(data.get(i));
        }
        return result;
    }

    private void fillNormal(ListRes data) {
        if (data != null && data.data.list instanceof List) {
            mAdapter.updateData(data.data.list);
        } else {
            mAdapter.updateData(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initImmersionBar();
        initView();
        initData();
    }

    private void initView() {
        mImmersionBar.statusBarDarkFont(true).init();
        banner = findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //设置图片集合
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);

        rec = findViewById(R.id.rec);
        mAdapter = new ListAdapter(this, true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        !recyclerView.canScrollVertically(1)) {
                    if (mAdapter.hasMoreData()) {
                        Map listParam = new HashMap();
                        listParam.put("flag", 33);
                        listParam.put("pageno", mAdapter.getDataSize() / 6 + 1 + "");
                        listParam.put("pagesize", "6");
                        presenter.getListData(listParam);
                    }
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        presenter = new HomePresenter(listener);
        Map recListParam = new HashMap();
        recListParam.put("flag", 37);
        Map listParam = new HashMap();
        listParam.put("flag", 33);
        listParam.put("pageno", "1");
        listParam.put("pagesize", "6");
        EasyLoader.showLoading(this);
        presenter.getHomeData(recListParam, listParam);
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getTopBarId() {
        return 0;
    }
}
