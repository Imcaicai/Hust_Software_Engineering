package com.example.library.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.library.R;

public class NotionFragment extends Fragment {
    private ImageView back;
    private View view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_notion, container, false);

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
}
