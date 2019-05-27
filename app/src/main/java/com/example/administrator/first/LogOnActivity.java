package com.example.administrator.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class LogOnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);
    }
    public void logon(View btn){
        openConfig();
    }

    private void openConfig(){
        Intent config = new Intent (this,LogOn2Activity.class);

        startActivityForResult(config,1);
    }
}
