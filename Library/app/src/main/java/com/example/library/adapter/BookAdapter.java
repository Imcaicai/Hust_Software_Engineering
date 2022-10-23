package com.example.library.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.sharedata.Book;
import com.example.library.sharedata.LostAndFound;
import com.example.library.view.activity.BookActivity;
import com.example.library.view.activity.LostActivity;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.myViewHolder>{
    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, ArrayList<Book> bookList){
        this.context = context;
        this.bookList = bookList;
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
        View view = View.inflate(context, R.layout.book, null);
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
        Book data = bookList.get(position);
        holder.name.setText(data.getName());
        holder.author.setText(data.getAuthor());
        holder.publish.setText(data.getPublish());
        holder.cover.setImageResource(data.getCoverId());
        holder.collect.setImageResource(data.getCollectId());
        //设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("collect", data.getCollectId());
                intent.putExtra("cover", data.getCoverId());
                intent.putExtra("name", data.getName());
                intent.putExtra("author", data.getAuthor());
                intent.putExtra("publish", data.getPublish());
                intent.putExtra("location", data.getLocation());
                intent.putExtra("id", data.getId());
                intent.putExtra("state", data.getState());
                intent.putExtra("form", data.getForm());
                intent.putExtra("ISBN", data.getISBN());
                intent.putExtra("theme", data.getTheme());
                intent.putExtra("introduction", data.getIntroduction());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    //自定义viewhodler
    class myViewHolder extends RecyclerView.ViewHolder{
        private ImageView cover;
        private ImageView collect;
        private TextView name;
        private TextView author;
        private TextView publish;

        public myViewHolder(View lostView){
            super(lostView);
            cover = (ImageView) lostView.findViewById(R.id.book_cover);
            collect = (ImageView) lostView.findViewById(R.id.collect);
            name = (TextView) lostView.findViewById(R.id.book_name);
            author = (TextView) lostView.findViewById(R.id.author);
            publish = (TextView) lostView.findViewById(R.id.publish);

        }
    }
}
