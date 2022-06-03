
package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    AppCompatButton turnOnOff;
    ImageView flashlightimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        turnOnOff = findViewById(R.id.btn);
        flashlightimg = findViewById(R.id.flashlight);

        turnOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turnOnOff.getText().toString().equals("Turn on")) {
                    turnOnOff.setText(R.string.turnoff);
                    flashlightimg.setImageResource(R.drawable.lighton);
                    changeLightState(true);
                } else {
                    turnOnOff.setText(R.string.turnon);
                    flashlightimg.setImageResource(R.drawable.lightoff);
                    changeLightState(false);
                }
            }

            private void changeLightState(boolean State) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
                    String camId = null;
                    try {
                        camId = cameraManager.getCameraIdList()[0];

                        cameraManager.setTorchMode(camId, State);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        turnOnOff.setText(R.string.turnon);
    }
}