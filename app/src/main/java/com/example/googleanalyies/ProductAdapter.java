package com.example.googleanalyies;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
   List<Product>products = new ArrayList<>();
   Context context;
   int layout  ;

   public ProductAdapter(List<Product> products , Context context , int layout) {
    this.products = products;
    this.context = context;
    this.layout =layout;


    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View view1 = LayoutInflater.from(context).inflate(R.layout.element_design,null);
       Product p = products.get(i);

       TextView nameProduct = view1.findViewById(R.id.namepro);
       nameProduct.setText(p.getName());
       return view1;
    }
}