package com.example.ushan.bmicalcu;

import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;


public class Bmiresult extends AppCompatActivity {


    TextView tvCalculatedBmi,tvUnderweight,tvNormal,tvOverweight,tvObese;
    Button btnSave,btnShare;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiresult);




        String line3;
        StringBuffer sb3 = new StringBuffer();
        String str3 = null;
        try {
            FileInputStream fis3 = openFileInput("f3.txt");
            InputStreamReader isr = new InputStreamReader(fis3);
            BufferedReader br = new BufferedReader(isr);
            while ((line3 = br.readLine()) != null) {
                sb3.append(line3);
            }
            str3 = sb3.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }








        FirebaseDatabase firebaseDatabase  = FirebaseDatabase.getInstance();
        DatabaseReference user = firebaseDatabase.getReference();
        final DatabaseReference myTopchild2 = user.child(str3);
        final DatabaseReference mychild1ref = myTopchild2.child("Data");




        final Intent i =  getIntent();
        final String  bm1 = i.getStringExtra("key1");
        final String  name = i.getStringExtra("key2");
        final String  age = i.getStringExtra("key3");
        final String  phone = i.getStringExtra("key4");
        final String e_mail = i.getStringExtra("key5");
        final String gender_of_per = i.getStringExtra("key6");





        tvCalculatedBmi = (TextView)findViewById(R.id.tvCalculatedBmi);
        tvUnderweight = (TextView)findViewById(R.id.tvUnderweight);
        tvNormal = (TextView)findViewById(R.id.tvNormal);
        tvObese = (TextView)findViewById(R.id.tvObese);
        tvOverweight = (TextView)findViewById(R.id.tvOverweight);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnShare = (Button)findViewById(R.id.btnShare);

        int flag =0;


        double b = Double.parseDouble(bm1);

        final String precise_bmi = String.format("%.2f",b);



        if(b < 18.5)
        {
            tvCalculatedBmi.setText("Your BMI is " + precise_bmi+ " and you are Underweight");
            flag=1;
        }
        else if(b>=18.5 && b <25)
        {

            tvCalculatedBmi.setText("Your BMI is " +precise_bmi+ " and you are Normal");
            flag=2;
        }
        else if(b>=25 && b <=30)
        {
            tvCalculatedBmi.setText("Your BMI is " + precise_bmi+ " and you are Oveweight");
            flag=3;
        }
        else
        {
            tvCalculatedBmi.setText("Your BMI is " + precise_bmi+ " and you are Obese");
            flag=4;
        }



        if(flag==1)
        {
            tvUnderweight.setTextColor(Color.parseColor("#DC143C"));
            tvNormal.setTextColor(Color.parseColor("#7FFF00"));
            tvOverweight.setTextColor(Color.parseColor("#7FFF00"));
            tvObese.setTextColor(Color.parseColor("#7FFF00"));

        }
        else if(flag==2)
        {
            tvUnderweight.setTextColor(Color.parseColor("#7FFF00"));
            tvNormal.setTextColor(Color.parseColor("#DC143C"));
            tvOverweight.setTextColor(Color.parseColor("#7FFF00"));
            tvObese.setTextColor(Color.parseColor("#7FFF00"));


        }
        else if(flag==3)
        {
            tvUnderweight.setTextColor(Color.parseColor("#7FFF00"));
            tvNormal.setTextColor(Color.parseColor("#7FFF00"));
            tvOverweight.setTextColor(Color.parseColor("#DC143C"));
            tvObese.setTextColor(Color.parseColor("#7FFF00"));

        }
        else if(flag==4)
        {
            tvUnderweight.setTextColor(Color.parseColor("#7FFF00"));
            tvNormal.setTextColor(Color.parseColor("#7FFF00"));
            tvOverweight.setTextColor(Color.parseColor("#7FFF00"));
            tvObese.setTextColor(Color.parseColor("#DC143C"));

        }







        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {


                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date myDate = new Date();
                String filename = timeStampFormat.format(myDate);



                try {

                    String r = ("Date- "+filename+"  "+"Age- "+age+"  "+"BMI- "+precise_bmi);
                    mychild1ref.push().setValue(r);

                    Toast.makeText(getApplicationContext(), "Saved Successfully", Toast.LENGTH_SHORT).show();


                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Plz check your connection and try again..", Toast.LENGTH_SHORT).show();
                    return;
                }


            }

        });




        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i  = new Intent(Intent.ACTION_SENDTO);
                i.setData(Uri.parse("mailto:"+"kamalsir@ymail.com"));
                i.putExtra(Intent.EXTRA_TEXT,"Name = "+name+"age = "+age+"Phone= "+phone+"BMI ="+tvCalculatedBmi.getText().toString());
                startActivity(i);
            }
        });










    }


}
