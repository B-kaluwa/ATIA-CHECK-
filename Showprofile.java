package com.example.myinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Showprofile extends AppCompatActivity {

    UploadTask uploadTask;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    ImageView imageView;
    TextView nameEt,bioEt,ageEt,emailEt,websiteEt;
    FloatingActionButton floatingActionButton;
    FloatingActionButton backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showprofile);

        floatingActionButton = findViewById(R.id.floatingbtn_sp);
        nameEt = findViewById(R.id.name_tv_sp);
        bioEt = findViewById(R.id.bio_tv_sp);
        ageEt = findViewById(R.id.age_tv_sp);
        emailEt = findViewById(R.id.email_tv_sp);
        websiteEt = findViewById(R.id.website_tv_sp);
        imageView = findViewById(R.id.imageView_sp);
        backBtn = findViewById(R.id.back_sp);
        documentReference = db.collection("user").document("profile");
        storageReference = firebaseStorage.getInstance().getReference("profile image");


        backBtn.setOnClickListener((v)-> startActivity(new Intent(Showprofile.this,MainActivity.class)));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Showprofile.this, CreateProfile.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected  void onStart() {
        super.onStart();

        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.getResult().exists()){
                            String name_result = task.getResult().getString("name");
                            String age_result = task.getResult().getString("age");
                            String bio_result = task.getResult().getString("bio");
                            String email_result = task.getResult().getString("email");
                            String web_result = task.getResult().getString("website");
                            String Url = task.getResult().getString("uri");

                            Picasso.get().load(Url).into(imageView);

                            nameEt.setText(name_result);
                            ageEt.setText(age_result);
                            bioEt.setText(bio_result);
                            emailEt.setText(email_result);
                            websiteEt.setText(web_result);
                        }else{
                            Toast.makeText(Showprofile.this, "No Profile exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}