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

public class SetAnalysisAdapter extends RecyclerView.Adapter<SetAnalysisAdapter.SetAnalysisViewHolder> {

   private Context context;
   private ArrayList<Integer> sets_id;

   public class SetAnalysisViewHolder extends RecyclerView.ViewHolder{
      ConstraintLayout model;

      ImageView set_image;
      ImageView set_image_shadow;

      public SetAnalysisViewHolder(@NonNull View itemView) {
         super(itemView);
         model = itemView.findViewById(R.id.model_small_artifact);

         set_image = itemView.findViewById(R.id.artifact_image);
         set_image_shadow = itemView.findViewById(R.id.artifact_image_shadow);
      }
   }

   public SetAnalysisAdapter(Context context, ArrayList<Integer> sets_id){
      this.context = context;
      this.sets_id = sets_id;
   }

   @Override
   public int getItemCount() {
      return sets_id.size();
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @NonNull
   @Override
   public SetAnalysisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_small_artifact, parent, false);
      SetAnalysisViewHolder ivh = new SetAnalysisViewHolder(view);
      return ivh;
   }

   @Override
   public void onBindViewHolder(@NonNull SetAnalysisViewHolder holder, int position) {

      holder.set_image.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findSetById(sets_id.get(position)).getImg(), 0, ConstantData.findSetById(sets_id.get(position)).getImg().length));
      holder.set_image_shadow.setImageBitmap(BitmapFactory.decodeByteArray(ConstantData.findSetById(sets_id.get(position)).getImg(), 0, ConstantData.findSetById(sets_id.get(position)).getImg().length));
      int set_id = ConstantData.findSetById(sets_id.get(position)).getId();

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
