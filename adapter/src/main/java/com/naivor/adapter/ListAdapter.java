package com.naivor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * absListview的适配器基类
 * <p>
 * Created by naivor on 16-4-12.
 */
public abstract class ListAdapter<T> extends BaseAdapter implements AdapterOperator<T> {
    protected final String TAG = this.getClass().getSimpleName();

    protected Context context;
    protected LayoutInflater inflater;

    protected List<T> itemDatas;

    private ListHolder<T> viewHolder;

    protected AdapterOperator.InnerClickListener innerClickListener;

    public ListAdapter(Context context) {
        this(context, LayoutInflater.from(context));
    }

    public ListAdapter(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;

        itemDatas = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return itemDatas.size();
    }

    @Override
    public T getItem(int position) {
        if (position < getCount()) {
            return itemDatas.get(position);
        }

        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolder = onCreateViewHolder(parent, getItemViewType(position));
            convertView = viewHolder.getConvertView();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListHolder<T>) convertView.getTag();
        }

        onBindViewHolder(viewHolder, position);

        return convertView;
    }

    /**
     * 通过重写此方法,不用设置数据,更改getitemcount的值就可以看到效果
     *
     * @param viewHolder
     * @param position
     */
    public void onBindViewHolder(ListHolder<T> viewHolder, int position) {
        viewHolder.bindData(this, position, getItem(position));
    }

    /**
     * 创建viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract ListHolder<T> onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public View createView(ViewGroup parent, int viewType) {
        return inflater.inflate(getLayoutRes(viewType), parent, false);
    }

    /**
     * 判断数据是否为空
     */
    @Override
    public boolean isEmpty() {
        return itemDatas.isEmpty();
    }

    /**
     * 添加数据
     */
    @Override
    public void addItems(List<T> list) {
        if (list != null) {
            itemDatas.addAll(list);
            notifyDataSetChanged();
        }
    }

    /**
     * 替换数据
     *
     * @param originItem
     * @param newItem
     */
    @Override
    public void replaceItem(T originItem, T newItem) {
        if (itemDatas.contains(originItem)) {
            int position = itemDatas.indexOf(originItem);
            itemDatas.set(position, newItem);

            notifyDataSetChanged();
        }
    }

    /**
     * 替换数据
     *
     * @param position
     * @param newItem
     */
    @Override
    public void replaceItem(int position, T newItem) {
        if (position < getCount() && position > -1) {
            itemDatas.set(position, newItem);

            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据
     */
    @Override
    public void addItems(int position, List<T> list) {
        if (list != null) {
            itemDatas.addAll(position, list);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加单个数据
     */
    @Override
    public void addItem(T item) {
        if (item != null) {
            itemDatas.add(item);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加单个数据
     */
    @Override
    public void addItem(int position, T item) {
        if (item != null && position >= 0) {
            itemDatas.add(position, item);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除数据
     */
    @Override
    public void removeItem(int position) {
        if (position >= 0 && position < getCount()) {

            itemDatas.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除数据
     */
    @Override
    public void removeItem(T data) {
        if (data != null && itemDatas.contains(data)) {

            itemDatas.remove(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 设置新数据，原来的清空
     */
    @Override
    public void setItems(List<T> list) {
        itemDatas.clear();

        if (list != null) {
            itemDatas.addAll(list);

            notifyDataSetChanged();
        }

    }

    /**
     * 清空
     */
    @Override
    public void clearItems() {
        if (!isEmpty()) {
            itemDatas.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据
     *
     * @return
     */
    @Override
    public List<T> getDatas() {
        return itemDatas;
    }


    public InnerClickListener getInnerClickListener() {
        return innerClickListener;
    }

    public void setInnerClickListener(InnerClickListener innerClickListener) {
        this.innerClickListener = innerClickListener;
    }

}
