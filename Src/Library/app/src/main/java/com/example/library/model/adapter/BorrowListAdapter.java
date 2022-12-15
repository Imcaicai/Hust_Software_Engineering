package com.example.library.model.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.library.R;
import com.example.library.controller.BookTableHelper;
import com.example.library.controller.DatabaseHelper;
import com.example.library.model.Book;
import com.example.library.model.Borrow;

import java.util.ArrayList;

public class BorrowListAdapter extends BaseAdapter {
    private Context mcontext;
    private ArrayList<Borrow> mBorrowList;
    private BookTableHelper bookTableHelper = new BookTableHelper();
    private ArrayList<Book> bookArrayList;
    public BorrowListAdapter(Context context,ArrayList<Borrow> borrow_list){
        mcontext = context;
        mBorrowList = borrow_list;
    }
    @Override
    public int getCount() {
        return mBorrowList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBorrowList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.list_borrow,null);
            holder.borrow_bookname = convertView.findViewById(R.id.borrow_bookname);
            holder.borrow_time = convertView.findViewById(R.id.borrow_time);
            holder.borrow_state = convertView.findViewById(R.id.borrow_state);
            holder.backtime = convertView.findViewById(R.id.backtime);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        final DatabaseHelper dbHelper=new DatabaseHelper(this.mcontext,"LibraryStore.db",null,1);
        Borrow borrow = mBorrowList.get(position);
        String bid = borrow.getBookId();
        bookArrayList = bookTableHelper.getBookDetails(dbHelper,bid);

        holder.borrow_bookname.setText(bookArrayList.get(0).getName());
        holder.borrow_time.setText("借阅日期：" + borrow.getBoTime());
        if (borrow.getState().equals("已被借阅")){
            String needreturn = "未还";
            holder.borrow_state.setText(needreturn);
            holder.backtime.setText("还书日期：--");
        }else {
            String needreturn = "已还";
            SpannableString spannableString = new SpannableString(needreturn);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#404146")),0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.borrow_state.setText(spannableString);
            holder.backtime.setText("还书日期："+borrow.getReTime());
        }
        return convertView;
    }

    public final class ViewHolder{
        public TextView borrow_bookname;
        public TextView borrow_time;
        public TextView borrow_state;
        public TextView backtime;
    }
}
