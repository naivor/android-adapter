package com.tianlai.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.naivor.adapter.AdapterOperator;
import com.naivor.adapter.RecyAdapter;
import com.naivor.adapter.RecyHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * recyclerAdapter测试
 * <p>
 * Created by tianlai on 17-4-2.
 */

public class MultiRecyAdapter extends RecyAdapter<SimpleItem> {


    public MultiRecyAdapter(Context context) {
        super(context);
    }

    /**
     * 创建viewholder,需要重写
     *
     * @param view
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder createHolder(View view, int viewType) {

        switch (viewType) {
            case SimpleItem.Type.A:
                return new AHolder(view);
            case SimpleItem.Type.B:
                return new BHolder(view);
            case SimpleItem.Type.S:
                return new SHolder(view);
            default:
                return null;
        }
    }

    @Override
    public int getAdapterItemType(int position) {
        return getItem(position).getType();
    }

    /**
     * 获取布局资源
     *
     * @param viewType
     * @return
     */
    @Override
    public int getLayoutRes(int viewType) {
        switch (viewType) {
            case SimpleItem.Type.A:
                return R.layout.list_item_txt_a;
            case SimpleItem.Type.B:
                return R.layout.list_item_txt_b;
            case SimpleItem.Type.S:
                return R.layout.list_item_txt;
            default:
                return 0;
        }

    }


    static class SHolder extends RecyHolder<SimpleItem> {

        @Bind(R.id.tv_text)
        TextView tvText;

        public SHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            registerLongClick(itemView);
        }

        @Override
        public void bindData(AdapterOperator<SimpleItem> operator, int position, SimpleItem itemData) {
            super.bindData(operator, position, itemData);

            tvText.setText(itemData.getContent() + "编号：" + position);
        }


    }

    static class AHolder extends RecyHolder<SimpleItem> implements View.OnClickListener {

        @Bind(R.id.tv_text_1)
        TextView tvText1;
        @Bind(R.id.tv_text_2)
        TextView tvText2;
        @Bind(R.id.tv_text_3)
        TextView tvText3;

        public AHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            registerClick(itemView);
        }

        @Override
        public void bindData(AdapterOperator<SimpleItem> operator, int position, SimpleItem itemData) {
            super.bindData(operator, position, itemData);

            tvText1.setText(itemData.getContent());
            tvText2.setText(itemData.getContent());
            tvText3.setText(itemData.getContent());
        }

    }

    static class BHolder extends RecyHolder<SimpleItem> implements View.OnClickListener {

        @Bind(R.id.tv_text_1)
        TextView tvText1;
        @Bind(R.id.tv_text_2)
        TextView tvText2;
        @Bind(R.id.tv_text_3)
        TextView tvText3;

        public BHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            registerClick(itemView);
        }

        @Override
        public void bindData(AdapterOperator<SimpleItem> operator, int position, SimpleItem itemData) {
            super.bindData(operator, position, itemData);

            tvText1.setText(itemData.getContent());
            tvText2.setText(itemData.getContent());
            tvText3.setText(itemData.getContent());
        }

    }

}
