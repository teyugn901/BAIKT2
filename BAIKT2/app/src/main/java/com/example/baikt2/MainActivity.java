package com.example.baikt2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton ibAdd;
    TextView txtChao;

    CayAdapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<CayModel> options=
                new FirebaseRecyclerOptions.Builder<CayModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Cay"),CayModel.class)
                        .build();

        mainAdapter=new CayAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        ibAdd=(ImageButton) findViewById(R.id.button_add);
        ibAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddCayActivity.class));
            }
        });


    }
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.startListening();
    }

}

   /* Tạo project trên firebase
    Xây dựng app quản lý các loại cây xanh (tên khoa học, tên thường gọi, đặc tính, màu lá, hình ảnh...)
    Sign up, login vào app
    Hiển thị list cây xanh sau khi login
    Click vào item --> Chi tiết cây xanh (tự bố trí màu sắc, bố cục, hình thức hiển thị...)
        Thêm, xoá cây xanh ở list
        Dữ liệu luôn kết nối với firebase*/