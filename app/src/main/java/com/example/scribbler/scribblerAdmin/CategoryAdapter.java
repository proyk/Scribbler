package com.example.scribbler.scribblerAdmin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scribbler.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CategoryAdapter extends FirebaseRecyclerAdapter<Categorymodel,CategoryAdapter.ViewHolder>{
    private Context context;
    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<Categorymodel> options) {
        super(options);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View cardView =layoutInflater.inflate(R.layout.item_card_category,parent,false);
        context=parent.getContext();
        return new ViewHolder(cardView);
    }



    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder,int position, @NonNull Categorymodel model) {
        holder.CName.setText(model.getCategoryName());
        holder.CDes.setText(model.getCategoryDescription());

        holder.CDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Are you sure want to delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(position);
                        FirebaseDatabase.getInstance().getReference().child("Category")
                                .child(getRef(holder.getAbsoluteAdapterPosition()).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        int i =holder.getAbsoluteAdapterPosition();
        holder.CEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Edit");
                builder.setMessage("Are you sure want to edit?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference key= FirebaseDatabase.getInstance().getReference().child("Category")
                                .child(getRef(holder.getAbsoluteAdapterPosition()).getKey());

                        Intent destIntent = new Intent();
                        destIntent.setClass(context,EditCategoryActivity.class);
                        destIntent.putExtra("editCategoryId", String.valueOf(key));
                        destIntent.putExtra("editCategoryName",model.getCategoryName());
                        destIntent.putExtra("editCategoryDes",model.getCategoryDescription());
                        context.startActivity(destIntent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView CName;
        private final TextView CDes;
        private final ImageView CEdit;
        private final ImageView CDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CName = itemView.findViewById(R.id.tv_categoryname);
            CDes = itemView.findViewById(R.id.tv_categorydescription);

            CEdit = itemView.findViewById(R.id.imgbtn_categoryedit);
            CDelete = itemView.findViewById(R.id.imgbtn_categorydelete);
        }
    }
}
