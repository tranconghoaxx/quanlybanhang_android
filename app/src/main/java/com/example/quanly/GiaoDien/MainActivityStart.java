package com.example.quanly.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.quanly.R;

import ThuVien.progressbaranimation;
import ThuVien.setinterval_animation;

public class MainActivityStart extends MenuActivity {

    //ProgressBar
    ProgressBar progressBar;
    TextView percent;
    //Hooks
    ImageView img_cat;
    //animation
    Animation bottomanimation, first_anima, second_anima, third_anima, fourth_anima, fifth_anima, sixth_anima, btnAnimation, btnAnimation_click, img_animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_start);

        first_anima = AnimationUtils.loadAnimation(this, R.anim.first_animation);
        second_anima = AnimationUtils.loadAnimation(this, R.anim.second_anima);
        third_anima = AnimationUtils.loadAnimation(this, R.anim.third_anima);
        fourth_anima = AnimationUtils.loadAnimation(this, R.anim.fouth_anima);
        fifth_anima = AnimationUtils.loadAnimation(this, R.anim.fifth_anima);
        sixth_anima = AnimationUtils.loadAnimation(this, R.anim.sixth_anima);
        bottomanimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animaton);
        btnAnimation = AnimationUtils.loadAnimation(this, R.anim.buttom_anima);
        btnAnimation.setStartOffset(3000);
        btnAnimation_click = AnimationUtils.loadAnimation(this,R.anim.btn_to_click);
        setinterval_animation setinterval = new setinterval_animation(0.2,20);
        btnAnimation_click.setInterpolator(setinterval);
        img_animation = AnimationUtils.loadAnimation(this,R.anim.img_animation);
        setControl();
        setAnima();
        //img
        AnimationDrawable runningcat = (AnimationDrawable)img_cat.getDrawable();
        runningcat.start();

        setProgressAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivityStart.this,MainActivityTrangChu.class);
                startActivity(intent);
            }
        },3000);
    }

    private void setProgressAnimation() {
        progressbaranimation progress = new progressbaranimation(this, progressBar, percent, 0, 100f);
        progress.setDuration(2900);
        progressBar.setAnimation(progress);
    }

    private void setAnima() {
        img_cat.setAnimation(img_animation);
        percent.setAnimation(img_animation);
    }

    private void setControl() {

        img_cat = findViewById(R.id.img_cat);

        //progress
        progressBar = findViewById(R.id.progress_id);
        percent = findViewById(R.id.percent);
        progressBar.setMax(100);
        progressBar.setScaleY(3f);
    }
}
