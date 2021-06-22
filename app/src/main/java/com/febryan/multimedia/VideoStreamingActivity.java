package com.febryan.multimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import com.febryan.multimedia.databinding.ActivityVideoStreamingBinding;

public class VideoStreamingActivity extends AppCompatActivity {

    private ActivityVideoStreamingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoStreamingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        String url = "https://www745.ff-02.com/token=XuWhia5usUXGVJA7spzZuw/1623762556/103.82.0.0/161/0/b6/69ca50d438ced8b7a90e932bbe7a0b60-1080p.mp4";

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.videoStreaming);
        binding.videoStreaming.setMediaController(mediaController);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.show();

        binding.videoStreaming.setVideoURI(Uri.parse(url));
        binding.videoStreaming.requestFocus();
        binding.videoStreaming.setOnPreparedListener(mp -> {
            progressDialog.dismiss();
            mp.start();
        });

    }
}