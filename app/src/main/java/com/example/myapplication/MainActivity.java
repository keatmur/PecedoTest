package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import androidx.viewpager2.widget.ViewPager2;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.example.myapplication.adapter.PageFragmentAdapter;
import com.example.myapplication.animations.ZoomOutPageTransformer;
import com.example.myapplication.fragment.PageFragment;
import com.example.myapplication.listener.PageListener;


public class MainActivity extends AppCompatActivity implements PageListener {

    private ViewPager2 viewPager;
    private PageFragmentAdapter fragmentAdapter;
    private final static String CHANNEL_ID = "chanelIc";
    NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        fragmentAdapter = new PageFragmentAdapter(getSupportFragmentManager(), getLifecycle());
        fragmentAdapter.addFragment(new PageFragment());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setPageTransformer(new ZoomOutPageTransformer());
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        /*int page =getIntent().getIntExtra("PAGE",1);
        fragmentAdapter.notifyItemChanged(page-1);*/
    }

    @Override
    public void del() {
        fragmentAdapter.removeFragment();
        notificationManager.cancel(fragmentAdapter.getItemCount() + 1);
    }

    @Override
    public void add() {
        fragmentAdapter.addFragment(new PageFragment());
    }

    @Override
    public void notificatoinSet(Integer page) {
/*
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.putExtra("PAGE",page);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);*/

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
        mBuilder
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("You create a notification”")
                .setContentText("Notification " + page.toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);
        //.setContentIntent(resultPendingIntent);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My channel",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("My channel description");
            channel.enableLights(true);
            channel.enableVibration(false);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(page, mBuilder.build());


    }
}