package com.duyle.tutorlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityAddOrEditSV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_edit_sv);

        TextView tvTitle = findViewById(R.id.tv_title);

        boolean isAddSv = getIntent().getBooleanExtra(MainActivity.KEY_MODE_LAYOUT, false);
        if (isAddSv) {
            tvTitle.setText("Thêm SV");
        } else {
            tvTitle.setText("Sửa SV");
        }

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}