package com.example.scribbler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadpter extends FirebaseRecyclerAdapter<PostAdd,myadpter.myViewAdpter> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myadpter(@NonNull FirebaseRecyclerOptions<PostAdd> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewAdpter holder, int position, @NonNull PostAdd model) {
        holder.posttitle.setText(model.getPostTitle());
        holder.postShortDesc.setText(model.getPostShortDescription());
        holder.postCategory.setText(model.getCategory());
        //holder.postimg.setImageURI(Uri.parse(model.getPostImageUrl()));

        //Picasso.get().load(model.getPostImageUrl()).into(holder.postimg);
        Glide.with(holder.postimg.getContext()).load(model.getPostImageUrl()).into(holder.postimg);
    }

    @NonNull
    @Override
    public myViewAdpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.onepost,parent,false);
       return new myViewAdpter(v);
    }

    class myViewAdpter extends RecyclerView.ViewHolder{
        ImageView postimg;
        TextView posttitle,postShortDesc,postCategory;
        public myViewAdpter(@NonNull View itemView) {

            super(itemView);
            postimg=(ImageView)itemView.findViewById(R.id.postimgone);
            posttitle=(TextView)itemView.findViewById(R.id.posttitleone);
            postShortDesc=(TextView)itemView.findViewById(R.id.postshortdescone);
            postCategory=(TextView)itemView.findViewById(R.id.postcatone);
        }
    }
}
