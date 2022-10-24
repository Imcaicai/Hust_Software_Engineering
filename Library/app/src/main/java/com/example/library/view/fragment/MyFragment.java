package com.example.library.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.library.R;
import com.example.library.view.activity.BookActivity;
import com.example.library.view.activity.ReaderGuideActivity;

import java.util.Objects;

public class MyFragment extends Fragment {
    private TextView lostIcon;
    private TextView guideIcon;
    private View view;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_my, container, false);
        lostIcon = view.findViewById(R.id.lost_and_found);
        lostIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                LostAndFoundFragment fragment = new LostAndFoundFragment();
                transaction.hide(Objects.requireNonNull(manager.findFragmentById(R.id.activity_main_fragment)));
                transaction.add(R.id.activity_main_fragment, fragment, "lostAndFound");
                transaction.show(fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        guideIcon = view.findViewById(R.id.reader_guide);
        guideIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                Intent intent = new Intent(context, ReaderGuideActivity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("1", "onResume: ");
    }
}
