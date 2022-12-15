package com.example.library.model.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.library.R;
import com.example.library.model.Book;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class BookGridAdapter extends BaseAdapter {
    private Context mcontext;
    private ArrayList<Book> mBookList;

    public BookGridAdapter(Context context, ArrayList<Book> book_list) {
        mcontext = context;
        mBookList = book_list;
    }

    @Override
    public int getCount() {
        return mBookList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 加载图书的grid视图
     * @param position
     * @param convertView
     * @param parent
     * @return View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.grid_book, null);
            holder.booklist_cover=convertView.findViewById(R.id.book_cover);
            holder.booklist_name = convertView.findViewById(R.id.name);
            holder.booklist_author = convertView.findViewById(R.id.author);
            holder.booklist_state = convertView.findViewById(R.id.state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Book book = mBookList.get(position);
        holder.booklist_cover.setImageBitmap(getAssetsBitmap(this.mcontext,book.getCover()));
        holder.booklist_name.setText(book.getName());
        holder.booklist_author.setText("作者：" + book.getAuthor());
        holder.booklist_state.setText("图书状态：" + book.getState());
        return convertView;
    }

    public final class ViewHolder {
        public ImageView booklist_cover;
        public TextView booklist_name;
        public TextView booklist_author;
        public TextView booklist_state;
    }

    /**
     * 从asset文件夹中读取图片
     * @param context
     * @param path
     * @return
     */
    public static Bitmap getAssetsBitmap(Context context, String path) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();

        try {
            InputStream is = am.open(path);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(am.toString());
        }
        return image;
    }


}