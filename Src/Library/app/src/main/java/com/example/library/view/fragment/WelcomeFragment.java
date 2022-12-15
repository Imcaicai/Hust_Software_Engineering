package com.example.library.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.library.R;
import com.example.library.view.activity.BookSearchActivity;
import com.example.library.view.activity.ScanCodeActivity;

import java.util.Objects;

public class WelcomeFragment extends Fragment {
    private View view;
    private ImageView scanCode;
    private ImageView map;
    private ImageView popular;
    private ImageView newBook;
    private EditText search;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_welcome, container, false);
        scanCode = view.findViewById(R.id.scan_code_btn);
        scanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ScanCodeActivity.class);
                startActivity(intent);
            }
        });

        map = view.findViewById(R.id.map_btn);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MapFragment fragment = new MapFragment();
                transaction.hide(Objects.requireNonNull(manager.findFragmentById(R.id.activity_main_fragment)));
                transaction.add(R.id.activity_main_fragment, fragment, "map");
                transaction.show(fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        popular = view.findViewById(R.id.popular_btn);
        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                BookPopularFragment fragment = new BookPopularFragment();
                transaction.hide(Objects.requireNonNull(manager.findFragmentById(R.id.activity_main_fragment)));
                transaction.add(R.id.activity_main_fragment, fragment, "bookPopular");
                transaction.show(fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        newBook = view.findViewById(R.id.new_book_btn);
        newBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                BookNewFragment fragment = new BookNewFragment();
                transaction.hide(Objects.requireNonNull(manager.findFragmentById(R.id.activity_main_fragment)));
                transaction.add(R.id.activity_main_fragment, fragment, "bookNew");
                transaction.show(fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        search = view.findViewById(R.id.search_book);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), BookSearchActivity.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }


}
