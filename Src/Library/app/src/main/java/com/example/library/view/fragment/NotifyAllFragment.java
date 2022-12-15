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
import com.example.library.model.adapter.NotifyAdapter;
import com.example.library.model.Notify;

import java.util.ArrayList;

public class NotifyAllFragment extends Fragment {
    private View view;
    public RecyclerView recyclerView;
    private ArrayList<Notify> notifyList = new ArrayList<Notify>();
    private NotifyAdapter notifyAdapter;
    private ImageView back;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_all_notify, container, false);
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
                    requireActivity().finish();;
                }
            }
        });
        return view;
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView) view.findViewById(R.id.notify_recyclerView);
        notifyAdapter = new NotifyAdapter(getActivity(), notifyList);
        recyclerView.setAdapter(notifyAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    private void initLost(){
        Notify notify1 = new Notify("喻见·你的第一本书", R.drawable.notify_1, R.drawable.label_2,"2021-09-01 00:00", "2021-10-3 00:00", "线上",
        "想熟练掌握信息资源检索、文献调研的方法吗？\n想了解更多学科热点追踪、学术规范的途径吗？\n想让你的Excel水平、P图技巧更上一层楼吗？\n新学期图书馆讲座与培训你不容错过！\n" +
                "第1讲：图书馆资源服务体系简介\n第2讲：ProQuest助力选项，及论文撰写中如何运用检索技巧\n第3讲：科研避雷——避免学术不端\n第4讲：事实与数据资源的获取与利用\n第5讲：掌握个人知识管理工具，文献调研小case");
        notifyList.add(notify1);

        Notify notify2 = new Notify("您的新学期图书馆讲座与培训套餐请查收！", R.drawable.notify_2, R.drawable.label_3,"2022-03-24 00:00", "2022-05-12 00:00", "线上",
                "金秋的九月\n图书馆迎来了2021届萌新们的参观热潮\n不少同学入馆的第一天 \n已经尝试借阅图书\n让我们看看新学期里\n新HUSTer借了一些什么书吧\n不知道这些图书\n" +
                        "是否引起萌新们来馆借书的兴趣呢\n图书馆已经准备好\n为2021届新生们开启\n");
        notifyList.add(notify2);

        Notify notify3 = new Notify("“五四演讲大赛”开讲了", R.drawable.notify_3, R.drawable.label_9,"2020-04-01 00:00", "2021-04-22 00:00", "线上",
                "本次的赛制分为初赛、决赛和集中展示三个部分。\n具体来说，本次比赛采取线上云演讲的形式，\n请各位有才华的小哥哥小姐姐们\n将自己的演讲视频上交给所在单位团委\n进行初赛评比。\n各单位将选出三位优秀的演说家\n参加校级评比，前十名将进入决赛。\n" +
                        "决赛以案例分析的方式进行，\n相关视频经评委打分后确定名次。\n各位演说家的决赛视频\n将会在学校的相关新媒体平台\n集中展示哦。");
        notifyList.add(notify3);

        Notify notify4 = new Notify("武汉封城 我们该做什么", R.drawable.notify_4, R.drawable.label_6,"2020-01-23 00:00", "2020-02-09 00:00", "线上",
                "1.关注“华中科技大学图书馆”官方微信公众号，进入“服务-阅读推广”栏目，在弹出的活动列表中，选中本次活动，点击“参加活动”即可参与。\n" +
                        "2.打卡方式为：对阅读内容进行拍摄或截图；撰写针对阅读内容不超过250字的微书评；作品上传至本次活动平台。\n" +
                        "3.活动共15天，需每天登录阅读推广平台发布作品，完成15次打卡，活动期间个人可有3次补签机会（即满足12天打卡15次最低标准）。\n" +
                        "4.微书评经网络查重及审核后统一进行平台展示，图书馆结合作品质量及点赞数进行优选，并将优选作品于官微进行推广，后发放奖品。");
        notifyList.add(notify4);

        Notify notify5 = new Notify("Tonight观影会——布达佩斯大饭店（第九期）", R.drawable.notify_5, R.drawable.label_4,"2021-11-06 18:30", "2021-11-06 22:30", "图书馆新馆B401机房内放映室",
                "图书馆第九期观影活动来啦！\n北风忽至，百木萧疏。\n当夏天渐渐为冬天让路，微凉的晚风中，你是否也难免有所思所忆？\n在冬季，你可曾留恋过“绿蚁新醅酒，红泥小火炉”的优雅？\n抑或是“柴门闻犬吠，风雪夜归人”的温暖？\n" +
                        "这一次，我们邀请你走进布达佩斯大饭店，一同探访冰冷世界中的欧洲桃花源，在影片中的皑皑雪景中，静静品味古老文明的繁盛与衰落，温情与忧伤...\n本周六晚六点半，主图B401,我们不见不散！");
        notifyList.add(notify5);

        Notify notify6 = new Notify("第三届大学生“悦读之星”读书演讲风采展示活动", R.drawable.notify_6, R.drawable.label_4,"2021-04-12 00:00", "2021-06-10 23：59", "线上",
                "以“阅百年历程·传精神力量”为主题，各参与馆组织本校学生，根据活动主题自选书目阅读，将读书心得录制为演讲视频，在线参加活动。通过活动引领当代大学生正确认识党的光辉历史和伟大功绩，从历史经验中获取智慧，用知识武装自己，用阅读不断锤炼和提升自己。");
        notifyList.add(notify6);

        Notify notify7 = new Notify("首届“喻见华中大杯”主题图像创意设计征集活动", R.drawable.notify_7, R.drawable.label_7,"2020-03-18 00:00", "2020-09-30 00:00", "线上",
                "为深入贯彻落实习近平新时代中国特色社会主义思想，坚定文化自信，按照《国务院关于推进文化创意和设计服务与相关产业融合发展的若干意见》有关要求，结合当前抗击新冠肺炎疫情实际，" +
                        "华中科技大学图书馆联合校团委举办首届“喻见华中大杯”主题图像创意设计征集活动，九思书苑、建筑与城市规划学院团委、机械科学与工程学院团委协同开展，大力推进我校全民阅读推广工作。\n");
        notifyList.add(notify7);
    }
}
