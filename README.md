# Android-Adapter

简单易用的Android Adapter,包括RecyAdapter和ListAdapter,对于RecyclerView 和 AbsListView 提供一致的Adapter操作风格

他们都实现了AdapterOperator接口，实现了对Adapter数据的添加，删除，置换，清空的操作，如图

![AdapterOperator接口](http://img.blog.csdn.net/20170403095646751?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbmFpdm9y/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 1. For RecyclerView，you should  extends  RecyAdapter

example:

```
public class TestRecyAdapter extends RecyAdapter<SimpleItem> {


    public TestRecyAdapter(Context context) {
        super(context);
    }


    @Override
    public RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new SHolder(createView(parent, viewType));
    }

    @Override
    public int getLayoutRes(int viewType) {
        return R.layout.list_item_txt;
    }

    static class SHolder extends RecyHolder<SimpleItem> implements View.OnClickListener{

        @Bind(R.id.tv_text)
        TextView tvText;

        public SHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void bindData(AdapterOperator<SimpleItem> operator, int position, SimpleItem itemData) {
            super.bindData(operator, position, itemData);

            tvText.setText(itemData.getContent() + "编号：" + position);
        }


        @Override
        public void onClick(View v) {
            if (clickListener!=null){
                clickListener.onClick(v,itemData,position);
            }
        }
    }
}

```


另外，RecyAdapter实现了HeaderFooter接口，支持对RecyclerView 添加Header，Footer

![HeaderFooter接口](http://img.blog.csdn.net/20170403095927236?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbmFpdm9y/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)



### 2. For  AbsListView,you should extends  ListAdapter

example:

```
public class TestListAdapter extends ListAdapter<SimpleItem> {

    public TestListAdapter(Context context) {
        super(context);
    }

   
    @Override
    public ListHolder<SimpleItem> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(createView(parent, viewType));
    }

  
    @Override
    public int getLayoutRes(int viewType) {
        return R.layout.list_item_txt;
    }

   
    static class HomeViewHolder extends ListHolder<SimpleItem> {

        @Bind(R.id.tv_text)
        TextView tvText;

        public HomeViewHolder(View convertView) {
            super(convertView);

            ButterKnife.bind(this, convertView);

        }

        @Override
        public void bindData(AdapterOperator<SimpleItem> operator, int position, SimpleItem itemData) {
            super.bindData(operator, position, itemData);

            tvText.setText(itemData.getContent() + "编号：" + position);
        }
    }

}
```


