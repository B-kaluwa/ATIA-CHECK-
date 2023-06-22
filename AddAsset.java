package com.example.myinterface;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class AddAsset extends AppCompatActivity {

    String[] item = {"Financial Asset", "Tangible Asset" ,"Intangile Asset"};
    AutoCompleteTextView autotypeTextView;
    ArrayAdapter<String> adapterItems;

    EditText assettitleEditText,typeEditText,amountEditText,dateEditText,detailEditText;
    Button saveAssetBtn;
    TextView pageTitleTextView;
    String name,type,amount,date,detail,docId;
    boolean isEditMode = false;
    TextView deleteAssetTextViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asset);

        autotypeTextView = findViewById(R.id.auto_type_text);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item, item);

        autotypeTextView.setAdapter(adapterItems);

        autotypeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(AddAsset.this,"Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        assettitleEditText = findViewById(R.id.asset_title_text);
        typeEditText = findViewById(R.id.auto_type_text);
        amountEditText = findViewById(R.id.asset_amount_text);
        dateEditText = findViewById(R.id.asset_date_text);
        detailEditText = findViewById(R.id.asset_detail_text);
        saveAssetBtn = findViewById(R.id.save_asset_btn);
        pageTitleTextView = findViewById(R.id.page_title1);
        deleteAssetTextViewBtn = findViewById(R.id.delete_asset_text_view_btn);

        name = getIntent().getStringExtra("name");
        type = getIntent().getStringExtra("type");
        amount = getIntent().getStringExtra("amount");
        date = getIntent().getStringExtra("date");
        detail = getIntent().getStringExtra("detail");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && docId.isEmpty()){
            isEditMode = true;
        }

        assettitleEditText.setText(name);
        typeEditText.setText(type);
        amountEditText.setText(amount);
        dateEditText.setText(date);
        detailEditText.setText(detail);
        if(isEditMode){
            pageTitleTextView.setText("Edit your Asset");
            deleteAssetTextViewBtn.setVisibility(View.VISIBLE);
        }

        saveAssetBtn.setOnClickListener( (v)-> saveAsset());

        deleteAssetTextViewBtn.setOnClickListener((v)-> deleteAssetFromFirebase());
    }

    void saveAsset(){
        String assetName = assettitleEditText.getText().toString();
        String assetType = typeEditText.getText().toString();
        String assetAmount = amountEditText.getText().toString();
        String assetDate = dateEditText.getText().toString();
        String assetDetail = detailEditText.getText().toString();
        if(assetName==null || assetName.isEmpty() ){
            assettitleEditText.setError("name of asset is required");
            return;
        }
        Asset asset = new Asset();
        asset.setName(assetName);
        asset.setType(assetType);
        asset.setAmount(assetAmount);
        asset.setDate(assetDate);
        asset.setDetail(assetDetail);
        asset.setTimestamp(Timestamp.now());

        saveAssetToFirebase(asset);

    }

    void saveAssetToFirebase(Asset asset){
        DocumentReference documentReference;
        if(isEditMode){
            //update the note
            documentReference = Utility.getCollectionReferenceForAssets().document(docId);
        }else {
            //create new note
            documentReference = Utility.getCollectionReferenceForAssets().document();
        }

        documentReference.set(asset).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task){
                if(task.isSuccessful()){
                    Utility.showToast(AddAsset.this,"Asset added successfully");
                    finish();
                }else{
                    Utility.showToast(AddAsset.this,"Failed while adding Asset");
                }
            }
        });

    }

    void deleteAssetFromFirebase(){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForAssets().document(docId);
        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task){
                if(task.isSuccessful()){
                    //note is deleted
                    Utility.showToast(AddAsset.this,"Asset deleted successfully");
                    finish();
                }else{
                    Utility.showToast(AddAsset.this,"Failed while deleting Asset");
                }
            }
        });

    }
}