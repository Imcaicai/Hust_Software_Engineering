package com.example.library.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;

import com.example.library.model.LostAndFound;
import com.example.library.view.activity.LostActivity;

import java.util.ArrayList;
import java.util.List;

public class LostAndFoundAdapter extends RecyclerView.Adapter<LostAndFoundAdapter.myViewHolder> {
    private Context context;
    private List<LostAndFound> lostAndFoundList;

    public LostAndFoundAdapter(Context context, ArrayList<LostAndFound> lostAndFoundList){
        this.context = context;
        this.lostAndFoundList = lostAndFoundList;
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param viewType
     * @return
     */
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建自定义布局
        View view = View.inflate(context, R.layout.lost_and_found, null);
        return new myViewHolder(view);
    }

    /**
     * 绑定数据，数据与view绑定
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        //根据点击位置绑定数据
        LostAndFound data = lostAndFoundList.get(position);
        holder.lostName.setText(data.getName());
        holder.lostLocation.setText(data.getLocation());
        holder.lostTime.setText(data.getTime());
        holder.lostImage.setImageResource(data.getImageId());
        //设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, LostActivity.class);
                intent.putExtra("lostImage", data.getImageId());
                intent.putExtra("lostName", data.getName());
                intent.putExtra("lostTime", data.getTime());
                intent.putExtra("lostLocation", data.getLocation());
                intent.putExtra("lostRemark", data.getRemark());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lostAndFoundList.size();
    }

    //自定义viewhodler
    class myViewHolder extends RecyclerView.ViewHolder{
        private ImageView lostImage;
        private TextView lostName;
        private TextView lostTime;
        private TextView lostLocation;

        public myViewHolder(View lostView){
            super(lostView);
            lostImage = (ImageView) lostView.findViewById(R.id.lost_img);
            lostName = (TextView) lostView.findViewById(R.id.lost_name);
            lostTime = (TextView) lostView.findViewById(R.id.lost_time);
            lostLocation = (TextView) lostView.findViewById(R.id.lost_location);

        }
    }
}
