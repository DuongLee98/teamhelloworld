package com.example.thant.demo_youngtion;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.thant.demo_youngtion.R;
import com.github.nkzawa.emitter.Emitter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.xml.transform.Result;
public class Scanner extends AppCompatActivity {
    TextView tv_qr_readTxt;
    TextView sendtext;
    Spinner coin_send;
    Spinner coin_recieve;
    EditText val;
    String s1, s2;
    String ar1[]={"HW","BE", "WN"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        IntentIntegrator integrator = new IntentIntegrator(Scanner.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
        val=(EditText)findViewById(R.id.editText);
        tv_qr_readTxt = (TextView) findViewById(R.id.tv_qr_readTxt);
        sendtext=(TextView) findViewById(R.id.tv_sent);
        Intent intent= getIntent();
        sendtext.setText(intent.getStringExtra("send"));
        coin_recieve=(Spinner) findViewById(R.id.recieve);
        coin_send=(Spinner) findViewById(R.id.send);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ar1);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        coin_send.setAdapter(adapter);
        coin_recieve.setAdapter(adapter);
        coin_send.setOnItemSelectedListener(new MyProcessEvent());
        coin_recieve.setOnItemSelectedListener(new hay());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");

            } else {
                Log.e("Scan", "Scanned");

               try{
                   tv_qr_readTxt.setText(result.getContents());
               }
               catch (Exception e){

               }
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    public void chuyentien(View view){
        Scanner.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    JSONObject create = new JSONObject();
                    create.put("from",sendtext.getText().toString());
                    create.put("ftype",s1);
                    create.put("to",tv_qr_readTxt.getText().toString());
                    create.put("ttype",s2);
                    create.put("val",val.getText().toString());
                    MainActivity.mSocket.emit("send",create);

                    MainActivity.mSocket.on("rpsend", new Emitter.Listener() {
                        @Override
                        public void call(Object... args)
                        {
                            JSONObject rpcreate = (JSONObject)args[0];
                            try {
                                boolean kt= rpcreate.getBoolean("cd");
                                System.out.println(rpcreate.getString("msg"));
                                JSONArray array=rpcreate.getJSONArray("symbols"); // tên loại điểm thưởng
                                JSONArray array1=rpcreate.getJSONArray("allbounty"); // tổng điểm thưởng đã tích
                                JSONArray array2=rpcreate.getJSONArray("balance"); //số dư
                                JSONArray array3=rpcreate.getJSONArray("company");
                                ArrayList<String> arrayList= new ArrayList<>();
                                ArrayList<String> arrayList1= new ArrayList<>();
                                ArrayList<String> arrayList2= new ArrayList<>();
//                                arrayList=doi(array);
//                                arrayList1=doi(array1);
//                                arrayList2=doi(array2);


                                if(kt==true){
                                    Toast.makeText(Scanner.this,"giao dịch thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(Scanner.this,Pay_activity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Scanner.this,"không hợp lệ",Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    private class MyProcessEvent implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            s1=ar1[arg2];
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            s1=ar1[0];
        }
    }
    private class hay implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
            s2=ar1[arg2];
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            s2=ar1[0];
        }
    }
}