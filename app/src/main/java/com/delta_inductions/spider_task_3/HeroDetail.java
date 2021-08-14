package com.delta_inductions.spider_task_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class HeroDetail extends AppCompatActivity {
    private static final String TAG ="herodetail" ;
    private TextView textView;
private ImageView imageView;
private String detail;
private String Url;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        setTitle("Info Page");
        detail = getIntent().getStringExtra("herodetail");
        textView = findViewById(R.id.herodetail);
        imageView = findViewById(R.id.heropic);
        progressBar = findViewById(R.id.progressbardetail);
        textView.setText(detail);
        Url = getIntent().getStringExtra("Url");
        Picasso.get().load(Url).fit().centerInside().into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                    progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

    }
}