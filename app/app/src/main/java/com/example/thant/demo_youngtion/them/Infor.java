package com.example.thant.demo_youngtion.them;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thant.demo_youngtion.MainActivity;
import com.example.thant.demo_youngtion.Pay_activity;
import com.example.thant.demo_youngtion.R;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Infor extends AppCompatActivity {
    public static ListView listView;
    Handler handler;
    String s;
    ArrayList<String> arrayList;
    ArrayList<String> arrayList1;
    ArrayList<String> arrayList2;
    ArrayList<String> arrayList3;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor);
        listView=(ListView) findViewById(R.id.listview);
        Intent intent=getIntent();
        arrayList=intent.getStringArrayListExtra("symbols");
        arrayList1=intent.getStringArrayListExtra("allbounty");
        arrayList2=intent.getStringArrayListExtra("balance");
        arrayList3=intent.getStringArrayListExtra("company");
        System.out.println(arrayList.size()+"fksdjkfsdjk");
        Listcoin listcoin = new Listcoin(this, R.layout.information, arrayList, arrayList1, arrayList2, arrayList3);
        listView.setAdapter(listcoin);

    }

}
