package com.example.orderpad;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    private onFragmentSelected listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment, container, false);
        Button loadFragment = view.findViewById(R.id.load_fragment);
        loadFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonSelected();
            }
        });
        return view;


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onFragmentSelected) {
            listener = (onFragmentSelected) context;
        }
        else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    public interface onFragmentSelected {
        public void onButtonSelected();
        public void onSignOut();
    }

}
