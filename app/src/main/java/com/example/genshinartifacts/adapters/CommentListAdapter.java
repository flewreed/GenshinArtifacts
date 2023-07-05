package com.example.genshinartifacts.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;

import java.util.ArrayList;
import java.util.Map;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.SpisokViewHolder> {

   private Context context;
   private Map<ArrayList<Integer>, String> analiseResult;
   private ArrayList<ArrayList<Integer>> sets;

   public class SpisokViewHolder extends RecyclerView.ViewHolder{
      TextView comment;
      RecyclerView imagesRV;

      public SpisokViewHolder(@NonNull View itemView) {
         super(itemView);
         comment = itemView.findViewById(R.id.comment);
         imagesRV = itemView.findViewById(R.id.artifacts_set_img);
      }
   }

   public CommentListAdapter(Context context, Map<ArrayList<Integer>, String> analiseResult){
      this.context = context;
      this.analiseResult = analiseResult;
      this.sets = new ArrayList<>(this.analiseResult.keySet());
   }

   @Override
   public int getItemCount() {
      return analiseResult.size();
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @NonNull
   @Override
   public CommentListAdapter.SpisokViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_analysis_result, parent, false);
      CommentListAdapter.SpisokViewHolder ivh = new CommentListAdapter.SpisokViewHolder(view);
      return ivh;
   }

   @Override
   public void onBindViewHolder(@NonNull CommentListAdapter.SpisokViewHolder holder, int position) {
      holder.comment.setText(analiseResult.get(sets.get(position)));
      characterSetAdapter(holder.imagesRV, sets.get(position));

   }

   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
   }

   private void characterSetAdapter(RecyclerView pointsView, ArrayList<Integer> sets_id) {
      RecyclerView characterList = pointsView;
      SetAnalysisAdapter setAnalysisAdapter;
      setAnalysisAdapter = new SetAnalysisAdapter(context, sets_id);
      characterList.setLayoutManager(new GridLayoutManager(context, sets_id.size()));
      characterList.setAdapter(setAnalysisAdapter);

   }
}