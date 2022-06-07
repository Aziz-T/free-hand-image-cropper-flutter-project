package com.example.cropper;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.embedding.engine.FlutterEngine;

public class MainActivity extends FlutterActivity  {


    private static final String CHANNEL = "samples.flutter.dev/battery";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {

                            if(call.method.equals("goIntent")){

                                Map<String,byte[]> map= call.arguments();
                                byte[] bytes = map.get("data");
                                Log.d("GAGAGA", String.valueOf(bytes));
                                goCropPage(bytes);
                            }
                        }
                );
    }



    void goCropPage(byte[] val){
        Intent intent = new Intent(MainActivity.this, CropperActivity.class);
        intent.putExtra("val",val);
        startActivity(intent);
    }



}

