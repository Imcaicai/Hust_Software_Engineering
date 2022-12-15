package com.example.library.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.model.Map;
import com.example.library.view.activity.MapActivity;

import java.util.ArrayList;
import java.util.List;

public class MapAdapter extends RecyclerView.Adapter<MapAdapter.myViewHolder> {
    private Context context;
    private List<Map> mapList;

    public MapAdapter(Context context, ArrayList<Map> mapList){
        this.context = context;
        this.mapList = mapList;
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
        View view = View.inflate(context, R.layout.map, null);
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
        Map data = mapList.get(position);
        holder.floor.setImageResource(data.getFloorId());
        //设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, MapActivity.class);
                intent.putExtra("mapId_0", data.getMapId_0());
                intent.putExtra("mapId_1",data.getMapId_1());
                intent.putExtra("floorName", data.getFloorName());
                intent.putExtra("sloganId", data.getSloganId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    //自定义viewhodler
    class myViewHolder extends RecyclerView.ViewHolder{
        private ImageView floor;

        public myViewHolder(View lostView){
            super(lostView);
            floor = (ImageView) lostView.findViewById(R.id.floor_btn_1);

        }
    }
}
