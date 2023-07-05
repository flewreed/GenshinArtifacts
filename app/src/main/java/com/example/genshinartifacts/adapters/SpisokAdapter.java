package com.example.genshinartifacts.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;

import java.util.ArrayList;

public class SpisokAdapter extends RecyclerView.Adapter<SpisokAdapter.SpisokViewHolder> {

	private Context context;
	private ArrayList<String> points;
	private int modelLayout;

	public class SpisokViewHolder extends RecyclerView.ViewHolder{
		TextView point;

		public SpisokViewHolder(@NonNull View itemView) {
			super(itemView);
			point = itemView.findViewById(R.id.txtViewPoint);
		}
	}

	public SpisokAdapter(Context context, ArrayList<String> points){
		this.context = context;
		this.points = points;
		this.modelLayout = R.layout.model_spisok;
	}

	public SpisokAdapter(Context context, ArrayList<String> points, boolean isSmall){
		this.context = context;
		this.points = points;
		this.modelLayout = R.layout.model_small_spisok;
	}

	@Override
	public int getItemCount() {
		return points.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@NonNull
	@Override
	public SpisokAdapter.SpisokViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(modelLayout, parent, false);
		SpisokAdapter.SpisokViewHolder ivh = new SpisokAdapter.SpisokViewHolder(view);
		return ivh;
	}

	@Override
	public void onBindViewHolder(@NonNull SpisokAdapter.SpisokViewHolder holder, int position) {
		holder.point.setText(points.get(position));
	}

	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}
}