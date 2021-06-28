package com.example.scribbler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class createPost extends AppCompatActivity {
    ImageView img;
    Button browse,upload;
    Uri filepath;
    Bitmap bitmap;
    EditText post_title,post_short_desc,post_desc;
    DatabaseReference databaseReference;
    Spinner category;
    ValueEventListener listener;
    ArrayAdapter<String> adapter;
    ArrayList<String> category_list;
    String likeCounts = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        try{
            img=(ImageView)findViewById(R.id.imageView);
            browse=(Button)findViewById(R.id.browse);
            upload=(Button) findViewById(R.id.upload);
            post_title=(EditText)findViewById(R.id.ptitle);
            post_short_desc=(EditText)findViewById(R.id.shortDescription);
            post_desc=(EditText)findViewById(R.id.PostDescription);
            category=(Spinner) findViewById(R.id.categories);

            category_list=new ArrayList<>();
            adapter =new ArrayAdapter<>(createPost.this, android.R.layout.simple_spinner_dropdown_item,category_list);

            browse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dexter.withActivity(createPost.this)
                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse response) {
                                    Intent intent=new Intent(Intent.ACTION_PICK);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent,"Please Select Image"),1);

                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse response) {

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                    token.continuePermissionRequest();
                                }
                            }).check();
                }
            });
            upload.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    uploadImg();
                }
            });
        }
        catch (NullPointerException e){

        }
        category.setAdapter(adapter);
        display_category();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String dateAndTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }


    public void display_category(){
        category_list.add("Select Category");
        databaseReference = FirebaseDatabase.getInstance().getReference("Category");
        listener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren())
                {
                    category_list.add(item.child("categoryName").getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadImg() {
        ProgressDialog pd =new ProgressDialog(this);
        pd.setTitle("File Upload");
        pd.show();
        String curdateAndTime = dateAndTime();
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference ref=storage.getReference().child(System.currentTimeMillis()+"post");
        databaseReference = FirebaseDatabase.getInstance().getReference("Posts");
        ref.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Uri downloadUrl=uri;
                                PostAdd Post=new PostAdd(post_title.getText().toString(),post_short_desc.getText().toString(),post_desc.getText().toString(),downloadUrl.toString(),category.getSelectedItem().toString(), curdateAndTime);
                                String PostImageUploadId = databaseReference.push().getKey();
                                databaseReference.push().setValue(Post);
                            }
                        });

                        /*PostAdd Post=new PostAdd(post_title.getText().toString(),post_short_desc.getText().toString(),post_desc.getText().toString(),taskSnapshot.getMetadata().getReference().getDownloadUrl().toString(),category.getSelectedItem().toString(), curdateAndTime);
                        String PostImageUploadId = databaseReference.push().getKey();
                        databaseReference.push().setValue(Post);*/
                        Toast.makeText(getApplicationContext(),"File Uploaded",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(com.example.scribbler.createPost.this,RecyclerViewDB.class));


                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        float per=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        pd.setMessage("Uploading...."+per+"%");
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            filepath=data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}