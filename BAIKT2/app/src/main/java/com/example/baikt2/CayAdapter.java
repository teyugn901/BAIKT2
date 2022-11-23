package com.example.baikt2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CayAdapter extends FirebaseRecyclerAdapter<CayModel,CayAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CayAdapter(@NonNull FirebaseRecyclerOptions<CayModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull CayModel model) {
        holder.txtTenKH.setText(model.getTenKH());
        holder.txtTenTG.setText(model.getTenTG());
        holder.txtDacTinh.setText(model.getDacTinh());
        holder.txtMauLa.setText(model.getMauLa());

        Glide.with(holder.img.getContext())
                .load(model.getHinhAnh())
                .placeholder(R.drawable.tree)
                .circleCrop()
                .error(R.drawable.tree)
                .into(holder.img);

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_cay))
                        .setExpanded(true,1200)
                        .create();
                //dialogPlus.show();
                View view = dialogPlus.getHolderView();

                EditText anh = view.findViewById(R.id.editT_anh_ud);
                EditText TenKH = view.findViewById(R.id.editT_name_ud);
                EditText tenTG = view.findViewById(R.id.editT_tentinh_ud);
                EditText dactinh = view.findViewById(R.id.editT_gia_ud);
                EditText mauLa = view.findViewById(R.id.editT_mauLa_ud);



                Button btnUpdate = view.findViewById(R.id.button_Update);

                anh.setText(model.getHinhAnh());
                TenKH.setText(model.getTenKH());
                tenTG.setText(model.getTenTG());
                dactinh.setText(model.getDacTinh());
                mauLa.setText(model.getMauLa());

                dialogPlus.show();
                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("anh",anh.getText().toString());
                        map.put("tenKH",TenKH.getText().toString());
                        map.put("tenTG",tenTG.getText().toString());
                        map.put("dacTinh ",dactinh.getText().toString());
                        map.put("mauLa ",mauLa.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("cay")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.txtTenKH.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.txtTenKH.getContext(), "Error while Updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txtTenKH.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Delete data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("cay")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.txtTenKH.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView txtTenKH,txtTenTG, txtDacTinh,txtMauLa;
        ImageButton btnEdit,btnDelete;

        public  myViewHolder(@NonNull View itemView){
            super(itemView);

            img=(CircleImageView)  itemView.findViewById(R.id.img1);
            txtTenKH = (TextView)  itemView.findViewById(R.id.textView_tenTour);
            txtTenTG = (TextView)  itemView.findViewById(R.id.textView_tenTinh);
            txtDacTinh = (TextView)  itemView.findViewById(R.id.textView_gia);
            txtMauLa=(TextView) itemView.findViewById(R.id.textView_mauLa);
            btnEdit=(ImageButton) itemView.findViewById(R.id.button_edit);
            btnDelete=(ImageButton) itemView.findViewById(R.id.button_delete);
        }
    }
}