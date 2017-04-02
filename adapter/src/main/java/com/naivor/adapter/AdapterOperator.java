package com.naivor.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 适配器操作接口
 * <p>
 * Created by tianlai on 17-3-27.
 */

public interface AdapterOperator<T> {

    /**
     * 创建view
     *
     * @param parent
     * @param viewType
     * @return
     */
    View createView(ViewGroup parent, int viewType);

    /**
     * 获取布局资源
     *
     * @param viewType
     * @return
     */
    @LayoutRes
    int  getLayoutRes(int viewType);

    /**
     * 判断数据是否为空
     */
    boolean isEmpty();

    /**
     * 添加数据
     */
    void addItems(List<T> list);

    /**
     * 替换数据
     *
     * @param originItem
     * @param newItem
     */
    void replaceItem(T originItem, T newItem);
    /**
     * 替换数据
     *
     * @param position
     * @param newItem
     */
    void replaceItem(int position, T newItem);


    /**
     * 添加数据
     */
    void addItems(int position, List<T> list);

    /**
     * 添加单个数据
     */
    void addItem(T item);

    /**
     * 添加单个数据
     */
    void addItem(int position, T item);

    /**
     * 删除数据
     */
    void removeItem(int position);

    /**
     * 删除数据
     */
    void removeItem(T data);

    /**
     * 设置新数据，原来的清空
     */
    void setItems(List<T> list);

    /**
     * 清空
     */
    void clearItems();

    /**
     * 获取数据
     *
     * @return
     */
    List<T> getDatas();

    /**
     * 列表类的内部控件点击监听
     * <p>
     * Created by tianlai on 16-7-16.
     */
    interface InnerClickListener<T> {
        void onClick(View view, T itemData, int postition);
    }
}
