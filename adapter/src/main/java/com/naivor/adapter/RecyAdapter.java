package com.naivor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * recyclerView的适配器基类
 * <p>
 * Created by tianlai on 16-7-11.
 */
public abstract class RecyAdapter<T> extends RecyclerView.Adapter implements AdapterOperator<T>, HeaderFooter {
    protected final String TAG = this.getClass().getSimpleName();

    private static final int TYPE_HEADER = Integer.MIN_VALUE;
    private static final int TYPE_FOOTER = Integer.MAX_VALUE;

    protected Context context;
    protected LayoutInflater inflater;

    protected List<T> itemDatas;

    protected InnerListener<T> InnerListener;

    private List<View> headers;
    private List<View> footers;

    private ViewGroup parent;

    public RecyAdapter(Context context) {
        this(context, LayoutInflater.from(context));
    }

    public RecyAdapter(Context context, LayoutInflater inflater) {
        this.context = context;
        this.inflater = inflater;

        itemDatas = new ArrayList<>();
        headers = new ArrayList<>();
        footers = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return itemDatas.size() + getHeaderCount() + getFooterCount();
    }

    public T getItem(int position) {
        if (position < getItemCount()) {
            return itemDatas.get(position - getHeaderCount());
        }

        return null;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int headerCount = getHeaderCount();
        int realCount = itemDatas.size();

        if (viewType < TYPE_HEADER + headerCount) {
            View itemView = headers.get(viewType - TYPE_HEADER);

            return new ViewHolder(itemView);
        } else if (viewType >= TYPE_FOOTER - getItemCount()) {
            return new ViewHolder(footers.get(TYPE_FOOTER - viewType - headerCount - realCount));
        }

        return createHolder(parent, viewType - Integer.MAX_VALUE / 2);

    }

    /**
     * 创建viewholder
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position) || isFooter(position)) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();

            if (layoutParams == null) {
                RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.bottomMargin = 1;
                params.topMargin = 1;
                holder.itemView.setLayoutParams(params);

            } else if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }


        } else {
            ((RecyHolder<T>) holder).bindData(this, position, itemDatas.get(position - getHeaderCount()));
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public final int getItemViewType(int position) {

        if (isHeader(position)) {  //处理头部
            return TYPE_HEADER + position;
        } else if (isFooter(position)) {  //处理尾部
            return TYPE_FOOTER - position;
        }

        int innerItemViewType = getAdapterItemType(position - getHeaderCount());

        if (innerItemViewType >= Integer.MAX_VALUE / 2) {
            throw new IllegalArgumentException("your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
        }

        return innerItemViewType + Integer.MAX_VALUE / 2;
    }

    @Override
    public View createView(ViewGroup parent, int viewType) {
        return inflater.inflate(getLayoutRes(viewType), parent, false);
    }

    /**
     * 获取自己适配器定义的ItemType
     *
     * @param position
     * @return
     */
    public int getAdapterItemType(int position) {
        return 0;
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

//            notifyItemChanged(position);

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
        if (position < itemDatas.size() && position > -1) {
            itemDatas.set(position, newItem);

//        notifyItemChanged(position);

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
        if (position >= 0 && position < getItemCount()) {

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

    @Override
    public List<T> getDatas() {
        return itemDatas;
    }


    public InnerListener<T> getInnerListener() {
        return InnerListener;
    }

    public void setInnerListener(InnerListener<T> InnerListener) {
        this.InnerListener = InnerListener;
    }

    /**
     * 添加头部
     *
     * @param header
     */
    @Override
    public void addHeaderView(View header) {
        if (header == null) {
            throw new RuntimeException("header is null");
        }

        headers.add(header);
        this.notifyDataSetChanged();
    }

    /**
     * 添加尾部
     *
     * @param footer
     */
    @Override
    public void addFooterView(View footer) {
        if (footer == null) {
            throw new RuntimeException("footer is null");
        }

        footers.add(footer);
        this.notifyDataSetChanged();
    }

    /**
     * 返回第一个HeaderView
     *
     * @return
     */
    @Override
    public View getHeaderView() {
        return getHeaderCount() > 0 ? headers.get(0) : null;
    }

    /**
     * 返回第index个HeaderView
     *
     * @param index 从0开始
     * @return
     */
    @Override
    public View getHeaderView(int index) {
        return getHeaderCount() >= (index + 1) ? headers.get(index) : null;
    }

    /**
     * 返回第一个FooterView
     *
     * @return
     */
    @Override
    public View getFooterView() {
        return getFooterCount() > 0 ? footers.get(0) : null;
    }

    /**
     * 返回第index个FooterView
     *
     * @param index
     * @return
     */
    @Override
    public View getFooterView(int index) {
        return getFooterCount() >= (index + 1) ? footers.get(index) : null;
    }

    /**
     * 移除头部 view
     *
     * @param view
     */
    @Override
    public void removeHeaderView(View view) {
        if (headers.contains(view)) {
            headers.remove(view);
            this.notifyDataSetChanged();
        }
    }

    /**
     * 移除头部 index
     *
     * @param index
     */
    @Override
    public void removeHeaderView(int index) {
        if (getHeaderCount() >= (index + 1)) {
            headers.remove(index);
            this.notifyDataSetChanged();
        }
    }

    /**
     * 移除尾部
     *
     * @param view
     */
    @Override
    public void removeFooterView(View view) {
        if (footers.contains(view)) {
            footers.remove(view);
            this.notifyDataSetChanged();
        }
    }

    /**
     * 移除尾部
     *
     * @param index
     */
    @Override
    public void removeFooterView(int index) {
        if (getFooterCount() >= (index + 1)) {
            footers.remove(index);
            this.notifyDataSetChanged();
        }
    }

    /**
     * 获取头部的数量
     *
     * @return
     */
    @Override
    public int getHeaderCount() {
        return headers.size();
    }

    /**
     * 获取尾部的数量
     *
     * @return
     */
    @Override
    public int getFooterCount() {
        return footers.size();
    }

    /**
     * 是否是头部
     *
     * @param position
     * @return
     */
    @Override
    public boolean isHeader(int position) {
        return getHeaderCount() > position && position >= 0;
    }

    /**
     * 是否是头部
     *
     * @param view
     * @return
     */
    @Override
    public boolean isHeader(View view) {
        return headers.contains(view);
    }

    /**
     * 是否是尾部
     *
     * @param position
     * @return
     */
    @Override
    public boolean isFooter(int position) {
        return getItemCount() - getFooterCount() - 1 < position && position > 0;
    }

    /**
     * 是否是尾部
     *
     * @param view
     * @return
     */
    @Override
    public boolean isFooter(View view) {
        return footers.contains(view);
    }

    /**
     * 清空头部
     */
    @Override
    public void clearHeader() {
        headers.clear();
        this.notifyDataSetChanged();
    }

    /**
     * 清空尾部
     */
    @Override
    public void clearFooter() {
        footers.clear();
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
