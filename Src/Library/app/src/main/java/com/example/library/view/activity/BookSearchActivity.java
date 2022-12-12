package com.example.library.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.library.R;
import com.example.library.adapter.BookGridAdapter;
import com.example.library.helper.BookTableHelper;
import com.example.library.helper.DatabaseHelper;
import com.example.library.sharedata.Book;
import com.example.library.utils.TipsDialog;

import java.util.ArrayList;

public class BookSearchActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Book> bookArrayList;
    private GridView gridView;
    private EditText searchEdit;
    private Button searchBtn;
    private ImageView addDefaultBtn;
    private BookTableHelper bookTableHelper = new BookTableHelper();
    final DatabaseHelper dbHelper=new DatabaseHelper(this,"LibraryStore.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        initView();
        initBookGrid();
        //判断创建初始数据按钮是否可点击
        SharedPreferences sharedPreferences = getSharedPreferences("system",MODE_PRIVATE);
        boolean default_data = sharedPreferences.getBoolean("default_data",false);
        if (default_data == true){
            addDefaultBtn.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_btn:
                String searchText=searchEdit.getText().toString();
                Intent intent=new Intent(BookSearchActivity.this,BookSearchResultActivity.class);
                intent.putExtra("searchText",searchText);
                startActivity(intent);
                break;
            case R.id.add_default_btn:
                Book book1=new Book("平凡的世界","路遥","北京十月文艺出版社,2017","流通书库(B区:主馆B402、B403)","book_1.png","在馆内",
                        "978-7-5447-8590-7","长篇小说 英国 现代","该书以中国70年代中期到80年代中期十年间为背景，通过复杂的矛盾纠葛，以孙少安和孙少平两兄弟为中心，" +
                        "刻画了当时社会各阶层众多普通人的形象；劳动与爱情、挫折与追求、痛苦与欢乐、日常生活与巨大社会冲突纷繁地交织在一起，深刻地展示了普通人在大时代历史进程中所走过的艰难曲折的道路。");
                Book book2=new Book("小王子", "(法)安东尼·德·圣埃克苏佩里","群言出版社,2016","流通书库(B区:主馆B402、B403)","book_2.png","在馆内",
                        "978-7-5193-0144-6","童话 法国 现代","本书的主人公是来自外星球的小王子。书中以一位飞行员作为故事叙述者，" +
                        "讲述了小王子从自己星球出发前往地球的过程中，所经历的各种历险。");
                Book book3=new Book("月亮与六便士", "(英)威廉·萨默塞特·毛姆","南京译林出版社,2021","流通书库(B区:主馆B402、B403)","book_3.png","在馆内",
                        "978-7-5447-8590-7","长篇小说 英国 现代","一个英国证券交易所的经纪人，本已有稳定的职业和社会地位、美满的家庭，但却迷恋上绘画，" +
                        "像“被魔鬼附了体”，突然弃家出走，到巴黎去追求绘画的理想。没有人能够理解他的行为。");
                Book book4=new Book("三体", "刘慈欣","重庆出版社,2008","流通书库(B区:主馆B402、B403)","book_4.png","在馆内",
                        "978-7-5366-9293-0","科幻小说 现代","《三体》是刘慈欣创作的系列长篇科幻小说，由《三体》、《三体Ⅱ·黑暗森林》、《三体Ⅲ·死神永生》组成，" +
                        "作品讲述了地球人类文明和三体文明的信息交流、生死搏杀及两个文明在宇宙中的兴衰历程。");
                Book book5=new Book("无人生还", "(英)阿加莎·克里斯蒂","北京新星出版社,2020","流通书库(B区:主馆B209、B210)","book_5.png","在馆内",
                        "978-7-5133-3828-8","侦探小说 英国 现代","十个相互陌生、身份各异的人受邀前往德文郡海岸边一座孤岛上的豪宅。客人到齐后，主人却没有出现。" +
                        "当晚，一个神秘的声音发出指控，分别说出每个人心中罪恶的秘密。接着，一位客人离奇死亡。");
                Book book6=new Book("Java语言程序设计", "姜志强","北京电子工业出版社,2021","流通书库(B区:主馆B209、B210)","book_6.png","在馆内",
                        "978-7-121-40310-1","JAVA 程序设计 高等学校 教材","全书共11章: 第1章介绍Java语言的基本知识和基本概念: " +
                        "第2章介绍Java语言的基本语法、运算符和基本语句等语言结构化程序设计内容。");
                Book book7=new Book("我们仨", "杨绛","北京三联书店,2018","流通书库(B区:主馆B402、B403)","book_7.png","在馆内",
                        "978-7-108-06310-6","散文集 中国 当代","本书作者用含蓄节制的文字, 记述了其与丈夫钱钟书、女儿钱瑗一家三口63年间的风雨坎坷, " +
                        "展现了一个特殊的知识分子家庭鲜为人知的历程。");
                Book book8=new Book("设计师要懂心理学", "(美)苏珊·魏因申克","北京人民邮电出版社,2020", "流通书库(B区:主馆B209、B210)", "book_8.png","在馆内",
                        "978-7-115-55275-4","工业设计 应用心理学","本书出自国际知名的设计心理学专家苏珊·魏因申克(Susan Weinschenk)之手，" +
                        "内容实用、示例清晰，以创造直观而又有吸引力的设计为宗旨，讨论了设计师必须知道的100个心理学问题。");
                Book book9=new Book("王小波全集珍藏版", "王小波","南京译林出版社,2017","主馆C501","book_9.png","在馆内",
                        "978-7-5447-6580-0","中国文学 当代 作品综合集","最新最全展现王小波最侠骨柔情的时刻。爱情，友情，人生交情……" +
                        "《王小波全集》分为十卷，收录了王小波生前的重要著述，其中相当一部分是首次整理出来的思想火花。");
                Book book10=new Book("人类简史:从动物到上帝", "(以)尤瓦尔·赫拉利","北京中信出版集团,2017","主馆C502、C503","book_10.png","在馆内",
                        "978-7-5086-6075-2","社会发展史 通俗读物","本书三大线索理清人类发展脉络，认知革命、农业革命、科技革命彻底改变了人类的历史。" +
                        "认知革命使得人类成为想象的共同体，农业革命可能是史上最大的骗局，科技革命最终将使人类成为神一样的存在。");
                Book book11=new Book("外国新闻传播史纲要", "陈力丹, 张玉川","北京中国人民大学出版社,2022","流通书库(B区:主馆B402、B403)","book_11.png","在馆内",
                        "978-7-300-29979-2","新闻事业史 国外 高等教育 教材","本书从宏观上概括了人类社会现代新闻传播的历史轨迹，按照世界上目前的发展区域或板块，" +
                        "分别介绍了欧洲美洲、亚洲(西亚除外)、西亚北非、撒哈拉以南非洲及大洋洲的新闻传播史。");
                boolean flag1=bookTableHelper.addBook(dbHelper,book1);
                boolean flag2=bookTableHelper.addBook(dbHelper,book2);
                boolean flag3=bookTableHelper.addBook(dbHelper,book3);
                boolean flag4=bookTableHelper.addBook(dbHelper,book4);
                boolean flag5=bookTableHelper.addBook(dbHelper,book5);
                boolean flag6=bookTableHelper.addBook(dbHelper,book6);
                boolean flag7=bookTableHelper.addBook(dbHelper,book7);
                boolean flag8=bookTableHelper.addBook(dbHelper,book8);
                boolean flag9=bookTableHelper.addBook(dbHelper,book9);
                boolean flag10=bookTableHelper.addBook(dbHelper,book10);
                boolean flag11=bookTableHelper.addBook(dbHelper,book11);

                final TipsDialog tipsDialog=new TipsDialog(this);
                tipsDialog.setTile("提示");
                tipsDialog.setCancelbtnVisible(false);
                if(flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7 && flag8 && flag9 && flag10 && flag11){
                    tipsDialog.setMsg("插入成功！");
                    tipsDialog.setOnSureListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferences sharedPreferences = getSharedPreferences("system",MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("default_data",true);
                            editor.commit();
                            //Intent back = new Intent(ManageIndexActivity.this,ShowBooksActivity.class);
                            //startActivity(back);
                        }
                    });
                    tipsDialog.show();
                }
        }
    }

    private void initView(){
        gridView=findViewById(R.id.book_gridview);
        searchBtn=findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(this);
        searchEdit=findViewById(R.id.search_edit);
        addDefaultBtn=findViewById(R.id.add_default_btn);
        addDefaultBtn.setOnClickListener(this);

        //设置顶部框透明
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    private void initBookGrid(){
        bookArrayList=bookTableHelper.bookGrid(dbHelper);
        BookGridAdapter adapter=new BookGridAdapter(this,bookArrayList);
        GridView gridView=findViewById(R.id.book_gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new BookOnClickListener());
    }


    private class BookOnClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String bid = bookArrayList.get(position).getId();
            Intent intent = new Intent(BookSearchActivity.this, BookActivity1.class);
            intent.putExtra("bid",bid);
            intent.putExtra("startActivityCode","show");
            startActivity(intent);
        }
    }


}