package com.example.thant.demo_youngtion.them;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thant.demo_youngtion.R;

import java.util.ArrayList;

public class Listcoin extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<String> listsymbol;
    ArrayList<String> listallbouty;
    ArrayList<String> listbalan;
    ArrayList<String> listcompany;

    public Listcoin(Context context, int layout, ArrayList<String> listsymbol, ArrayList<String> listallbouty, ArrayList<String> listbalan, ArrayList<String> listcompany) {
        this.context = context;
        this.layout = layout;
        this.listsymbol = listsymbol;
        this.listallbouty = listallbouty;
        this.listbalan = listbalan;
        this.listcompany = listcompany;
    }

    @Override
    public int getCount() {
        return listallbouty.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView =inflater.inflate(layout,null);
        TextView symbol= (TextView) convertView.findViewById(R.id.textView);
        TextView bouty= (TextView) convertView.findViewById(R.id.textView2);
        TextView balan= (TextView) convertView.findViewById(R.id.textView3);
        TextView company= (TextView) convertView.findViewById(R.id.textView4);
        symbol.setText(listsymbol.get(position).toString());
        bouty.setText(listallbouty.get(position).toString());
        balan.setText(listbalan.get(position).toString());
       company.setText(listcompany.get(position).toString());
        System.out.println(company);
        return convertView;
    }
}
