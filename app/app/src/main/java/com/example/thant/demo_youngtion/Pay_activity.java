package com.example.thant.demo_youngtion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thant.demo_youngtion.them.Click_QR;
import com.example.thant.demo_youngtion.them.Infor;
import com.example.thant.demo_youngtion.them.Listcoin;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class Pay_activity extends AppCompatActivity {
    ImageButton QRcode, user, quet, pay, setting, quanli;
    TextView tv;
    ArrayList<String> arrayList;
    ArrayList<String> arrayList1;
    ArrayList<String> arrayList2;
    ArrayList<String> arrayList3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);
        anhxa();
        Intent intent=getIntent();
       arrayList= intent.getStringArrayListExtra("symbols");
        arrayList1=intent.getStringArrayListExtra("allbounty");
        arrayList2=intent.getStringArrayListExtra("balance");
       arrayList3= intent.getStringArrayListExtra("company");
        System.out.println(MainActivity.value1);
        if(MainActivity.value1.length()>0) {
            tv.setText(MainActivity.value1);
            create(MainActivity.value1);
        }
    }
    public void anhxa(){
        user=(ImageButton) findViewById(R.id.user);
        QRcode=(ImageButton) findViewById(R.id.QRcode);
        quet=(ImageButton) findViewById(R.id.quet);
        pay=(ImageButton) findViewById(R.id.pay);
        setting=(ImageButton) findViewById(R.id.out);
        quanli=(ImageButton) findViewById(R.id.quanli);
        tv= (TextView) findViewById(R.id.name);
    }
    public void scan(View v){
        Intent intent= new Intent(Pay_activity.this, CreateQr.class);
        startActivity(intent);
    }
    public void pay(View v){
        Intent intent= new Intent(Pay_activity.this, Scanner.class);
        intent.putExtra("send",MainActivity.value1);
        startActivity(intent);
    }
    public void clickQR(View view){
        Intent intent= new Intent(Pay_activity.this, Click_QR.class);
        intent.putExtra("string", MainActivity.value1);
        startActivity(intent);
    }
    public void create(String s){
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(s, BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageButton) findViewById(R.id.QRcode)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    public void sign_out(View view){
        Intent intent = new Intent(Pay_activity.this, MainActivity.class);
        startActivity(intent);
    }
    public void infor(View view){
        Intent intent= new Intent(Pay_activity.this, Infor.class);
        intent.putStringArrayListExtra("symbols",arrayList);
        intent.putStringArrayListExtra("allbounty",arrayList1);
        intent.putStringArrayListExtra("balance",arrayList2);
        intent.putStringArrayListExtra("company",arrayList3);
        startActivity(intent);
    }
}
