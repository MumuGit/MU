package com.mu.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by mu on 2018/1/9.
 */

public abstract class BaseAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<T> mData;
    protected Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mRes;

    public BaseAdapter(Context context, List<T> data, int res) {
        this.mData = data;
        this.mContext = context;
        mRes = res;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(mRes, parent, false);
        Object holder = null;
        try {
            holder = ((Class) ((ParameterizedType) this.getClass().
                    getGenericSuperclass()).getActualTypeArguments()[1]).
                    getDeclaredConstructor(new Class[]{View.class}).newInstance(view);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return (VH) holder;
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }
}
