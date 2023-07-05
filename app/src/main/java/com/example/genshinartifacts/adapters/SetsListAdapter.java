package com.example.genshinartifacts.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.objectModels.ArtifactsSet;
import com.example.genshinartifacts.setsInfoPage.ArtifactInfoActivity;

import java.util.ArrayList;

public class SetsListAdapter extends RecyclerView.Adapter<SetsListAdapter.SetsListViewHolder> {

   private Context context;
   private ArrayList<ArtifactsSet> artifactsSets;

   public void setFilteredList(ArrayList<ArtifactsSet> filteredList){
      this.artifactsSets = filteredList;
      notifyDataSetChanged();
   }

   public class SetsListViewHolder extends RecyclerView.ViewHolder{

      ConstraintLayout model;

      ImageView set_image;
      ImageView set_image_shadow;
      TextView name;

      public SetsListViewHolder(@NonNull View itemView) {
         super(itemView);

         model = itemView.findViewById(R.id.model_artifact);

         set_image = itemView.findViewById(R.id.artifact_set_image);
         set_image_shadow = itemView.findViewById(R.id.artifact_set_image_shadow);
         name = itemView.findViewById(R.id.artifact_set_name);
      }
   }

   public SetsListAdapter(Context context, ArrayList<ArtifactsSet> artifactsSets){
      this.context = context;
      this.artifactsSets = artifactsSets;
   }

   @Override
   public int getItemCount() {
      return artifactsSets.size();
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @NonNull
   @Override
   public SetsListAdapter.SetsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_artifact, parent, false);
      SetsListAdapter.SetsListViewHolder ivh = new SetsListAdapter.SetsListViewHolder(view);
      return ivh;
   }

   @Override
   public void onBindViewHolder(@NonNull SetsListAdapter.SetsListViewHolder holder, int position) {
      int id = artifactsSets.get(position).getId();
      holder.set_image.setImageBitmap(BitmapFactory.decodeByteArray(artifactsSets.get(position).getImg(), 0,artifactsSets.get(position).getImg().length));
      holder.set_image_shadow.setImageBitmap(BitmapFactory.decodeByteArray(artifactsSets.get(position).getImg(), 0,artifactsSets.get(position).getImg().length));
      holder.name.setText(artifactsSets.get(position).getName());

      holder.model.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            Intent intent = new Intent(context, ArtifactInfoActivity.class);
            intent.putExtra("id", id);
            context.startActivity(intent);
         }
      });
   }

   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
   }
}