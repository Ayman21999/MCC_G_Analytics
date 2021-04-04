package com.example.googleanalyies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    long time;
    long end;
    long start;
    Button sport_bt;
    Button clothes_bt;
    Button phone_bt;
    Button laptop_bt;
    FirebaseFirestore firebaseFirestore;
    FirebaseFirestore firebaseFirestore2;
    CollectionReference ref2;
    CollectionReference ref;
    DocumentReference dcoref;
    DocumentReference docref2;

    int sport = 0;
    int clothres = 0;
    int laptop = 0;
    int phone = 0;
    ArrayList<Integer> intrse  = new ArrayList<>();

    FirebaseAnalytics analytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseFirestore = FirebaseFirestore.getInstance();
        ref = firebaseFirestore.collection("timer");
        dcoref = ref.document("Main");

        bindindEelements();
        OnClickmethods();
        UserIntrest();

        intrse.add(sport);
        intrse.add(phone);
        intrse.add(laptop);
        intrse.add(clothres);

        firebaseFirestore2 = FirebaseFirestore.getInstance();
        ref2 = firebaseFirestore2.collection("Times");


    }

    public void bindindEelements() {
        sport_bt = findViewById(R.id.soprt);
        clothes_bt = findViewById(R.id.clothes);
        phone_bt = findViewById(R.id.phoone);
        laptop_bt = findViewById(R.id.laptop);

    }

    public void OnClickmethods() {
        sport_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ShowProducts.class);
                i.putExtra("sport", "sport");
                sport++;
                docref2 = ref2.document("sport");
                docref2.update("numberOFentring", FieldValue.increment(sport));
                startActivity(i);

            }
        });

        clothes_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ShowProducts.class);
                i.putExtra("clothes", "clothes");
                clothres++;
                docref2 = ref2.document("Clothes");
                docref2.update("numberOFentring", FieldValue.increment(clothres));
                startActivity(i);
            }
        });

        laptop_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ShowProducts.class);
                i.putExtra("laptop", "laptop");
                docref2 = ref2.document("Laptop");
                laptop++;
                docref2.update("numberOFentring", FieldValue.increment(laptop));
                startActivity(i);
            }

        });

        phone_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ShowProducts.class);
                i.putExtra("phone", "smartPhone");
                docref2 = ref2.document("Phone");
                phone++;
                docref2.update("numberOFentring", FieldValue.increment(phone));
                startActivity(i);
            }
        });
    }

    public void UserIntrest() {

        //        if (sport > clothres && sport > laptop && sport >phone){
            Collections.addAll(intrse);
            for (int i = 0 ; i< intrse.size(); i ++){
                int intresting = Collections.max(intrse);
                analytics.setUserProperty("intrest", String.valueOf(intresting));
                Toast.makeText(this, intresting+"", Toast.LENGTH_SHORT).show();
            }

//        }else  if (clothres >  && sport > laptop && sport >phone)

    }

    @Override
    protected void onStart() {
        super.onStart();
        start = System.currentTimeMillis() / 1000;

    }

    @Override
    protected void onStop() {
        super.onStop();
        end = System.currentTimeMillis() / 1000;
        time = end - start;
        dcoref.update("time", FieldValue.increment(time));

    }


}
