package com.example.activities_learn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
    }
/*
 SaveInstanceState: Trạng thái vòng đời của một Activity
 - Là một loại dữ liệu không bền vững
 - Được lưu trữ trong RAM
 - Sử dụng để truyền, phục hồi, lưu trạng thái của một Activity.
 - Dữ liêệu trong saveinstanceStated được lưu dưới dạng Bundle.
 - Được khôi phục khi phương thức onCreate và onRestoreInstanceState được gọi.

 => Ví dụ đơn giản: Android hỗ trợ Landscape, lúc này cả Aavtivities sẽ bị destroy cùng với dữ liệu.
 => Giải pháp: sử dụng SaveInstanceState là công cụ để giữu trạng thái của Activities không bị thay đổi
*/
    
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Log.d(TAG, "onRestoreInstanceState: ");
    }
}