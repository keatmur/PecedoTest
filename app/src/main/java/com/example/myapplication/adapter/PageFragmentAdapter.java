package com.example.myapplication.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import java.util.ArrayList;
import java.util.List;

public class PageFragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> mFragmentList;


    public PageFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        mFragmentList = new ArrayList<>();
    }


    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
        notifyDataSetChanged();
    }

    public void removeFragment() {
        if (mFragmentList.size() > 0) {
            mFragmentList.remove(mFragmentList.size() - 1);
            notifyItemRemoved(mFragmentList.size() - 1);
        }
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment pageFragment = mFragmentList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("NUMBER_PAGE", position + 1);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public int getItemCount() {
        return mFragmentList.size();
    }
}

