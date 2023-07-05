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
import com.example.genshinartifacts.charactersInfoPage.CharacterInfoActivity;
import com.example.genshinartifacts.objectModels.Character;

import java.util.ArrayList;

public class CharactersListAdapter extends RecyclerView.Adapter<CharactersListAdapter.CharactersListViewHolder> {

	private Context context;
	private ArrayList<Character> characters;

	public void setFilteredList(ArrayList<Character> filteredList){
		this.characters = filteredList;
		notifyDataSetChanged();
	}

	public class CharactersListViewHolder extends RecyclerView.ViewHolder{

		ConstraintLayout model;

		ImageView character_image;
		ImageView character_image_shadow;
		ImageView element_image;
		TextView name;
		TextView character_weapon;
		TextView character_rarity;

		public CharactersListViewHolder(@NonNull View itemView) {
			super(itemView);

			model = itemView.findViewById(R.id.model_character);

			character_image = itemView.findViewById(R.id.character_image);
			character_image_shadow = itemView.findViewById(R.id.character_image_shadow);
			element_image = itemView.findViewById(R.id.element_image);
			name = itemView.findViewById(R.id.character_name);
			character_weapon = itemView.findViewById(R.id.character_weapon);
			character_rarity = itemView.findViewById(R.id.character_rarity);
		}
	}

	public CharactersListAdapter(Context context, ArrayList<Character> characters){
		this.context = context;
		this.characters = characters;
	}

	@Override
	public int getItemCount() {
		return characters.size();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@NonNull
	@Override
	public CharactersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_character, parent, false);
		CharactersListViewHolder ivh = new CharactersListViewHolder(view);
		return ivh;
	}

	@Override
	public void onBindViewHolder(@NonNull CharactersListViewHolder holder, int position) {
		int id = characters.get(position).getId();
		holder.character_image.setImageBitmap(BitmapFactory.decodeByteArray(characters.get(position).getSmall_img(), 0,characters.get(position).getSmall_img().length));
		holder.character_image_shadow.setImageBitmap(BitmapFactory.decodeByteArray(characters.get(position).getSmall_img(), 0,characters.get(position).getSmall_img().length));
		holder.element_image.setImageBitmap(BitmapFactory.decodeByteArray(characters.get(position).getElementImage(), 0,characters.get(position).getElementImage().length));
		holder.name.setText(characters.get(position).getName());
		holder.character_weapon.setText(characters.get(position).getWeapon());
		holder.character_rarity.setText(characters.get(position).getRarityString());

		holder.model.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, CharacterInfoActivity.class);
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
