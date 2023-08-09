package com.example.ushan.bmicalcu;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.example.ushan.bmicalcu.MainActivity.firebaseAuth;


public class welcome extends AppCompatActivity {

    TextView tvHeight, tvFeet, tvInch, tvWeight, tvWelcome;
    Spinner spFeet, spInch;
    EditText etWeight;
    Button btnCalculate, btnViewHistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvHeight = (TextView)findViewById(R.id.tvHeight);
        tvFeet = (TextView)findViewById(R.id.tvFeet);
        tvInch = (TextView)findViewById(R.id.tvInch);
        tvWeight = (TextView)findViewById(R.id.tvWeight);
        tvWelcome = (TextView)findViewById(R.id.tvWelcome);
        etWeight = (EditText)findViewById(R.id.etWeight);
        spFeet = (Spinner)findViewById(R.id.spFeet);
        spInch = (Spinner)findViewById(R.id.spInch);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
        btnViewHistory = (Button)findViewById(R.id.btnViewHistory);


        Intent intent  = getIntent();
        final String name = intent.getStringExtra("k1");
        final String age = intent.getStringExtra("k2");
        final String phone = intent.getStringExtra("k3");
        final String e_mail = intent.getStringExtra("k4");
        final String gender = intent.getStringExtra("k5");


//---------------------------------------------------------------------------------------


        String line1;
        StringBuffer sb1 = new StringBuffer();
        String str1 = null;
        try {
            FileInputStream fis1 = openFileInput("f1.txt");
            InputStreamReader isr = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr);
            while ((line1 = br.readLine()) != null) {
                sb1.append(line1);
            }
            str1 = sb1.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String line2;
        StringBuffer sb2 = new StringBuffer();
        String str2 = null;
        try {
            FileInputStream fis2 = openFileInput("f2.txt");
            InputStreamReader isr = new InputStreamReader(fis2);
            BufferedReader br = new BufferedReader(isr);
            while ((line2 = br.readLine()) != null) {
                sb2.append(line2);
            }
            str2 = sb2.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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


        String line4;
        StringBuffer sb4 = new StringBuffer();
        String str4 = null;
        try {
            FileInputStream fis4 = openFileInput("f4.txt");
            InputStreamReader isr = new InputStreamReader(fis4);
            BufferedReader br = new BufferedReader(isr);
            while ((line4 = br.readLine()) != null) {
                sb4.append(line4);
            }
            str4 = sb4.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String line5;
        StringBuffer sb5 = new StringBuffer();
        String str5 = null;
        try {
            FileInputStream fis5 = openFileInput("f5.txt");
            InputStreamReader isr = new InputStreamReader(fis5);
            BufferedReader br = new BufferedReader(isr);
            while ((line5 = br.readLine()) != null) {
                sb5.append(line5);
            }
            str5 = sb5.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String line6;
        StringBuffer sb6 = new StringBuffer();
        String str6 = null;
        try {
            FileInputStream fis6 = openFileInput("f6.txt");
            InputStreamReader isr = new InputStreamReader(fis6);
            BufferedReader br = new BufferedReader(isr);
            while ((line6 = br.readLine()) != null) {
                sb6.append(line6);
            }
            str6 = sb6.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();



        }




//----------------------------------------------------------------------------------------

        tvWelcome.setText("Welcome "+str1);



        ArrayList<Integer> feet = new ArrayList<Integer>();


        int i;

        for (i=2;i<=9;i=i+1)
        {

            feet.add(i);
        }



        ArrayAdapter<Integer> feetAdapter = new ArrayAdapter<Integer>(

                this,android.R.layout.simple_spinner_item, feet);




        spFeet.setAdapter(feetAdapter);
        spFeet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               int height_in_feet = (Integer) adapterView.getItemAtPosition(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                int height_in_feet = (Integer) adapterView.getItemAtPosition(0);


            }
        });






        ArrayList<Integer> inchi = new ArrayList<>();



        int j1;

        for (j1=0;j1<=11;j1=j1+1)
        {

            inchi.add(j1);
        }



        ArrayAdapter<Integer> inchiAdapter = new ArrayAdapter<Integer>(
                this,android.R.layout.simple_spinner_item, inchi);





        spInch.setAdapter(inchiAdapter);
        spInch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String height_in_inch = adapterView.getItemAtPosition(i).toString();



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                 String height_in_inch = adapterView.getItemAtPosition(0).toString();
            }
        });



        final String finalStr1 = str1;
        final String finalStr2 = str2;
        final String finalStr3 = str3;
        final String finalStr4 = str4;
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {


                String height_in_feet_string = spFeet.getSelectedItem().toString();
                int height_in_feet_double = Integer.parseInt(height_in_feet_string);
                double height_in_meters_of_feet = (height_in_feet_double/3.281);


                String height_in_inch_string = spInch.getSelectedItem().toString();
                int height_in_inch_double = Integer.parseInt(height_in_inch_string);
                double height_in_meters_of_inch = (height_in_inch_double/39.37);

                double f_height_in_meters = (height_in_meters_of_feet + height_in_meters_of_inch);



                String weight_in_string = etWeight.getText().toString();


                if(weight_in_string.isEmpty())
                {
                    etWeight.setError("Invalid Weight");
                    etWeight.requestFocus();
                    return;
                }

                Double weight = Double.parseDouble(weight_in_string);

                if(weight > 635.0 || weight < 10.0 )
                {
                    etWeight.setError("Invalid weight");
                    return;
                }

                else {
                    Double bmi_in_double = ((weight) / (f_height_in_meters * f_height_in_meters));
                    String bmi = bmi_in_double.toString();


                    Intent i = new Intent(getApplicationContext(), Bmiresult.class);
                    i.putExtra("key1", bmi);
                    i.putExtra("key2", finalStr1);
                    i.putExtra("key3", finalStr2);
                    i.putExtra("key4", finalStr3);
                    i.putExtra("key5", finalStr4);
                    i.putExtra("key6", gender);
                    startActivity(i);

                }


            }
        });






        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        Intent i = new Intent(getApplicationContext(),History.class);
         startActivity(i);

            }
        });







    }



    public void onBackPressed() {



        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close the application");
        builder.setCancelable(false);


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //android.os.Process.killProcess(android.os.Process.myPid());

              //  welcome.super.onDestroy();


                Intent  il = new Intent(Intent.ACTION_MAIN);
                il.addCategory(il.CATEGORY_HOME);
                il.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(il);


            }
        });




        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });





        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }

        });



        AlertDialog alert = builder.create();
        alert.setTitle("Exit");
        alert.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.about )
        {
            Snackbar.make(findViewById(android.R.id.content),"Developed by Ushan Motwani" ,Snackbar.LENGTH_LONG).show();

        }
        if(item.getItemId() == R.id.logout)
        {
            firebaseAuth.signOut();
            Intent i  = new Intent(getApplicationContext(),login_Activity.class);
            startActivity(i);
            finish();
        }
        if(item.getItemId() == R.id.contact)
        {
            Intent c = new Intent(Intent.ACTION_DIAL);
            c.setData(Uri.parse("tel:"+"8208834606"));
            startActivity(c);
        }
        return super.onOptionsItemSelected(item);
    }
}


