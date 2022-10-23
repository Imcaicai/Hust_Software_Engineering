package com.example.library.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.library.R;
import com.example.library.databinding.FragmentLoginBinding;
import com.example.library.utils.MWindowsUtils;
import com.example.library.viewModel.LoginViewModel;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        MWindowsUtils.init(requireActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        init();
        return binding.getRoot();
    }

    private void init(){
        LoginViewModel loginViewModel = new LoginViewModel(binding, requireActivity(), this);
        loginViewModel.init();
    }
}
