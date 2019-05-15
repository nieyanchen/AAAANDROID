package com.example.administrator.first;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        TextView out = findViewById(R.id.txtout);
        TextView out3 = findViewById(R.id.txtout3);
        EditText inp = findViewById(R.id.inp );
        String str =inp.getText().toString();
        Button btn =findViewById(R.id.btn);
        btn.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        TextView inp =findViewById(R.id.inp);
        TextView out3 =findViewById(R.id.txtout3);
        double a =Double.parseDouble(inp.getText().toString());
        double b =( 9 * a ) / 5 + 32;
        out3.setText("转换后的华氏度为:"+String.valueOf(b)+"℉");
        }

    public void btn(View view) {
    }
}

