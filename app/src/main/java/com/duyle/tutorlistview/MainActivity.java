package com.duyle.tutorlistview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<StudentModel> arrSv = new ArrayList<>();
    AdapterSV adapterSV;

    public static final String KEY_MODE_LAYOUT = "add_sv";

    ActivityResultLauncher<Intent> getData = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == ActivityAddOrEditSV.MODE_THEM_SV) {
                        Intent data = result.getData();

                        StudentModel svModel = (StudentModel) data.getSerializableExtra("sv");
                        arrSv.add(svModel);
                        adapterSV.notifyDataSetChanged();

                        luuDuLieu();

                    } else if (result.getResultCode() == ActivityAddOrEditSV.MODE_SUA_SV) {
                        Intent data = result.getData();

                        StudentModel svModel = (StudentModel) data.getSerializableExtra("sv");
                        arrSv.set(indexSVEdit, svModel);
                        adapterSV.notifyDataSetChanged();

                        luuDuLieu();
                    }
                }
            });

    private int indexSVEdit = -1;

    String FILE_NAME = "sv.txt";
    private void luuDuLieu () {
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE_NAME, MODE_PRIVATE);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(arrSv);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (Exception e) {

        }
    }

    private void docDulieu () {

        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            arrSv = (ArrayList<StudentModel>) objectInputStream.readObject();

            objectInputStream.close();

            fileInputStream.close();


        } catch (Exception e) {

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddSV = findViewById(R.id.btn_add_sv);

        btnAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityAddOrEditSV.class);

                intent.putExtra(KEY_MODE_LAYOUT, true);

                getData.launch(intent);
            }
        });

//        btnEditSV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ActivityAddOrEditSV.class);
//
//                intent.putExtra(KEY_MODE_LAYOUT,  false);
//
//                startActivity(intent);
//            }
//        });

        ListView lvStudent = findViewById(R.id.lv_student);

        docDulieu();

        if (arrSv.size() == 0) {

            arrSv.add(new StudentModel("Nguyễn Văn Long", "PH11311", R.mipmap.ava2));
            arrSv.add(new StudentModel("Trần Tuấn Anh", "PH14511", R.mipmap.ava3));
            arrSv.add(new StudentModel("Lê Văn Minh", "PH116712", R.mipmap.ava4));

        }



        adapterSV = new AdapterSV(this, arrSv);

        lvStudent.setAdapter(adapterSV);

//        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(MainActivity.this, arrSv.get(i).getsName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private class AdapterSV extends BaseAdapter {

        MainActivity activity;
        ArrayList<StudentModel> list;

        public AdapterSV(MainActivity activity, ArrayList<StudentModel> list) {
            this.activity = activity;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = activity.getLayoutInflater();
            view = inflater.inflate(R.layout.item_sv, viewGroup, false);

            StudentModel svModel = list.get(i);

            ImageView ivAvatar = view.findViewById(R.id.iv_avatar);
            TextView tvName = view.findViewById(R.id.tv_name);
            TextView tvMssv = view.findViewById(R.id.tv_mssv);

            Button btnXoa = view.findViewById(R.id.btn_xoa);

            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    arrSv.remove(i);
                    notifyDataSetChanged();

                    luuDuLieu();
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    indexSVEdit = i;

                    Intent intent = new Intent(getApplicationContext(), ActivityAddOrEditSV.class);

                    intent.putExtra(KEY_MODE_LAYOUT, false);
                    intent.putExtra("sv", arrSv.get(i));

                    getData.launch(intent);

                    //startActivity(intent);
                }
            });

            ivAvatar.setImageResource(svModel.getIconId());
            tvName.setText(svModel.getsName());
            tvMssv.setText(svModel.getMssv());

            return view;
        }
    }
}