package com.naivor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

/**
 * recyclerView的viewholder基类
 * <p>
 * Created by tianlai on 16-7-16.
 */
public abstract class RecyHolder<T> extends RecyclerView.ViewHolder implements HolderOperator<T>,
        View.OnClickListener, View.OnLongClickListener, View.OnFocusChangeListener, CompoundButton.OnCheckedChangeListener {
    protected final String TAG = this.getClass().getSimpleName();

    protected T itemData;
    protected int position;

    protected Context context;

    protected RecyAdapter adapter;

    protected AdapterOperator.InnerListener<T> innerListener;

    public RecyHolder(View itemView) {
        super(itemView);
        this.context = itemView.getContext();
    }


    @Override
    public View getConvertView() {
        return itemView;
    }


    /**
     * 绑定数据
     *
     * @param itemData
     * @param position
     * @param operator
     */
    @Override
    public void bindData(AdapterOperator<T> operator, int position, T itemData) {
        this.itemData = itemData;
        this.position = position;

        if (operator instanceof RecyAdapter) {
            adapter = (RecyAdapter) operator;
        }

        if (adapter != null) {
            innerListener = adapter.getInnerListener();
        }
    }

    /**
     * 查找控件
     *
     * @param viewId
     * @return
     */
    @Override
    public View find(int viewId) {
        return itemView.findViewById(viewId);

    }

    /**
     * 查找控件
     *
     * @param viewId
     * @return
     */
    @Override
    public View find(View itemView, int viewId) {
        return itemView.findViewById(viewId);

    }

    @Override
    public void onClick(View v) {
        if (innerListener != null) {
            innerListener.onClick(v, itemData, position);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (innerListener != null) {
            innerListener.onLongClick(v, itemData, position);
        }
        return true;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (innerListener != null) {
            innerListener.onCheckedChanged(buttonView, isChecked, itemData, position);
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (innerListener != null) {
            innerListener.onFocusChange(v, hasFocus, itemData, position);
        }
    }

    /**
     * 注册Click事件
     */
    protected void registerClick(View v) {
        if (v == null) {
            throw new NullPointerException("View can't be null");
        }

        v.setOnClickListener(this);
    }
    /**
     * 注册LongClick事件
     */
    protected void registerLongClick(View v) {
        if (v == null) {
            throw new NullPointerException("View can't be null");
        }

        v.setOnLongClickListener(this);
    }

    /**
     * 注册Focus事件
     */
    protected void registerFocusChange(View v) {
        if (v == null) {
            throw new NullPointerException("View can't be null");
        }

        v.setOnFocusChangeListener(this);
    }

    /**
     * 注册Check事件
     */
    protected void registerCheckedChanged(CompoundButton v) {
        if (v == null) {
            throw new NullPointerException("View can't be null");
        }

        v.setOnCheckedChangeListener(this);
    }
}
