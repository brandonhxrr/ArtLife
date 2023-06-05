package com.brandonhxrr.artlife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brandonhxrr.artlife.ui.main.Intro3;
import com.google.android.material.button.MaterialButton;

public class Intro2 extends Fragment {

    MaterialButton next;

    public Intro2() { }

    public static Intro2 newInstance() {
        return new Intro2();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro2, container, false);

        next = view.findViewById(R.id.btn_next);
        next.setOnClickListener(v -> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Intro3 intro2Fragment = new Intro3();
            fragmentTransaction.replace(R.id.container, intro2Fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        return view;
    }
}