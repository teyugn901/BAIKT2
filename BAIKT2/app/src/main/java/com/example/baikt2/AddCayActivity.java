package com.example.baikt2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
public class AddCayActivity extends AppCompatActivity {

    EditText ed_tenTourAdd, ed_tenTinhAdd, ed_AnhAdd, ed_giaAdd, ed_moTaAdd;
    Button btnAdd;
    ImageButton btnBack;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /*ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.setTitle("Sinh vien updating");
        dialog.setCanceledOnTouchOutside(false);*/

        ed_tenTourAdd = (EditText)findViewById(R.id.editT_name_ud);
        ed_tenTinhAdd =(EditText) findViewById(R.id.editT_tentinh_ud);
        ed_giaAdd=(EditText) findViewById(R.id.editT_gia_ud);
        ed_AnhAdd = (EditText) findViewById(R.id.editT_anh_ud);
        ed_moTaAdd = (EditText) findViewById(R.id.editT_mauLa_ud);

        btnAdd = (Button) findViewById(R.id.button_save);
        btnBack = (ImageButton) findViewById(R.id.button_back);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog.show();
                insertData();
                clearAll();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void clearAll(){
        ed_tenTourAdd.setText("");
        ed_tenTinhAdd.setText("");
        ed_AnhAdd.setText("");
        ed_giaAdd.setText("");
        ed_moTaAdd.setText("");
    }
    private void insertData(){
        Map<String,Object> map= new HashMap<>();
        map.put("tenKH", ed_tenTourAdd.getText().toString());
        map.put("tenTG", ed_tenTinhAdd.getText().toString());
        map.put("dacTinh", ed_giaAdd.getText().toString());
        map.put("anh", ed_AnhAdd.getText().toString());
        map.put("mauLa",ed_moTaAdd.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("cay").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddCayActivity.this, "Data Insert Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCayActivity.this, "Error While Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
