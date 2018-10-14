package com.example.thant.demo_youngtion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thant.demo_youngtion.them.Infor;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
   public static String value1;
    public static  com.github.nkzawa.socketio.client.Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://10.83.1.93:1998");
        } catch (URISyntaxException e) {}
    }
    Button btn;
    EditText edt, edt2;
    static String key="QR";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int permission_read_storage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int permission_camera = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        if (permission_read_storage != PackageManager.PERMISSION_GRANTED
                || permission_camera != PackageManager.PERMISSION_GRANTED) {
            makeRequest();

        }
    }
    private void makeRequest() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.VIBRATE,Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setContentView(R.layout.activity_main);
                    btn=(Button) findViewById(R.id.btn);
                    edt=(EditText) findViewById(R.id.acc);
                    edt2=(EditText) findViewById(R.id.pass);
                    mSocket.connect();

                } else {

                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }

                return;
            }
        }
    }
    public void Sigh_in(View v){
       send();
    }
    public void create(View view){
        Intent intent= new Intent(MainActivity.this,Đang_ki.class);
        startActivity(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }
    public void send(){
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject create = new JSONObject();
                    create.put("user",edt.getText().toString());
                    create.put("pass",edt2.getText().toString());
                    MainActivity.mSocket.emit("login",create);

                    MainActivity.mSocket.on("rplogin", new Emitter.Listener() {
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
                                ArrayList<String> arrayList3= new ArrayList<>();
                                arrayList=doi(array);
                                arrayList1=doi(array1);
                                arrayList2=doi(array2);
                                arrayList3=doi(array3);

                                if(kt==true){
                                    Intent intent = new Intent(MainActivity.this, Pay_activity.class);
                                    intent.putStringArrayListExtra("symbols",arrayList);
                                    intent.putStringArrayListExtra("allbounty",arrayList1);
                                    intent.putStringArrayListExtra("balance",arrayList2);
                                    intent.putStringArrayListExtra("company",arrayList3);
                                   value1=edt.getText().toString();
                                    if(edt.getText().toString().length()>0&&edt2.getText().toString().length()>0){
                                        startActivity(intent);
                                    }
                                    else{
                                        Toast.makeText(MainActivity.this,"tài khoản hoặc mât khẩu không hợp lệ",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    Toast.makeText(MainActivity.this,"tài khoản hoặc mât khẩu không hợp lệ",Toast.LENGTH_SHORT).show();
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
    public static ArrayList<String> doi(JSONArray u) throws JSONException {
        ArrayList<String> arrayList= new ArrayList<>();
        for(int i=0;i<u.length();i++){
            arrayList.add(u.getString(i).toString());
        }
        return arrayList;
    }

}
