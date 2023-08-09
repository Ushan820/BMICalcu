package com.example.ushan.bmicalcu;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;





public class History extends AppCompatActivity {

    ListView lvhistory;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);



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








        lvhistory = (ListView)findViewById(R.id.lvhistory);

         final ArrayList<String> history = new ArrayList<String>();

         final ArrayAdapter<String> history_adapter =  new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,history);





        mychild1ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String history_all  =  dataSnapshot.getValue().toString();
                history.add(history_all);
                lvhistory.setAdapter(history_adapter);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }
}
