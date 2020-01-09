package com.example.signature;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DrawTestActivity extends AppCompatActivity {

    private  MyDrawView myDrawView;
    private Button btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_test);
        myDrawView = findViewById(R.id.myDrawView);
        btnTest = findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDrawView.SetColor(Color.RED);
                myDrawView.invalidate();
            }
        });
    }
}
