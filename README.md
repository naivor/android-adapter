# Android-Adapter  

<a href="http://www.methodscount.com/?lib=com.naivor%3Aadapter%3A1.0.3"><img src="https://img.shields.io/badge/Methods and size-core: 171 | deps: 21831 | 96 KB-e91e63.svg"/></a>

简单易用的Android Adapter,包括RecyAdapter和ListAdapter,对于RecyclerView 和 AbsListView 提供一致的Adapter操作风格

### 现在可以使用在线库：

```
compile 'com.naivor:adapter:1.0.3'
```
RecyAdapter和ListAdapter都实现了AdapterOperator接口，实现了对Adapter数据的添加，删除，置换，清空的操作，如图

![AdapterOperator接口](https://github.com/naivor/Android-Adapter/blob/master/docs/AdapterOperator.png)

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

    static class SHolder extends RecyHolder<SimpleItem> {

        @Bind(R.id.tv_text)
        TextView tvText;

        public SHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            registerClick(itemView);
        }

        @Override
        public void bindData(AdapterOperator<SimpleItem> operator, int position, SimpleItem itemData) {
            super.bindData(operator, position, itemData);

            tvText.setText(itemData.getContent() + "编号：" + position);
        }
      
    }
}

```
效果：


![img](https://github.com/naivor/Android-Adapter/blob/master/docs/Adapter%20RecyclerView.gif)

另外，RecyAdapter实现了HeaderFooter接口，支持对RecyclerView 添加Header，Footer

![HeaderFooter接口](https://github.com/naivor/Android-Adapter/blob/master/docs/HeaderFooter.png)


效果：


![img](https://github.com/naivor/Android-Adapter/blob/master/docs/Recycler%20HeaderFooter.gif)

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
ListView 使用效果：


![img](https://github.com/naivor/Android-Adapter/blob/master/docs/Adapter%20%20ListView.gif)

GridView 使用效果：


![img](https://github.com/naivor/Android-Adapter/blob/master/docs/Adapter%20GridView.gif)

### 3. For Multiple Items

定义不同的ViewHolder:
```
static class AHolder extends RecyHolder<SimpleItem> implements View.OnClickListener {...}
static class BHolder extends RecyHolder<SimpleItem> implements View.OnClickListener {...}
static class SHolder extends RecyHolder<SimpleItem> implements View.OnClickListener {...}
```
对不同的ViewType进行处理：

```
 @Override
    public RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        View view = createView(parent, viewType);

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
```
效果：

![img](https://github.com/naivor/Android-Adapter/blob/master/docs/Multiple%20Items.gif)



License
=========

    Copyright (c) 2017. Naivor.All rights reserved. 

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

