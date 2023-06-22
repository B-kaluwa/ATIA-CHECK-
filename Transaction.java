package com.example.myinterface;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class Transaction extends AppCompatActivity {

    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transanction);

        Button AddNoteBtn = findViewById(R.id.addnewnotebtn);
        recyclerView = findViewById(R.id.recycler_view);

        AddNoteBtn.setOnClickListener((v)-> startActivity(new Intent(Transaction.this,AddNoteActivity.class)) );
            setupRecyclerView();
        }

        void setupRecyclerView() {
            Query query = Utility.getCollectionReferenceForNotes().orderBy("timestamp",Query.Direction.DESCENDING);
            FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>().setQuery(query, Note.class).build();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            noteAdapter = new NoteAdapter(options, this);
            recyclerView.setAdapter(noteAdapter);
        }

        @Override
        protected void onStart() {
            super.onStart();
            noteAdapter.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            noteAdapter.stopListening();
        }

        @Override
        protected void onResume() {
            super.onResume();
            noteAdapter.notifyDataSetChanged();
        }

    }


