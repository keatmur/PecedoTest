package com.example.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.listener.PageListener;

import java.util.ArrayList;

public class PagerAdapter extends  RecyclerView.Adapter<PagerAdapter.ViewHolder> {
    private static final String TAG = "MyLog";

    private ArrayList<Integer> list =new ArrayList<>();
    private PageListener pageListener;
    private final Context ctx;

    public PagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.page_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.numberPage.setText(list.get(position).toString());
        if (position==0) {
            Log.i(TAG, String.valueOf(getItemCount()));
            holder.del.setVisibility(View.INVISIBLE);
        }else {
            holder.del.setVisibility(View.VISIBLE);
        }
        //Log.i(TAG, String.valueOf(getItemCount()));
        holder.add.setOnClickListener(view -> {
            pageListener.add();

        });
        holder.del.setOnClickListener(view -> {
            pageListener.del();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(list.get(position));
    }


    public PageListener getPageListener() {
        return pageListener;
    }

    public void setPageListener(PageListener pageListener) {
        this.pageListener = pageListener;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView numberPage;
        AppCompatImageButton add;
        AppCompatImageButton del;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            numberPage = itemView.findViewById(R.id.tv_number_page);
            add =itemView.findViewById(R.id.bt_add_page);
            del =itemView.findViewById(R.id.bt_del_page);
        }


    }
}
