package com.example.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.sharedata.Notify;
import com.example.library.view.activity.NotifyActivity;

import java.util.ArrayList;
import java.util.List;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyAdapter.myViewHolder>{
    private Context context;
    private List<Notify> notifyList;

    public NotifyAdapter(Context context, ArrayList<Notify> notifyList){
        this.context = context;
        this.notifyList = notifyList;
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    public NotifyAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View view = View.inflate(context, R.layout.notify, null);
        return new NotifyAdapter.myViewHolder(view);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(NotifyAdapter.myViewHolder holder, int position) {
        //根据点击位置绑定数据
        Notify data = notifyList.get(position);
        holder.notifyName.setText(data.getName());
        holder.notifyLocation.setText(data.getLocation());
        holder.notifyStart.setText(data.getStart());
        holder.notifyCover.setImageResource(data.getCoverId());
        //设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, NotifyActivity.class);
                intent.putExtra("notifyCover", data.getCoverId());
                intent.putExtra("notifyLabel", data.getLabelId());
                intent.putExtra("notifyName", data.getName());
                intent.putExtra("notifyStart", data.getStart());
                intent.putExtra("notifyEnd", data.getEnd());
                intent.putExtra("notifyLocation", data.getLocation());
                intent.putExtra("notifyIntroduction", data.getIntroduction());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifyList.size();
    }

    //自定义viewhodler
    class myViewHolder extends RecyclerView.ViewHolder{
        private ImageView notifyCover;
        private TextView notifyStart;
        private TextView notifyLocation;
        private TextView notifyName;

        public myViewHolder(View notifyView){
            super(notifyView);
            notifyCover = (ImageView) notifyView.findViewById(R.id.notify_img);
            notifyStart = (TextView) notifyView.findViewById(R.id.start_time);
            notifyLocation = (TextView) notifyView.findViewById(R.id.notify_location);
            notifyName = (TextView) notifyView.findViewById(R.id.notify_name);

        }
    }
}
