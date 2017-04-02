package com.naivor.adapter;

import android.view.View;

/**
 * recycler的header，footer 操作接口
 * <p>
 * Created by tianlai on 17-4-2.
 */

public interface HeaderFooter {

    /**
     * 添加头部
     *
     * @param header
     */
    void addHeaderView(View header);

    /**
     * 添加尾部
     *
     * @param footer
     */
    void addFooterView(View footer);

    /**
     * 返回第一个HeaderView
     *
     * @return
     */
    View getHeaderView();

    /**
     * 返回第index个HeaderView
     *
     * @return
     */
    View getHeaderView(int index);

    /**
     * 返回第一个FooterView
     *
     * @return
     */
    View getFooterView();

    /**
     * 返回第index个FooterView
     *
     * @return
     */
    View getFooterView(int index);


    /**
     * 移除头部 view
     *
     * @param view
     */
    void removeHeaderView(View view);

    /**
     * 移除头部 index
     *
     * @param index
     */
    void removeHeaderView(int index);

    /**
     * 移除尾部
     *
     * @param view
     */
    void removeFooterView(View view);

    /**
     * 移除尾部
     *
     * @param index
     */
    void removeFooterView(int index);

    /**
     * 获取头部的数量
     *
     * @return
     */
    int getHeaderCount();

    /**
     * 获取尾部的数量
     *
     * @return
     */
    int getFooterCount();

    /**
     * 是否是头部
     *
     * @param position
     * @return
     */
    boolean isHeader(int position);

    /**
     * 是否是头部
     *
     * @param view
     * @return
     */
    boolean isHeader(View view);

    /**
     * 是否是尾部
     *
     * @param position
     * @return
     */
    boolean isFooter(int position);

    /**
     * 是否是尾部
     *
     * @param view
     * @return
     */
    boolean isFooter(View view);

    /**
     * 清空头部
     */
    void clearHeader();

    /**
     * 清空尾部
     */
    void clearFooter();
}
