package com.example.signature;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import static android.os.Environment.getExternalStorageDirectory;

public class SignatureActivity extends AppCompatActivity {
    private static final int PERMISSION_EXTERNAL = 1;
    public static final  int RESULT_IMAGE = 23;
    FirstSurfaceView viewSurfaceview;
    Button btnClear;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        viewSurfaceview = findViewById(R.id.surfaceView);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewSurfaceview.reDraw();
            }
        });

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SignatureActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //没有授权，则申请授权
                    //ActivityCompat.requestPermissions() 参数一：context 参数二：申请的权限名数组 参数三：请求码，要求唯一值
                    ActivityCompat.requestPermissions(SignatureActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_EXTERNAL);
                } else {
                    save();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PERMISSION_EXTERNAL:
                if (resultCode != Activity.RESULT_OK) return;
                save();
                break;
        }
    }

    private void save() {
        //记录文件路径，用于返回显示
        String path = "";
        Bitmap bitmap = viewSurfaceview.getBitmap();
        if (bitmap != null) {
            try {
                /**
                 * 得到的路径/storage/emulated/0/Android/data/yourPackageName/files
                 * 这个目录会在应用被卸载的时候删除，而且访问这个目录不需要动态申请STORAGE权限。
                 * 如果这个目录不存在，系统会自动帮你创建
                 */
                File dir = getExternalFilesDir(null);
                // 图片文件路径 使用时间戳为文件名
                File file = new File(dir, System.currentTimeMillis() +".png");
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                os.flush();
                os.close();
                Toast.makeText(this, "存储完成", Toast.LENGTH_SHORT).show();
                path = file.getAbsolutePath().toString();
            } catch (Exception e) {
                Toast.makeText(this, "存储失败", Toast.LENGTH_SHORT).show();
            }
            if(path.length() > 0){
                Intent intent = new Intent();
                intent.putExtra("path",path);
                setResult(RESULT_IMAGE,intent);
                finish();
            }
        }
    }
}
