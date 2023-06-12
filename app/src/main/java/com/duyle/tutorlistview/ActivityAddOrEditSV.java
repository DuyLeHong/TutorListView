package com.duyle.tutorlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAddOrEditSV extends AppCompatActivity {

    boolean isAddSv;

    public static final int MODE_THEM_SV = 1;
    public static final int MODE_SUA_SV = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_edit_sv);

        TextView tvTitle = findViewById(R.id.tv_title);

        EditText edtTen = findViewById(R.id.edt_name);
        EditText edtMssv = findViewById(R.id.edt_mssv);

        isAddSv = getIntent().getBooleanExtra(MainActivity.KEY_MODE_LAYOUT, false);
        if (isAddSv) {
            tvTitle.setText("Thêm SV");
        } else {
            tvTitle.setText("Sửa SV");

            StudentModel svModel = (StudentModel) getIntent().getSerializableExtra("sv");
            edtTen.setText(svModel.getsName());
            edtMssv.setText(svModel.getMssv());

        }

        Button btnSave = findViewById(R.id.btn_save);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tenSV = edtTen.getText().toString();
                String mssv = edtMssv.getText().toString();

                StudentModel svModel = new StudentModel(tenSV, mssv, R.mipmap.ava2);
                Intent data = new Intent();
                data.putExtra("sv", svModel);

                if (isAddSv) {
                    setResult(MODE_THEM_SV, data);
                } else {
                    setResult(MODE_SUA_SV, data);
                }


                finish();
            }
        });
    }
}