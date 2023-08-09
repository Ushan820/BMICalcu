package com.example.ushan.bmicalcu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.ushan.bmicalcu.MainActivity.firebaseAuth;
import static java.lang.Integer.parseInt;


public class signup extends AppCompatActivity {


    EditText etName,etAge,etPhone,etEmail,etPassword;
    RadioGroup rgGender;
    Button btnSubmit;
    RadioButton rgMale ,rgFemale;
    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



                progressDialog = new ProgressDialog(this);


                etName = (EditText) findViewById(R.id.etName);
                etAge = (EditText) findViewById(R.id.etAge);
                etPhone = (EditText) findViewById(R.id.etPhone);
                etEmail = (EditText)findViewById(R.id.etEmail);
                rgGender = (RadioGroup) findViewById(R.id.rgGender);
                rgMale = (RadioButton) findViewById(R.id.rgMale);
                rgFemale = (RadioButton) findViewById(R.id.rgFemale);
                btnSubmit = (Button) findViewById(R.id.btnSubmit);

                etPassword = (EditText) findViewById(R.id.etPassword);


                btnSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        final String m = etName.getText().toString();
                        final String n = etAge.getText().toString();
                        final String o = etPhone.getText().toString();
                        final String e = etEmail.getText().toString();
                        final String pass = etPassword.getText().toString();
                        final String male = rgMale.getText().toString();
                        final String female = rgFemale.getText().toString();






                        if (m.isEmpty())
                        {
                            etName.setError("Name is Empty");
                            etName.requestFocus();
                            return;

                        }
                        else if (TextUtils.isDigitsOnly(m))
                        {
                            etName.setError("Invalid Name");
                            etName.requestFocus();
                            return;

                        }
                        else if (n.isEmpty()) {
                            etAge.setError("Age is empty");
                            etAge.requestFocus();
                            return;
                        }


                        final int p = parseInt(n);


                        if (p > 122 || p < 1) {
                            etAge.setError("Enter correct age");
                            etAge.requestFocus();
                            return;
                        }
                        else if (o.length() != 10) {

                            etPhone.setError("Enter correct phone number");
                            etPhone.requestFocus();
                            return;
                        }
                        else if (e.length() == 0) {
                            etEmail.setError("Invalid E-mail Address");
                            etEmail.requestFocus();
                            return;
                        }
                        else if (!Patterns.EMAIL_ADDRESS.matcher(e).matches())
                        {
                            etEmail.setError("Invalid E-mail Address");
                            etEmail.requestFocus();
                            return;
                        } else if (pass.length() == 0) {
                            etPassword.setError("Enter password with atleast 8 chacters");
                            etPassword.requestFocus();
                            return;
                        } else if (pass.length() < 8) {
                            etPassword.setError("Enter password with atleast 8 chacters");
                            etPassword.requestFocus();
                            return;
                        }
                        else {
                            progressDialog.setMessage("Processing....");
                            progressDialog.show();


                            firebaseAuth.createUserWithEmailAndPassword(e, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {


                                        try {
                                            FileOutputStream fos1 = openFileOutput("f1.txt",MODE_PRIVATE);
                                            fos1.write(m.getBytes());
                                            fos1.close();
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }


                                        try {
                                            FileOutputStream fos2 = openFileOutput("f2.txt",MODE_PRIVATE);
                                            fos2.write(n.getBytes());
                                            fos2.close();
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }


                                        try {
                                            FileOutputStream fos3 = openFileOutput("f3.txt",MODE_PRIVATE);
                                            fos3.write(o.getBytes());
                                            fos3.close();
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }


                                        try {
                                            FileOutputStream fos4 = openFileOutput("f4.txt",MODE_PRIVATE);
                                            fos4.write(e.getBytes());
                                            fos4.close();
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }


                                        try {
                                            FileOutputStream fos5 = openFileOutput("f5.txt",MODE_PRIVATE);
                                            fos5.write(male.getBytes());
                                            fos5.close();
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }

                                        try {
                                            FileOutputStream fos6 = openFileOutput("f6.txt",MODE_PRIVATE);
                                            fos6.write(female.getBytes());
                                            fos6.close();
                                        } catch (FileNotFoundException e1) {
                                            e1.printStackTrace();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }



                                        //personal Info Database storage Start


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
                                        DatabaseReference myTopchild1 = user.child(str3);
                                        DatabaseReference mychiild1ref = myTopchild1.child("Personal_Info");
                                        DatabaseReference mychiild2ref = mychiild1ref.child("NAME");
                                        DatabaseReference mychiild3ref = mychiild1ref.child("AGE");
                                        DatabaseReference mychiild4ref = mychiild1ref.child("PHONE");
                                        DatabaseReference mychiild5ref = mychiild1ref.child("EMAIL");
                                        DatabaseReference mychiild6ref = mychiild1ref.child("PASS");
                                        DatabaseReference mychiild7ref = mychiild1ref.child("GENDER");



                                        mychiild2ref.push().setValue(m);
                                        mychiild3ref.push().setValue(n);
                                        mychiild4ref.push().setValue(o);
                                        mychiild5ref.push().setValue(e);
                                        mychiild6ref.push().setValue(pass);
                                        if(rgMale.isChecked()) {
                                            mychiild7ref.push().setValue(male);

                                        }
                                        else if(rgFemale.isChecked())
                                        {
                                            mychiild7ref.push().setValue(female);

                                        }

                                        //personal info Database Storage End

                                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();



                                        progressDialog.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), welcome.class);
                                        intent.putExtra("k1", m);
                                        intent.putExtra("k2",n);
                                        intent.putExtra("k3",o);
                                        intent.putExtra("k5",e);
                                        if(rgMale.isChecked()) {
                                            intent.putExtra("k5",male);

                                        }
                                        else if(rgFemale.isChecked())
                                        {
                                            intent.putExtra("k5",female);

                                        }
                                        startActivity(intent);


                                    } else {
                                        Toast.makeText(getApplicationContext(), "Failed to register please try again", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                        }

                    }
                });

    }

}

