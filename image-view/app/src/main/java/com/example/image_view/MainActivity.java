package com.example.image_view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import com.example.image_view.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {
    ActivityMainBinding binding;

    ImageView mImage = binding.imageView;
    Button backImg = binding.backwardButton;
    Button nextImg = binding.frontwardButton;

    protected int[] gallary = new int[] {R.drawable.warriot01, R.drawable.warrior02, R.drawable.warrior03};
    protected int currenImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextImg.setOnClickListener(v -> {
            if (currenImageIndex < 2) {
                mImage.setImageResource(gallary[currenImageIndex]);
                currenImageIndex++;
            }
        });

        backImg.setOnClickListener(v -> {
            if (currenImageIndex > 0) {
                mImage.setImageResource(gallary[currenImageIndex]);
                currenImageIndex--;
            }
        });
    }
}

