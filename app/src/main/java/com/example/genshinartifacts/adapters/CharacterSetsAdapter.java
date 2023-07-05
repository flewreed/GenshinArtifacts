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
import com.example.genshinartifacts.objectModels.ConstantData;
import com.example.genshinartifacts.setsInfoPage.ArtifactInfoActivity;

import java.util.ArrayList;

public class CharacterSetsAdapter extends RecyclerView.Adapter<CharacterSetsAdapter.CharacterSetsViewHolder> {

   private Context context;
   private ArrayList<Integer> characterSets;

   public class CharacterSetsViewHolder extends RecyclerView.ViewHolder{

      ConstraintLayout model;

      ImageView set_image;
      ImageView set_image_shadow;
      TextView name;

      public CharacterSetsViewHolder(@NonNull View itemView) {
         super(itemView);

         model = itemView.findViewById(R.id.model_rec_art);

         set_image = itemView.findViewById(R.id.artifact_set_image);
         set_image_shadow = itemView.findViewById(R.id.artifact_set_image_shadow);
         name = itemView.findViewById(R.id.artifact_set_name);
      }
   }

   public CharacterSetsAdapter(Context context, ArrayList<Integer> characterSets){
      this.context = context;
      this.characterSets = characterSets;
   }

   @Override
   public int getItemCount() {
      return characterSets.size();
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @NonNull
   @Override
   public CharacterSetsAdapter.CharacterSetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_rec_artifact, parent, false);
      CharacterSetsAdapter.CharacterSetsViewHolder ivh = new CharacterSetsAdapter.CharacterSetsViewHolder(view);
      return ivh;
   }

   @Override
   public void onBindViewHolder(@NonNull CharacterSetsAdapter.CharacterSetsViewHolder holder, int position) {
      int id = ConstantData.findSetById(characterSets.get(position)).getId();
      holder.set_image.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findSetById(characterSets.get(position)).getImg(), 0, ConstantData.findSetById(characterSets.get(position)).getImg().length));
      holder.set_image_shadow.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findSetById(characterSets.get(position)).getImg(), 0, ConstantData.findSetById(characterSets.get(position)).getImg().length));
      holder.name.setText(ConstantData.findSetById(characterSets.get(position)).getName());

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