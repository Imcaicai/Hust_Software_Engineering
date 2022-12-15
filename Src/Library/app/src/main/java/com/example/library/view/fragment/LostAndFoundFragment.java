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
import com.example.library.model.adapter.LostAndFoundAdapter;
import com.example.library.model.LostAndFound;

import java.util.ArrayList;

public class LostAndFoundFragment extends Fragment {

    private View view;
    public RecyclerView recyclerView;
    private ArrayList<LostAndFound> lostList = new ArrayList<LostAndFound>();
    private LostAndFoundAdapter lostAndFoundAdapter;
    private ImageView back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_lost_and_found, container, false);
        initRecyclerView();
        initLost();

        back = view.findViewById(R.id.icon_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                if (manager.getBackStackEntryCount() > 0) {
                    manager.popBackStackImmediate();
                } else {
                    requireActivity().finish();;
                }
            }
        });
        return view;
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.lost_recyclerView);
        lostAndFoundAdapter = new LostAndFoundAdapter(getActivity(), lostList);
        recyclerView.setAdapter(lostAndFoundAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    private void initLost(){
        LostAndFound lost1 = new LostAndFound("张小钰的校园卡", R.drawable.lost_1, "2022-03-21", "图书馆一楼", "如图");
        lostList.add(lost1);
        LostAndFound lost2 = new LostAndFound("粉色的水杯", R.drawable.lost_2, "2022-03-20", "图书馆咖啡厅", "如图");
        lostList.add(lost2);
        LostAndFound lost3 = new LostAndFound("白色的鼠标", R.drawable.lost_3, "2022-03-20", "图书馆五楼", "如图");
        lostList.add(lost3);
        LostAndFound lost4 = new LostAndFound("李小萁的校园卡", R.drawable.lost_4, "2022-03-18", "图书馆一楼", "如图");
        lostList.add(lost4);
        LostAndFound lost5 = new LostAndFound("苹果蓝牙耳机", R.drawable.lost_5, "2022-03-17", "图书馆咖啡厅", "如图");
        lostList.add(lost5);
        LostAndFound lost6 = new LostAndFound("蓝色的保温杯", R.drawable.lost_6, "2022-03-15", "图书馆三楼", "如图");
        lostList.add(lost6);
    }
}
