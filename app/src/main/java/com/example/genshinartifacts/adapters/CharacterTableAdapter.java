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
import com.example.genshinartifacts.objectModels.UserCharacter;
import com.example.genshinartifacts.userCharactersPage.MyCharacterActivity;

import java.util.ArrayList;

public class CharacterTableAdapter extends RecyclerView.Adapter<CharacterTableAdapter.CharacterTableViewHolder> {

   private Context context;
   private ArrayList<UserCharacter> usersCharacters = ConstantData.usersCharacters;

   public class CharacterTableViewHolder extends RecyclerView.ViewHolder{

      ConstraintLayout model;

      ImageView character_image;
      ImageView character_image_shadow;
      TextView name;

      public CharacterTableViewHolder(@NonNull View itemView) {
         super(itemView);

         model = itemView.findViewById(R.id.model_user_characters);

         character_image = itemView.findViewById(R.id.character_image);
         character_image_shadow = itemView.findViewById(R.id.character_image_shadow);
         name = itemView.findViewById(R.id.character_name);
      }
   }

   public CharacterTableAdapter(Context context){
      this.context = context;
   }

   @Override
   public int getItemCount() {
      return usersCharacters.size();
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @NonNull
   @Override
   public CharacterTableAdapter.CharacterTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_user_character, parent, false);
      CharacterTableAdapter.CharacterTableViewHolder ivh = new CharacterTableAdapter.CharacterTableViewHolder(view);
      return ivh;
   }

   @Override
   public void onBindViewHolder(@NonNull CharacterTableAdapter.CharacterTableViewHolder holder, int position) {
      int character_position = position;

      holder.name.setText(usersCharacters.get(position).getCharacter_name());
      holder.character_image.setImageBitmap(BitmapFactory.decodeByteArray(usersCharacters.get(position).getSmall_img(), 0, usersCharacters.get(position).getSmall_img().length));
      holder.character_image_shadow.setImageBitmap(BitmapFactory.decodeByteArray(usersCharacters.get(position).getSmall_img(), 0, usersCharacters.get(position).getSmall_img().length));

      holder.model.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            Intent intent = new Intent(context, MyCharacterActivity.class);
            intent.putExtra("character_position", character_position);
            context.startActivity(intent);
         }
      });
   }

   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
   }
}
