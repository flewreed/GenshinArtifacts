package com.example.genshinartifacts.charactersInfoPage;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.adapters.CharacterSetsAdapter;
import com.example.genshinartifacts.adapters.SpisokAdapter;
import com.example.genshinartifacts.objectModels.Character;
import com.example.genshinartifacts.objectModels.ConstantData;

import java.util.ArrayList;

public class CharacterInfoActivity extends AppCompatActivity {

	private CharacterInfoActivity characterInfoActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_info);

		setCharacterData(getIntent().getExtras().getInt("id"));
	}

	private void setCharacterData(int id) {
		Character character = ConstantData.findCharacterById(id);

		ImageView char_img = findViewById(R.id.character_full_image);
		TextView char_name = findViewById(R.id.char_name);
		ImageView element_img = findViewById(R.id.element_image);
		TextView element_name = findViewById(R.id.element_name);
		TextView weapon = findViewById(R.id.weapon);
		TextView region = findViewById(R.id.region);
		TextView rarity = findViewById(R.id.rarity);
		TextView char_description = findViewById(R.id.char_description);
		RecyclerView strong_points = findViewById(R.id.recViewStrongPoints);
		RecyclerView weak_points = findViewById(R.id.recViewWeakPoints);
		RecyclerView character_sets = findViewById(R.id.recViewCharacterSets);

		char_img.setImageBitmap(BitmapFactory.decodeByteArray(character.getFull_img(), 0, character.getFull_img().length));
		char_name.setText(character.getName());
		element_img.setImageBitmap(BitmapFactory.decodeByteArray(character.getElementImage(), 0, character.getElementImage().length));
		element_name.setText(character.getElement_name());
		weapon.setText(character.getWeapon());
		region.setText(character.getRegion());
		rarity.setText(character.getRarityString());
		char_description.setText(character.getDescription());

		characterListAdapter(strong_points, character.getStrong_points());
		characterListAdapter(weak_points, character.getWeak_points());
		characterSetAdapter(character_sets, character.getSets());

	}

	private void characterListAdapter(RecyclerView pointsView, ArrayList<String> points) {
		RecyclerView characterList = pointsView;
		SpisokAdapter spisokAdapter;

		spisokAdapter = new SpisokAdapter(this, points);
		characterList.setLayoutManager(new LinearLayoutManager(this));
		characterList.setAdapter(spisokAdapter);
	}

	private void characterSetAdapter(RecyclerView pointsView, ArrayList<Integer> sets_id) {
		RecyclerView characterList = pointsView;
		CharacterSetsAdapter characterSetsAdapter;

		characterSetsAdapter = new CharacterSetsAdapter(this, sets_id);
		characterList.setLayoutManager(new GridLayoutManager(this, 3));
		characterList.setAdapter(characterSetsAdapter);
	}
}