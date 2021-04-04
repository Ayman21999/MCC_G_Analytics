package com.example.googleanalyies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ShowProducts extends AppCompatActivity {
    FirebaseFirestore fire;
    long time;
    long end;
    long start;

            CollectionReference ref;
    List<Product> sports;
    List<Product> clothes;
    List<Product> phone;
    List<Product> laptop;
    FirebaseAnalytics firebaseAnalytics;
    DocumentReference docref;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        firebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        toolbar = findViewById(R.id.toolbar2);
        fire = FirebaseFirestore.getInstance();
        ref = fire.collection("timer");
        if (getIntent().hasExtra("sport")) {
            toolbar.setTitle("Sport category");
            firebaseAnalytics.setCurrentScreen(this, "Sport category", null);
            docref = ref.document("sport");
            // Sport products
            Product football = new Product();
            football.setName("Football");
            Product basketball = new Product();
            basketball.setName("basketball");
            Product volley = new Product();
            volley.setName("volley");
            sports = new ArrayList<>();
            sports.add(football);
            sports.add(basketball);
            sports.add(volley);
            ListView list = findViewById(R.id.list);
            ProductAdapter adapter = new ProductAdapter(sports, getApplicationContext(), R.layout.activity_main);
            list.setAdapter(adapter);

        } else if (getIntent().hasExtra("clothes")) {
            toolbar.setTitle("Clothes category");
            firebaseAnalytics.setCurrentScreen(this, "Clothes category", null);
            docref = ref.document("Clothes");

            // Clothes Products
            Product t_shirt = new Product();
            t_shirt.setName("T-shirt");
            Product jacket = new Product();
            jacket.setName("Jacket");
            Product hat = new Product();
            hat.setName("Hat");
            clothes = new ArrayList<>();
            clothes.add(t_shirt);
            clothes.add(jacket);
            clothes.add(hat);
            ListView list = findViewById(R.id.list);
            ProductAdapter adapter = new ProductAdapter(clothes, getApplicationContext(), R.layout.element_design);
            list.setAdapter(adapter);


        } else if (getIntent().hasExtra("phone")) {
            toolbar.setTitle("Phone category");
            firebaseAnalytics.setCurrentScreen(this, "Phone category", null);
            docref = ref.document("smart phone");

            //SmartPhone Products
            Product samsung = new Product();
            samsung.setName("Samsung");
            Product sony = new Product();
            sony.setName("Sony");
            Product iphone = new Product();
            iphone.setName("iphone");
            phone = new ArrayList<>();
            phone.add(sony);
            phone.add(samsung);
            phone.add(iphone);
            ListView list = findViewById(R.id.list);
            ProductAdapter adapter = new ProductAdapter(phone, getApplicationContext(), R.layout.element_design);
            list.setAdapter(adapter);


        } else if (getIntent().hasExtra("laptop")) {
            //laptops Products
            toolbar.setTitle("Laptop category");
            firebaseAnalytics.setCurrentScreen(this, "Laptop category", null);
            docref = ref.document("Laptop");

            Product acer = new Product();
            acer.setName("Acer");
            Product hp = new Product();
            hp.setName("hp");
            Product dell = new Product();
            dell.setName("Dell");
            laptop = new ArrayList<>();
            laptop.add(acer);
            laptop.add(hp);
            laptop.add(dell);
            ListView list = findViewById(R.id.list);
            ProductAdapter adapter = new ProductAdapter(laptop, getApplicationContext(), R.layout.element_design);
            list.setAdapter(adapter);

        }


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
        docref.update("spend", FieldValue.increment(time));

    }
}
