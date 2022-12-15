package com.example.library.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.model.adapter.MapAdapter;
import com.example.library.model.Map;

import java.util.ArrayList;

public class MapFragment extends Fragment {
    private View view;
    public RecyclerView recyclerView;
    private ArrayList<Map> mapList = new ArrayList<Map>();
    private MapAdapter mapAdapter;
    private FragmentManager manager;
    private ImageView back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_map, container, false);
        initRecyclerView();
        initLost();

        back = view.findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                if (manager.getBackStackEntryCount() > 0) {
                    manager.popBackStackImmediate();
                } else {
                    requireActivity().finish();
                }
            }
        });
        return view;
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.map_recyclerView);
        mapAdapter = new MapAdapter(getActivity(), mapList);
        recyclerView.setAdapter(mapAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    private void initLost(){
        Map map1 = new Map(R.drawable.floor_1,R.drawable.floor_slogan_1,R.drawable.map_11,R.drawable.map_12,"一楼导览");
        mapList.add(map1);
        Map map2 = new Map(R.drawable.floor_2,R.drawable.floor_slogan_2,R.drawable.map_21,R.drawable.map_22,"二楼导览");
        mapList.add(map2);
        Map map3 = new Map(R.drawable.floor_3,R.drawable.floor_slogan_3,R.drawable.map_31,R.drawable.map_32,"三楼导览");
        mapList.add(map3);
        Map map4 = new Map(R.drawable.floor_4,R.drawable.floor_slogan_4,R.drawable.map_41,R.drawable.map_42,"四楼导览");
        mapList.add(map4);
        Map map5 = new Map(R.drawable.floor_5,R.drawable.floor_slogan_5,R.drawable.map_51,R.drawable.map_52,"五楼导览");
        mapList.add(map5);
    }
}
