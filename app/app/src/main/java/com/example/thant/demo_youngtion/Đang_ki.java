package com.example.thant.demo_youngtion;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class Đang_ki extends AppCompatActivity {
//    private com.github.nkzawa.socketio.client.Socket mSocket;
//    {
//        try {
//            mSocket = IO.socket("http://10.83.1.93:1998");
//        } catch (URISyntaxException e) {}
//    }
    EditText edt1;
    EditText edt2;
    EditText edt3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ki);
        edt1=(EditText) findViewById(R.id.taikhoan);
        edt2=(EditText) findViewById(R.id.matkhau);
        edt3=(EditText) findViewById(R.id.sdt);
       // mSocket.connect();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mSocket.disconnect();
//    }
    public void create(View view){
        Đang_ki.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject create = new JSONObject();
                    create.put("user",edt1.getText().toString());
                    create.put("pass",edt2.getText().toString());
                    create.put("phone",edt3.getText().toString());
                    MainActivity.mSocket.emit("create",create);

                    MainActivity.mSocket.on("rpcreate", new Emitter.Listener() {
                        @Override
                        public void call(Object... args)
                        {
                            JSONObject rpcreate = (JSONObject)args[0];
                            try {
                                rpcreate.getBoolean("cd");
                                rpcreate.getString("msg");
                                if(rpcreate.getBoolean("cd")==true){
                                    Toast.makeText(Đang_ki.this,"bạn đã đăng kí thành công",Toast.LENGTH_SHORT).show();
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
}
