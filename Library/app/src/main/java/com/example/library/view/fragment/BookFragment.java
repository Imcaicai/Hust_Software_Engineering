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
import com.example.library.adapter.BookAdapter;
import com.example.library.adapter.LostAndFoundAdapter;
import com.example.library.sharedata.Book;
import com.example.library.sharedata.LostAndFound;

import java.util.ArrayList;

public class BookFragment extends Fragment {
    private View view;
    public RecyclerView recyclerView;
    private ArrayList<Book> bookList = new ArrayList<Book>();
    private BookAdapter bookAdapter;
    private ImageView cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_book, container, false);
        initRecyclerView();
        initBook();

        cancel = view.findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
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
        recyclerView = (RecyclerView) view.findViewById(R.id.book_recyclerView);
        bookAdapter = new BookAdapter(getActivity(), bookList);
        recyclerView.setAdapter(bookAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //设置item的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

    }

    private void initBook(){
        Book book1 = new Book("平凡的世界", "路遥","北京十月文艺出版社, 2017","馆藏地点：流通书库(B区) （主馆B402、B403）",
                "索书号：1101617617","馆藏状态：在架上 ","载体形态：348页 : 图 (部分彩图) ; 19cm",
                "ISBN：978-7-5447-8590-7 精装 CNY49.00","主题：长篇小说 英国 现代",
                "     该书以中国70年代中期到80年代中期十年间为背景，通过复杂的矛盾纠葛，以孙少安和孙少平两兄弟为中心，刻画了当时社会各阶层众多普通人的形象；" +
                        "劳动与爱情、挫折与追求、痛苦与欢乐、日常生活与巨大社会冲突纷繁地交织在一起，深刻地展示了普通人在大时代历史进程中所走过的艰难曲折的道路。1991年3月，" +
                        "《平凡的世界》获中国第三届茅盾文学奖。",R.drawable.icon_uncollect,R.drawable.book_1);
        bookList.add(book1);

        Book book2 = new Book("小王子", "(法) 安东尼·德·圣埃克苏佩里著; 贺鸿莉译","北京 : 群言出版社, 2016","馆藏地点： 流通书库(B区) （主馆B402、B403）",
                "索书号：1101114315","馆藏状态：馆内阅览","载体形态：310页 : 图 ; 22cm",
                "ISBN：978-7-5193-0144-6 CNY18.00","主题：童话 法国 现代",
                "     本书的主人公是来自外星球的小王子。书中以一位飞行员作为故事叙述者，讲述了小王子从自己星球出发前往地球的过程中，所经历的各种历险。" +
                        "作者以小王子的孩子式的眼光，透视出成人的空虚、盲目，愚妄和死板教条，用浅显天真的语言写出了人类的孤独寂寞、没有根基随风流浪的命运。" +
                        "同时，也表达出作者对金钱关系的批判，对真善美的讴歌。",R.drawable.icon_uncollect,R.drawable.book_2);
        bookList.add(book2);

        Book book3 = new Book("月亮与六便士", "（英国) 威廉·萨默塞特·毛姆著; 冯涛译","南京 : 译林出版社, 2021","馆藏地点：  流通书库(B区) （主馆B402、B403）",
                "索书号：1101617617","馆藏状态： 在架上","载体形态：348页 : 图 (部分彩图) ; 19cm",
                "ISBN：978-7-5447-8590-7 精装 CNY49.00","主题：长篇小说 英国 现代",
                "     一个英国证券交易所的经纪人，本已有稳定的职业和社会地位、美满的家庭，但却迷恋上绘画，像“被魔鬼附了体”，突然弃家出走，到巴黎去追求绘画的理想。" +
                        "没有人能够理解他的行为。经过一番离奇的遭遇后，主人公最后离开文明世界，远遁到与世隔绝的塔希提岛上。他的灵魂终于得到了的安宁。" +
                        "有人认为这篇小说的原型是法国印象派画家高更，这更增加了它的传奇色彩。",R.drawable.icon_uncollect,R.drawable.book_3);
        bookList.add(book3);

        Book book4 = new Book("三体", "刘慈欣","重庆 : 重庆出版社, 2008","馆藏地点：   流通书库(B区) （主馆B402、B403）",
                "索书号：1100421612","馆藏状态： 在架上","载体形态：302页 : 图 ; 21cm",
                "ISBN：978-7-5366-9293-0 CNY23.00","主题：长篇小说 科学幻想小说 中国 当代",
                "     《三体》是刘慈欣创作的系列长篇科幻小说，由《三体》、《三体Ⅱ·黑暗森林》、《三体Ⅲ·死神永生》组成，第一部于2006年5月起在《科幻世界》杂志上连载，" +
                        "第二部于2008年5月首次出版，第三部则于2010年11月出版。作品讲述了地球人类文明和三体文明的信息交流、生死搏杀及两个文明在宇宙中的兴衰历程。" +
                        "其第一部经过刘宇昆翻译后获得了第73届雨果奖最佳长篇小说奖。",R.drawable.icon_uncollect,R.drawable.book_4);
        bookList.add(book4);

        Book book5 = new Book("无人生还", "(英) 阿加莎·克里斯蒂著; 夏阳译","北京 : 新星出版社, 2020","馆藏地点：    流通书库(B区) （主馆B209、B210）",
                "索书号：1101595517","馆藏状态： 借出","载体形态：237页 ; 21cm",
                "ISBN：978-7-5133-3828-8 CNY42.00","主题：侦探小说 英国 现代",
                "     十个相互陌生、身份各异的人受邀前往德文郡海岸边一座孤岛上的豪宅。客人到齐后，主人却没有出现。当晚，一个神秘的声音发出指控，分别说出每个人心中罪恶的秘密。" +
                        "接着，一位客人离奇死亡。暴风雨让小岛与世隔绝，《十个小士兵》--这首古老的童谣成了死亡咒语。如同歌谣中所预言的，客人一个接一个死去……杀人游戏结束后，" +
                        "竟无一人生还！\n",R.drawable.icon_uncollect,R.drawable.book_5);
        bookList.add(book5);

        Book book6 = new Book("Java语言程序设计", "姜志强","北京 : 电子工业出版社, 2021","馆藏地点：    流通书库(B区) （主馆B209、B210）",
                "索书号：1101595517","馆藏状态： 在架上","载体形态：XII, 314页 : 图 ; 26cm",
                "ISBN：978-7-121-40310-1 CNY52.00","主题：JAVA 程序设计 高等学校 教材",
                "     全书共11章: 第1章介绍Java语言的基本知识和基本概念: 第2章介绍Java语言的基本语法、运算符和基本语句等语言结构化程序设计内容 ; " +
                        "第3章介绍Java语言面向对象程序设计的主要内容 ; 第4章介绍Java语言的异常处理 ; 第5章介绍Java语言的图形用户界面技术 ; 第6章介绍Java语言与输入/输出有关的流和文件 ; " +
                        "第7章介绍Java语言的多线程: 第8章介绍Java语言与数据结构和数据处理有关的类和接口 ; 第9章介绍Java Applet程序设计; 第10章介绍Java语言的网络与通信程序设计: " +
                        "第11章介绍Java语言的JDBC技术。",R.drawable.icon_uncollect,R.drawable.book_6);
        bookList.add(book6);

        Book book7 = new Book("我们仨", "杨绛","北京 : 三联书店, 2018","馆藏地点：   流通书库(B区) （主馆B402、B403）",
                "索书号：1101391417","馆藏状态： 在架上","载体形态：165页, [3] 页图版 : 图 ; 21cm",
                "ISBN：978-7-108-06310-6 CNY28.00","主题：散文集 中国 当代",
                "     本书作者用含蓄节制的文字, 记述了其与丈夫钱钟书、女儿钱瑗一家三口63年间的风雨坎坷, 展现了一个特殊的知识分子家庭鲜为人知的历程。",
                R.drawable.icon_uncollect,R.drawable.book_7);
        bookList.add(book7);

        Book book8 = new Book("设计师要懂心理学", "(美) 苏珊·魏因申克著 / Susan Weinschenk; 徐佳, 马迪, 余盈亿译","北京 : 人民邮电出版社, 2020",
                "馆藏地点：    流通书库(B区) （主馆B209、B210）", "索书号：1101605064","馆藏状态： 在架上","载体形态：xv, 240页 : 彩图 ; 21cm",
                "ISBN：978-7-115-55275-4 CNY69.00","主题：工业设计 应用心理学", "      本书出自国际知名的设计心理学专家苏珊·魏因申克" +
                "(Susan Weinschenk)之手，内容实用、示例清晰，以创造直观而又有吸引力的设计为宗旨，讨论了设计师必须知道的100个心理学问题。这些问题分为“人如何观察”“人如何阅读”" +
                "“人如何记忆”“人如何思考”“人如何集中注意力”“人的动机来源”“人是社会性动物”“人如何感知”“人会犯错”和“人如何决策”十个主题，每个问题短小精悍，片刻即可读完，" +
                "让人轻松地理解设计背后的心理学动机。新版更新了一些内容，来自第1版出版后作者新的研究体会。同时，作者还调整了遣词造句，改换了一些图片，让书能与时俱进。",
                R.drawable.icon_uncollect,R.drawable.book_8);
        bookList.add(book8);

        Book book9 = new Book("王小波全集 : 珍藏版", "王小波","南京 : 译林出版社, 2017","馆藏地点：    主馆C501",
                "索书号：1101143882","馆藏状态：  馆内阅览","载体形态：10册 ; 24cm", "ISBN：978-7-5447-6580-0 精装 ; 1 CNY29.80 ",
                "主题：中国文学 当代 作品综合集", "     最新最全展现王小波最侠骨柔情的时刻。爱情，友情，人生交情……《王小波全集》分为十卷，" +
                "每卷都以平装和精装珍藏版两种装帧形式出版，收录了王小波生前的重要著述，其中相当一部分是首次整理出来的思想火花。全套书分为杂文、长篇小说、中篇小说、短篇小说、" +
                "书信集及未竟稿，约200万字。", R.drawable.icon_uncollect,R.drawable.book_9);
        bookList.add(book9);

        Book book10 = new Book("人类简史 : 从动物到上帝", "(以) 尤瓦尔·赫拉利著 ; 林俊宏译","北京 : 中信出版集团, 2017","馆藏地点：     主馆C502、C503",
                "索书号：1101155606","馆藏状态：  馆内阅览","载体形态：xi, 417页 : 图 ; 23cm", "ISBN：978-7-5086-6075-2 CNY68.00",
                "主题：社会发展史 通俗读物", "     本书三大线索理清人类发展脉络，认知革命、农业革命、科技革命彻底改变了人类的历史。认知革命使得人类成为想象的共同体，" +
                "农业革命可能是史上最大的骗局，科技革命最终将使人类成为神一样的存在。", R.drawable.icon_uncollect,R.drawable.book_10);
        bookList.add(book10);

        Book book11 = new Book("外国新闻传播史纲要", "陈力丹, 张玉川著","北京 : 中国人民大学出版社, 2022","馆藏地点：      流通书库(B区) （主馆B402、B403）",
                "索书号：1101667475","馆藏状态：   在架上","载体形态：365页 : 图 ; 26cm", "ISBN：978-7-300-29979-2 CNY59.80",
                "主题：新闻事业史  国外 高等教育 教材", "     本书从宏观上概括了人类社会现代新闻传播的历史轨迹，" +
                "以期让读者对15世纪末至今全球现代化进程中新闻传播的发展大势有直观的印象；按照世界上目前的发展区域或板块，分别介绍了欧洲、" +
                "美洲、亚洲(西亚除外)、西亚北非、撒哈拉以南非洲及大洋洲的新闻传播史。", R.drawable.icon_uncollect,R.drawable.book_11);
        bookList.add(book11);
    }
}
