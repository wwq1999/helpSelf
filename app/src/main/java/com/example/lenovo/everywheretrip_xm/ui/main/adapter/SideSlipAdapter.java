package com.example.lenovo.everywheretrip_xm.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.everywheretrip_xm.R;
import com.example.lenovo.everywheretrip_xm.ui.main.activity.SideslipActivity;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.List;

/**
 * Created by Lenovo on 2019/5/3.
 */

public class SideSlipAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List<String> list;
    private Context context;

    public SideSlipAdapter(Context context, List<String> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        View closeView = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.listview_item_delete, parent, false);
            holder = new ViewHolder();
            holder.tv_delete = convertView.findViewById(R.id.content);
            holder.btn_top = convertView.findViewById(R.id.btnTop);
            holder.btn_delete = convertView.findViewById(R.id.btnDelete);
            convertView.setTag(holder);
        }

        if (closeView == null){
            closeView = convertView;
        }
        final View finalCloseView = closeView;// listView的itemView

        holder = (ViewHolder) convertView.getTag();
        holder.tv_delete.setText(list.get(position));

        // 置顶按钮的单击事件
        holder.btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "置顶", Toast.LENGTH_SHORT).show();
                ((SwipeMenuLayout)(finalCloseView)).quickClose();// 关闭侧滑菜单：需要将itemView强转，然后调用quickClose()方法
            }
        });

        // 删除按钮的单击事件
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SwipeMenuLayout)(finalCloseView)).quickClose();// 关闭侧滑菜单
                if (delItemListener != null){
                    delItemListener.delete(position);// 调用接口的方法，回调删除该项数据
                }
            }
        });

        return convertView;
    }

    /**
     * 缓存控件用
     */
    static class ViewHolder{
        TextView tv_delete;// 展示内容
        Button btn_top;// 置顶
        Button btn_delete;// 删除
    }

    // 定义接口，包含了删除数据的方法
    public interface DeleteItem{
        void delete(int pos);
    }
    private DeleteItem delItemListener;
    // 设置监听器的方法
    public void setDelItemListener(DeleteItem delItemListener){
        this.delItemListener = delItemListener;
    }

}
