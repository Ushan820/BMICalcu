package com.example.ushan.bmicalcu;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;




public class MainActivity extends AppCompatActivity {


    ImageView ivMario;
    public static FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();

        ivMario = (ImageView) findViewById(R.id.ivMario);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.myanim);

        ivMario.startAnimation(animation);


        Picasso.with(getApplicationContext()).load("https://img.gifmagazine.net/gifmagazine/images/541533/original.gif").into(ivMario);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }

                if (firebaseAuth.getCurrentUser() == null) {

                    Intent intent = new Intent(MainActivity.this, login_Activity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Intent intent = new Intent(getApplicationContext(), welcome.class);
                    startActivity(intent);
                    finish();

                }

            }

        }).start();


    }

}


