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
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class login_Activity extends AppCompatActivity {


    TextView tvLogin, tvRegister;
    EditText etEmail2, etPassword2;
    Button btnLogin;

    ProgressDialog progressDialog;
    FirebaseAuth Auth;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);


        Auth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);



        etEmail2 = (EditText) findViewById(R.id.etEmail2);
        etPassword2 = (EditText) findViewById(R.id.etPassword2);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        tvRegister = (TextView)findViewById(R.id.tvRegister);
        tvLogin = (TextView)findViewById(R.id.tvLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String ema = etEmail2.getText().toString();
                final String passwd = etPassword2.getText().toString();





                if (ema.length() == 0) {
                    etEmail2.setError("Invalid E-mail Address");
                    etEmail2.requestFocus();
                    return;
                }
                else if(TextUtils.isEmpty(ema))
                {
                    etEmail2.setError("Invalid E-mail Address");
                    etEmail2.requestFocus();
                    return;

                }

                else if (!Patterns.EMAIL_ADDRESS.matcher(ema).matches()) {
                    etEmail2.setError("Invalid E-mail Address");
                    etEmail2.requestFocus();
                    return;
                } else if (passwd.length() == 0) {
                    etPassword2.setError("Enter password with atleast 8 chacters");
                    etPassword2.requestFocus();
                    return;
                } else if (passwd.length() < 8) {
                    etPassword2.setError("Enter password with atleast 8 chacters");
                    etPassword2.requestFocus();
                    return;
                } else {

                    progressDialog.setMessage("Processing...");
                    progressDialog.show();

                    Auth.signInWithEmailAndPassword(ema, passwd).addOnCompleteListener(login_Activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {



                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Intent intent = new Intent(getApplicationContext(), welcome.class);
                                        startActivity(intent);
                                        finish();

                                    } else {

                                        Toast.makeText(getApplicationContext(), "Error in Sign In", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            }
                    );


                }


            }
        });



        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i  = new Intent(getApplicationContext(),signup.class);
                startActivity(i);
            }
        });





    }

}

































