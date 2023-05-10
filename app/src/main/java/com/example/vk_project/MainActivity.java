package com.example.vk_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        int android_version = Build.VERSION.SDK_INT;
        System.out.println("ANDROID VERSION" + android_version);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button filesBtn = findViewById(R.id.button);
        filesBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (checkButton()){
                    //It's okey
                    Intent intent = new Intent(MainActivity.this,CheckedActivity.class);
                    String path = Environment.getExternalStorageDirectory().getPath();
                    intent.putExtra("path",path);
                    startActivity(intent);
                }else{
                    requestPermission();
                }
            }
        });
    }
    private boolean checkButton(){
        int result_old = ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE);
        int result_new = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_MEDIA_IMAGES);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(result_new==PackageManager.PERMISSION_GRANTED){
                return true;
            }else{
                return false;
            }
        }else{
            if(result_old==PackageManager.PERMISSION_GRANTED){
                return true;
            }else{
                return false;
            }
        }
    }
    private void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_MEDIA_IMAGES)){
                Toast.makeText(MainActivity.this, "Please allow the permission", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_MEDIA_IMAGES},110);
        }else{
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(MainActivity.this, "Please allow the permission", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},105);
        }
    }


}