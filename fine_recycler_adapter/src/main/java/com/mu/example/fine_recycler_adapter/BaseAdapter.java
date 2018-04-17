package com.mu.example.fine_recycler_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseAdapter<T, VH extends BaseHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mData;
    protected LayoutInflater mInflater;
    protected SparseIntArray mLayoutRes;
    protected HashMap mListeners;
    protected HashMap mParams;
    protected static final int DEFAULT_VIEW_TYPE = -0xff;
    protected static final int LOAD_MORE_VIEW_TYPE = 0x00000222;
    protected static final int HEADER_VIEW_TYPE = 0x00000111;
    public static String PARAM_COUNT = "PARAM_COUNT";
    public static String PARAM_LOAD_MORE_STATUS = "PARAM_LOAD_MORE_STATUS";
    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_END = 4;
    private int mLoadMoreStatus = STATUS_DEFAULT;
    public static final String DRAG_ENABLE_STATE = "DRAG_ENABLE_STATE";

    public static void setPageCount(int pageCount) {
        PAGE_COUNT = pageCount;
    }

    protected boolean hasLoadMore;

    public static int PAGE_COUNT = 10;

    public int getBottomLayoutCount() {
        return bottomLayoutCount;
    }

    private int bottomLayoutCount = 0;

    public int getHeaderLayoutCount() {
        return headerLayoutCount;
    }

    public void setHeaderLayoutCount(int headerLayoutCount) {
        this.headerLayoutCount = headerLayoutCount;
    }

    private int headerLayoutCount = 0;

    public BaseAdapter(Context context, boolean hasLoadMore) {
        mInflater = LayoutInflater.from(context);
        this.hasLoadMore = hasLoadMore;
        if (hasLoadMore) {
            addLayoutRes(LOAD_MORE_VIEW_TYPE, R.layout.item_load_more);
            bottomLayoutCount = 1;
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutRes.get(viewType), parent, false);
        VH baseHolder = null;
        switch (viewType) {
            case LOAD_MORE_VIEW_TYPE:
                baseHolder = (VH) new LoadMoreHolder(view);
                break;
            default:
                baseHolder = onCreateViewDefHolder(view, viewType);
        }
        return baseHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (hasLoadMore && position == getItemCount() - 1) {
            return LOAD_MORE_VIEW_TYPE;
        } else if (headerLayoutCount != 0 && position <= headerLayoutCount - 1) {
            return HEADER_VIEW_TYPE;
        }
        return getDefItemViewType(position);
    }

    protected abstract int getDefItemViewType(int position);

    public abstract VH onCreateViewDefHolder(View view, int viewType);

    public void addLayoutRes(int type, int res) {
        if (mLayoutRes == null) {
            mLayoutRes = new SparseIntArray();
        }
        mLayoutRes.append(type, res);
    }

    public void addLayoutRes(int res) {
        addLayoutRes(DEFAULT_VIEW_TYPE, res);
    }

    public void addListener(String name, Object listener) {
        if (mListeners == null) {
            mListeners = new LinkedHashMap();
        }
        mListeners.put(name, listener);
    }

    public void addParam(String name, Object param) {
        if (mParams == null) {
            mParams = new LinkedHashMap();
        }
        mParams.put(name, param);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (holder instanceof LoadMoreHolder) {
            holder.bind(null, position, mListeners, mParams);
        } else if (holder instanceof HeaderHolder) {
            holder.bind(null, position, mListeners, mParams);
        } else {
            onBindViewDefHolder(holder, position);
        }
    }

    public abstract void onBindViewDefHolder(VH holder, int position);

    public void updateData(List data) {
        if (mData == null) {
            mData = new LinkedList<>();
        }
        if (mData.size() == 0 && (data == null || data.size() == 0)) {
            setHeaderLayoutCount(0);
        }
        int prePosition = mData.size() + headerLayoutCount;
        if (data != null) {
            mData.addAll(data);
            notifyItemRangeChanged(prePosition, getItemCount() - prePosition);
        }
    }

    public boolean hasMoreData() {
        return getDataSize() > 0 && getDataSize() % PAGE_COUNT == 0;
    }

    public int getDataSize() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public void OnLoading() {
        if (getDataSize() >= PAGE_COUNT) {
            mLoadMoreStatus = STATUS_LOADING;
            addParam(PARAM_LOAD_MORE_STATUS, mLoadMoreStatus);
            notifyItemChanged(getItemCount() - 1);//Todo
        }

    }

    public void OnLoadFail() {
        mLoadMoreStatus = STATUS_FAIL;
        addParam(PARAM_LOAD_MORE_STATUS, mLoadMoreStatus);
        notifyItemChanged(getItemCount() - 1);
    }

    public void OnLoadEnd() {
        mLoadMoreStatus = STATUS_END;
        addParam(PARAM_LOAD_MORE_STATUS, mLoadMoreStatus);
        notifyItemChanged(getItemCount() - 1);

    }

    public void onLoadDefault() {
        mLoadMoreStatus = STATUS_DEFAULT;
        addParam(PARAM_LOAD_MORE_STATUS, mLoadMoreStatus);
        notifyItemChanged(getItemCount() - 1);


    }

    public void OnLoadComplete() {
        if (hasMoreData()) {
            onLoadDefault();
        } else {
            if (getDataSize() < PAGE_COUNT) {
                onLoadDefault();
            } else {
                OnLoadEnd();
            }

        }
    }


    public void onRefresh() {
        mData.clear();
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size() + getBottomLayoutCount() + getHeaderLayoutCount();
    }

    public void remove(int position) {
        int data_position = position - headerLayoutCount;
        if (data_position >= 0 && data_position < mData.size()) {
            mData.remove(data_position);
        }
        notifyDataSetChanged();
    }


    public void remove(List<Integer> positions) {
        if (mData != null && positions != null) {
            for (int i = positions.size() - 1; i >= 0; i--) {
                int index = positions.get(i);
                int data_position = index - headerLayoutCount;
                if (data_position >= 0 && data_position < mData.size()) {
                    mData.remove(data_position);
                }
            }
        }
        notifyDataSetChanged();
    }

}
