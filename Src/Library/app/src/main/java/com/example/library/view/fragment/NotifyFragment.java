package com.example.library.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.library.R;

import java.util.Objects;

public class NotifyFragment extends Fragment {
    private ImageView allIcon;
    private ImageView notion;
    private View view;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_notify, container, false);
        allIcon = view.findViewById(R.id.more_btn);
        allIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                NotifyAllFragment fragment = new NotifyAllFragment();
                transaction.hide(Objects.requireNonNull(manager.findFragmentById(R.id.activity_main_fragment)));
                transaction.add(R.id.activity_main_fragment, fragment, "notifyAll");
                transaction.show(fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        notion = view.findViewById(R.id.notion);
        notion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                NotionFragment fragment = new NotionFragment();
                transaction.hide(Objects.requireNonNull(manager.findFragmentById(R.id.activity_main_fragment)));
                transaction.add(R.id.activity_main_fragment, fragment, "notion");
                transaction.show(fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
