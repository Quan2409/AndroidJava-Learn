package com.example.image_view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {

    protected int[] gallary = new int[]{R.drawable.warriot01, R.drawable.warrior02, R.drawable.warrior03};
    protected int i = 0;
    ImageView mImage;
    Button backImg;
    Button nextImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextImg = findViewById(R.id.frontwardButton);
        backImg = findViewById(R.id.backwardButton);
        mImage = findViewById(R.id.imageView);

        nextImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < 3) {
                    i++;
                    mImage.setImageResource(gallary[i]);
                }
            }
        });
        backImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mImage.setImageResource(gallary[i]);
                if (i > 0) {
                    i--;
                }
            }
        });
    }
}

