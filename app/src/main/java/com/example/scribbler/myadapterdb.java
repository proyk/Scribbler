package com.example.scribbler;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapterdb extends FirebaseRecyclerAdapter<MyModel,myadapterdb.myviewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context ct;
    public myadapterdb(@NonNull FirebaseRecyclerOptions<MyModel> options,Context context) {
        super(options);
        ct = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull MyModel model) {
        holder.title.setText(model.getPostTitle());
        holder.shortDescription.setText(model.getPostShortDescription());
        holder.category.setText(model.getCategory());

        Glide.with(holder.post.getContext()).load(model.getPostImageUrl()).into(holder.post);


        holder.readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Detailed_Page.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("Post Title",holder.title.getText().toString());
                intent.putExtra("Post Short Description",holder.shortDescription.toString());
                intent.putExtra("Post Category",holder.category.toString());
                v.getContext().startActivity(intent);


                //Toast.makeText(ct.getApplicationContext(), "123", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @NonNull

    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_items_db,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        ImageView post;
        TextView title,shortDescription, category;
        Button readmore;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            post = itemView.findViewById(R.id.Post);
            title = itemView.findViewById(R.id.txt_Title);
            shortDescription = itemView.findViewById(R.id.txt_ShortDescription);
            readmore = itemView.findViewById(R.id.btn_ReadMore);
            category = itemView.findViewById(R.id.txt_Category);
        }
    }

}
