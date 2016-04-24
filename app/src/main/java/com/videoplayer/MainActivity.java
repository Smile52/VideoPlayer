package com.videoplayer;


import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Random;


import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class MainActivity extends AppCompatActivity {
    private io.vov.vitamio.widget.VideoView mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Vitamio.isInitialized(this);
        setContentView(R.layout.activity_main);
//        m_actionBar.hide();
        playfunction();

    }

    void playfunction(){
        String path = "http://192.168.1.106:8080/SmileFoodServer/Video/飘花电影piaohau.com末日崩塌HD1280高清中英双字.mkv";

        mVideoView = (io.vov.vitamio.widget.VideoView) findViewById(R.id.surface_view);
        mVideoView.setVideoPath(path);   //设置视频网络地址
//      mVideoView.setVideoURI(Uri.parse(path)); //也可以是本地,也可以是网络地址
        mVideoView.setMediaController(new io.vov.vitamio.widget.MediaController(this)); //设置媒体控制器
        mVideoView.setVideoLayout(io.vov.vitamio.widget.VideoView.VIDEO_LAYOUT_STRETCH, 0);   //设置视频的缩放参数,这里设置为拉伸
        mVideoView.requestFocus();
        //视频播放器的准备,此时播放器已经准备好了,此处可以设置一下播放速度,播放位置等等
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //此处设置播放速度为正常速度1
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        //当播放完成后,从头开始
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);   //转到第一帧
                mediaPlayer.start();     //开始播放
            }
        });

    }
}
