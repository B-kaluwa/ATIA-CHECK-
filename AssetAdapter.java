package com.example.myinterface;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AssetAdapter extends FirestoreRecyclerAdapter<Asset, AssetAdapter.AssetViewHolder> {

    Context context1;

    public AssetAdapter(@NonNull FirestoreRecyclerOptions<Asset> options, Context context) {
        super(options);
        this.context1 = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull AssetAdapter.AssetViewHolder holder, int position, @NonNull Asset asset) {
        holder.nameTextView.setText(asset.name);
        holder.typeTextView.setText(asset.type);
        holder.amountTextView.setText(asset.amount);
        holder.dateTextView.setText(asset.date);
        holder.detailTextView.setText(asset.detail);
        holder.timestampTextView.setText(Utility.timestampToString(asset.timestamp));

        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context1,AddAsset.class);
            intent.putExtra("name",asset.name);
            intent.putExtra("type",asset.type);
            intent.putExtra("amount",asset.amount);
            intent.putExtra("date",asset.date);
            intent.putExtra("detail",asset.detail);
            String docId = this.getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId",asset.name);
            context1.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public AssetAdapter.AssetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_asset_item,parent,false);
        return new AssetAdapter.AssetViewHolder(view);
    }

    class AssetViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView,typeTextView,amountTextView,dateTextView,detailTextView,timestampTextView;

        public AssetViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.asset_name_text_view);
            typeTextView = itemView.findViewById(R.id.asset_type_text_view);
            amountTextView = itemView.findViewById(R.id.asset_amount_text_view);
            dateTextView = itemView.findViewById(R.id.asset_date_text_view);
            detailTextView = itemView.findViewById(R.id.asset_details_text_view);
            timestampTextView = itemView.findViewById(R.id.asset_timestamp_text_view);
        }
    }

}
