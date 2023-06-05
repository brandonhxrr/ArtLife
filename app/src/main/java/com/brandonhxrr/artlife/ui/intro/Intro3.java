package com.brandonhxrr.artlife.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.R;
import com.brandonhxrr.artlife.ui.Login;
import com.google.android.material.button.MaterialButton;

public class Intro3 extends Fragment {

    MaterialButton start;

    public Intro3() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro3, container, false);
        start = view.findViewById(R.id.btn_start);
        start.setOnClickListener(v -> {
            Intent startLogin = new Intent(getActivity(), Login.class);
            getActivity().startActivity(startLogin);
        });
        return view;
    }
}