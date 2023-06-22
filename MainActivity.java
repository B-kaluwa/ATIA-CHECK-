package com.example.myinterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    UploadTask uploadTask;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;

    private Button button3,button4, button5, button6,button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modern);

        documentReference = db.collection("user").document("profile");

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfile();
            }

            private void openProfile() {

                documentReference.get()
                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                       if (task.getResult().exists()){
                                           startActivity(new Intent(MainActivity.this, Showprofile.class));
                                       }else {
                                           startActivity(new Intent(MainActivity.this, CreateProfile.class));
                                       }
                                    }
                                });
            }
        });


        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPayments();
            }

            private void openPayments() {
                startActivity(new Intent(MainActivity.this, Payments.class));
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this,AddAsset.class)));

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this,PortActivity.class)));


        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this,Online.class)));


    }

    public void clickexit(View v)
    {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        if (id == R.id.settings) {

            Intent intent = new Intent(MainActivity.this,Setting.class);
            startActivity(intent);
            return true;
        }
        /*else
            if (id == R.id.people) {
                Intent intent = new Intent(MainActivity.this,Contact.class);
                startActivity(intent);

                return true;
            }*/
            else
                if (id == R.id.article) {
                    Intent intent = new Intent(MainActivity.this,Terms.class);
                    startActivity(intent);

                    return true;
                }
                else
                    if (id == R.id.policy) {
                       Intent intent = new Intent(MainActivity.this,Policy.class);
                       startActivity(intent);

                       return true;
                    }
                    else
                        if (id == R.id.note_add) {
                            Intent intent = new Intent(MainActivity.this,TODO.class);
                            startActivity(intent);

                            return true;
                        }
                        else
                            if (id == R.id.manual) {
                                Intent intent = new Intent(MainActivity.this,umanual.class);
                                startActivity(intent);

                                return true;
                            }
                            else
                                if (id == R.id.logout) {
                                    FirebaseAuth.getInstance().signOut();
                                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                                    finish();

                                    return true;
                                }


    return super.onOptionsItemSelected(item);

}

}