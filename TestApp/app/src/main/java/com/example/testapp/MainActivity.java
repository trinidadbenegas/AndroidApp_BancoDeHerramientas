package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final String VIDEO_SAMPLE = "video";
    private VideoView mVideoView;

    private int mCurrentPosition = 0;
    private static final String PLAYBACK_TIME = "play_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoview);

        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(mediaController);

        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }




    }





    @Override
    protected void onStart() {
        super.onStart();
        initializePlayer();
    }
    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
    }



    private void initializePlayer() {

        Uri videoUri = getMedia(VIDEO_SAMPLE);
        mVideoView.setVideoURI(videoUri);

        if (mCurrentPosition > 0) {
            mVideoView.seekTo(mCurrentPosition);
        } else {
// Saltar a 1 muestra el primer cuadro del video.
            mVideoView.seekTo(1);
        }

        mVideoView.start();
    }

    private void releasePlayer() {
        mVideoView.stopPlayback();
    }



    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + mediaName);
    }



/*
    public void logout ( View v){
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.putExtra(LoginActivity.EXTRA_CLEAR_CREDENTIALS, true);
        startActivity(loginIntent);

    } */


    public void login( View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }


}