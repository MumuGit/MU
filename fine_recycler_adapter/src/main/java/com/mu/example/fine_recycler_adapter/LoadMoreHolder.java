package com.mu.example.fine_recycler_adapter;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ybslux.client.weight.recyclerloadmore.DataState;

import java.util.HashMap;

public class LoadMoreHolder extends BaseHolder<DataState> {
    private int mLoadMoreStatus = BaseAdapter.STATUS_DEFAULT;
    private LinearLayout load_more_loading_view;
    private FrameLayout load_more_load_fail_view;
    private FrameLayout load_more_load_end_view;
    public static final String OnRequestAgainListener = "OnRequestAgainListener";

    public LoadMoreHolder(View itemView) {
        super(itemView);
        load_more_loading_view = (LinearLayout) itemView.findViewById(R.id.load_more_loading_view);
        load_more_load_fail_view = (FrameLayout) itemView.findViewById(R.id.load_more_load_fail_view);
        load_more_load_end_view = (FrameLayout) itemView.findViewById(R.id.load_more_load_end_view);
    }

    @Override
    public void bind(DataState item, int position, final HashMap listeners, HashMap params) {
        if(params.get(BaseAdapter.PARAM_LOAD_MORE_STATUS)!=null){
            mLoadMoreStatus = (int) params.get(BaseAdapter.PARAM_LOAD_MORE_STATUS);
        }
        load_more_load_fail_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listeners.get(OnRequestAgainListener) != null) {
                    OnRequestAgainListener onRequestAgainListener =
                            (OnRequestAgainListener) listeners.get(OnRequestAgainListener);
                    onRequestAgainListener.OnRequestAgain();
                }
            }
        });

        updateLoadMoreShow();
    }

    private void updateLoadMoreShow() {
        switch (mLoadMoreStatus) {
            case BaseAdapter.STATUS_LOADING:
                load_more_loading_view.setVisibility(View.VISIBLE);
                load_more_load_fail_view.setVisibility(View.INVISIBLE);
                load_more_load_end_view.setVisibility(View.INVISIBLE);
                break;
            case BaseAdapter.STATUS_FAIL:
                load_more_loading_view.setVisibility(View.INVISIBLE);
                load_more_load_fail_view.setVisibility(View.VISIBLE);
                load_more_load_end_view.setVisibility(View.INVISIBLE);
                break;
            case BaseAdapter.STATUS_END:
                load_more_loading_view.setVisibility(View.INVISIBLE);
                load_more_load_fail_view.setVisibility(View.INVISIBLE);
                load_more_load_end_view.setVisibility(View.VISIBLE);
                break;
            case BaseAdapter.STATUS_DEFAULT:
                load_more_loading_view.setVisibility(View.INVISIBLE);
                load_more_load_fail_view.setVisibility(View.INVISIBLE);
                load_more_load_end_view.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public interface OnRequestAgainListener {
        void OnRequestAgain();
    }
}
