package com.example.genshinartifacts.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.objectModels.ConstantData;
import com.example.genshinartifacts.setsInfoPage.ArtifactInfoActivity;

import java.util.ArrayList;

public class UserArtifactTableAdapter extends RecyclerView.Adapter<UserArtifactTableAdapter.UserArtifactTableViewHolder> {

   private Context context;
   private ArrayList<Integer> artifacts_id;

   public class UserArtifactTableViewHolder extends RecyclerView.ViewHolder{
      ConstraintLayout model;

      ImageView artifact_image;
      ImageView artifact_image_shadow;

      public UserArtifactTableViewHolder(@NonNull View itemView) {
         super(itemView);
         model = itemView.findViewById(R.id.model_small_artifact);

         artifact_image = itemView.findViewById(R.id.artifact_image);
         artifact_image_shadow = itemView.findViewById(R.id.artifact_image_shadow);
      }
   }

   public UserArtifactTableAdapter(Context context, ArrayList<Integer> artifacts_id){
      this.context = context;
      this.artifacts_id = artifacts_id;
   }

   @Override
   public int getItemCount() {
      return artifacts_id.size();
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @NonNull
   @Override
   public UserArtifactTableAdapter.UserArtifactTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_small_artifact, parent, false);
      UserArtifactTableAdapter.UserArtifactTableViewHolder ivh = new UserArtifactTableAdapter.UserArtifactTableViewHolder(view);
      return ivh;
   }

   @Override
   public void onBindViewHolder(@NonNull UserArtifactTableAdapter.UserArtifactTableViewHolder holder, int position) {

      holder.artifact_image.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactById(artifacts_id.get(position)).getImg(), 0, ConstantData.findArtifactById(artifacts_id.get(position)).getImg().length));
      holder.artifact_image_shadow.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findArtifactById(artifacts_id.get(position)).getImg(), 0, ConstantData.findArtifactById(artifacts_id.get(position)).getImg().length));
      int set_id = ConstantData.findArtifactById(artifacts_id.get(position)).getSet_id();

      holder.model.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            Intent intent = new Intent(context, ArtifactInfoActivity.class);
            intent.putExtra("id", set_id);
            context.startActivity(intent);
         }
      });
   }

   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
   }
}
