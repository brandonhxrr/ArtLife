package com.brandonhxrr.artlife.ui.intro;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.R;
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
            //TODO: Create intent for Login
            //getActivity().startActivity();
        });
        return view;
    }
}