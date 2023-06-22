package com.example.myinterface;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class PortActivity extends AppCompatActivity {

    RecyclerView recyclerViewa;
    AssetAdapter assetAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port);

        recyclerViewa = findViewById(R.id.recycler_view1);

        setupRecyclerView();
    }

    void setupRecyclerView(){
        Query query = Utility.getCollectionReferenceForAssets().orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Asset> options = new FirestoreRecyclerOptions.Builder<Asset>().setQuery(query, Asset.class).build();
        recyclerViewa.setLayoutManager(new LinearLayoutManager(this));
        assetAdapter = new AssetAdapter(options, this);
        recyclerViewa.setAdapter(assetAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        assetAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        assetAdapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        assetAdapter.notifyDataSetChanged();
    }
}