package com.example.clase81;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.example.clase81.databinding.ActivityMainBinding;
import com.example.clase81.databinding.ActivityNotificationBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        binding.textView3.setText(pid);

    }
}