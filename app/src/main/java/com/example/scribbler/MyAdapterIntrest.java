package com.example.scribbler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyAdapterIntrest extends FirebaseRecyclerAdapter<PostAdd, MyAdapterIntrest.myViewAdpter> {
    String getList;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdapterIntrest(@NonNull FirebaseRecyclerOptions<PostAdd> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewAdpter holder, int position, @NonNull PostAdd model) {
        String[] CollectionCategory = getList.split(",");
        for(int i=0;i<CollectionCategory.length;i++) {
            if (model.getCategory().equals(CollectionCategory[i].toString())) {
                holder.card.setVisibility(View.VISIBLE);
                holder.posttitle.setText(model.getPostTitle());
                holder.postShortDesc.setText(model.getPostShortDescription());
                holder.postCategory.setText(model.getCategory());
                //holder.postimg.setImageURI(Uri.parse(model.getPostImageUrl()));

                //Picasso.get().load(model.getPostImageUrl()).into(holder.postimg);
                Glide.with(holder.postimg.getContext()).load(model.getPostImageUrl()).into(holder.postimg);
            }
            if (i == CollectionCategory.length-1) {
                holder.card.setVisibility(View.GONE);
            }
        }
        }

    @NonNull
    @Override
    public myViewAdpter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.onepost,parent,false);
       return new myViewAdpter(v);
    }

    public void setCatogry(String getList) {
        this.getList=getList;
    }

    class myViewAdpter extends RecyclerView.ViewHolder{
        ImageView postimg;
        TextView posttitle,postShortDesc,postCategory;
        CardView card;
        public myViewAdpter(@NonNull View itemView) {

            super(itemView);
            postimg=(ImageView)itemView.findViewById(R.id.postimgone);
            posttitle=(TextView)itemView.findViewById(R.id.posttitleone);
            postShortDesc=(TextView)itemView.findViewById(R.id.postshortdescone);
            postCategory=(TextView)itemView.findViewById(R.id.postcatone);
            card=(CardView)itemView.findViewById(R.id.card321);
        }
    }
}
