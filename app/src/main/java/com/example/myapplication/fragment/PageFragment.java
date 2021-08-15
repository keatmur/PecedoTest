package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.listener.PageListener;


public class PageFragment extends Fragment {


    private TextView numberPage;
    private AppCompatImageButton add;
    private AppCompatImageButton del;
    private AppCompatButton notificatoin;

    private PageListener pageListener;
    private Integer page;


    public PageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            pageListener = (PageListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс PageListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        page = getArguments().getInt("NUMBER_PAGE", 1);
    }

    @Override
    public void onStart() {
        super.onStart();
        numberPage.setText(page.toString());
        if (page == 1) {
            del.setVisibility(View.INVISIBLE);
        } else {
            del.setVisibility(View.VISIBLE);
        }
        notificatoin.setOnClickListener(view -> {
            pageListener.notificatoinSet(page);
        });

        add.setOnClickListener(view -> {
            pageListener.add();
        });
        del.setOnClickListener(view -> {
            pageListener.del();
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.page_layout, container, false);
        numberPage = itemView.findViewById(R.id.tv_number_page);
        add = itemView.findViewById(R.id.bt_add_page);
        del = itemView.findViewById(R.id.bt_del_page);
        notificatoin = itemView.findViewById(R.id.bt_create_new_notification);
        return itemView;
    }

}