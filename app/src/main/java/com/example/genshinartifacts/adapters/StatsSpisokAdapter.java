package com.example.genshinartifacts.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.objectModels.Character;

import java.util.ArrayList;
import java.util.Map;

public class StatsSpisokAdapter extends RecyclerView.Adapter<StatsSpisokAdapter.SpisokViewHolder> {

   private Context context;
   private ArrayList<String> stats_type;
   private Map<String, ArrayList<String>> statsList;

   public class SpisokViewHolder extends RecyclerView.ViewHolder{
      TextView stat_type;
      RecyclerView stats;

      public SpisokViewHolder(@NonNull View itemView) {
         super(itemView);
         stat_type = itemView.findViewById(R.id.stat_type);
         stats = itemView.findViewById(R.id.stats);
      }
   }

   public StatsSpisokAdapter(Context context, Character character){
      this.context = context;
      this.stats_type = character.getStats_type();
      this.statsList = character.getStats();
   }

   @Override
   public int getItemCount() {
      return stats_type.size();
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @NonNull
   @Override
   public StatsSpisokAdapter.SpisokViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_forolesya, parent, false);
      StatsSpisokAdapter.SpisokViewHolder ivh = new StatsSpisokAdapter.SpisokViewHolder(view);
      return ivh;
   }

   @Override
   public void onBindViewHolder(@NonNull StatsSpisokAdapter.SpisokViewHolder holder, int position) {
      holder.stat_type.setText(stats_type.get(position));
      pointsListAdapter(holder.stats, statsList.get(stats_type.get(position)));

   }

   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
   }

   private void pointsListAdapter(RecyclerView pointsView, ArrayList<String> points) {
      RecyclerView characterList = pointsView;
      SpisokAdapter spisokAdapter;

      spisokAdapter = new SpisokAdapter(context, points, true);
      characterList.setLayoutManager(new LinearLayoutManager(context));
      characterList.setAdapter(spisokAdapter);
   }
}