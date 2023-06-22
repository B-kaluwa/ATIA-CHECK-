package com.example.myinterface;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Online extends AppCompatActivity {

    TextView view1,view2,view3,view4,view5,view6,view7,view8,view9,view10,view11,view12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        view1 = findViewById(R.id.video1);
        view2 = findViewById(R.id.the_fool);
        view3 = findViewById(R.id.berg);
        view4 = findViewById(R.id.google_news);
        view5 = findViewById(R.id.bigger);
        view6 = findViewById(R.id.stock);
        view7 = findViewById(R.id.rules);
        view8 = findViewById(R.id.search_g);
        view9 = findViewById(R.id.wiki);


        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/watch?v=WxzqOcY1R1U");
            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.fool.com/");
            }
        });

        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://bloomberg.com");
            }
        });

        view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://news.google.com/foryou?hl=en-US&gl=US&ceid=US:en");
            }
        });

        view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.biggerpockets.com/investment-calculators");
            }
        });

        view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://stockunlocked.com/");
            }
        });

        view7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.youtube.com/results?search_query=financial+rules+for+investing+");
            }
        });

        view8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("http://www.google.com");
            }
        });

        view9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("http://www.wikipedia.com");
            }
        });

    }

    private void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}