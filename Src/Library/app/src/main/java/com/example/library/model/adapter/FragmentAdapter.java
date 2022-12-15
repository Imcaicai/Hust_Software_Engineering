package com.example.library.model.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.library.view.fragment.LibraryIntroductionFragment;
import com.example.library.view.fragment.OpenTimeFragment;
import com.example.library.view.fragment.OrganizationFragment;
import com.example.library.view.fragment.RegulationFragment;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {

        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new LibraryIntroductionFragment();
            case 1:
                return new OpenTimeFragment();
            case 2:
                return new OrganizationFragment();
            default:
                return new RegulationFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
