
package com.example.userinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensecondUI();
            }
        });

    }
    public void opensecondUI(){
        Intent intent = new Intent(this, secondUI.class);
        startActivity(intent);
    }

    public void tapToAnimate(View view) {
        Button button = (Button)findViewById(R.id.button);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        MyBounceInterpolator interpolator= new MyBounceInterpolator(0.2, 20);
        animation.setInterpolator(interpolator);
        button.startAnimation(animation);


    }
}