package com.example.myapplication;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private CameraManager cameraManager;
    private  String cameraID;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try{
            cameraID = cameraManager.getCameraIdList()[0];
        } catch (Exception e){
            e.printStackTrace();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)

    public void enable(View view){
        try {
            cameraManager.setTorchMode(cameraID, true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void disable(View view){
        try {
            cameraManager.setTorchMode(cameraID, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}