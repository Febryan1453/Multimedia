package com.febryan.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.febryan.multimedia.databinding.ActivityMainBinding;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnPlay.setEnabled(true);
        binding.btnPause.setEnabled(false);
        binding.btnResume.setEnabled(false);
        binding.btnStop.setEnabled(false);

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.musikk);
        try {
            mediaPlayer.setDataSource(this, uri);
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        binding.btnPlay.setOnClickListener(v -> {
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            }catch (IOException e){
                e.printStackTrace();
                Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
            binding.btnPlay.setEnabled(false);
            binding.btnPause.setEnabled(true);
            binding.btnResume.setEnabled(false);
            binding.btnStop.setEnabled(true);
        });

        binding.btnPause.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) mediaPlayer.pause();

            binding.btnPlay.setEnabled(false);
            binding.btnPause.setEnabled(false);
            binding.btnResume.setEnabled(true);
            binding.btnStop.setEnabled(false);

        });

        binding.btnStop.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) mediaPlayer.stop();

            binding.btnPlay.setEnabled(true);
            binding.btnPause.setEnabled(false);
            binding.btnResume.setEnabled(false);
            binding.btnStop.setEnabled(false);
        });

        binding.btnResume.setOnClickListener(v -> {
            mediaPlayer.start();
            binding.btnPlay.setEnabled(false);
            binding.btnPause.setEnabled(true);
            binding.btnResume.setEnabled(false);
            binding.btnStop.setEnabled(true);
        });

        binding.btnVideo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
            startActivity(intent);
        });

        binding.btnVideoStreaming.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VideoStreamingActivity.class);
            startActivity(intent);
        });

    }
}