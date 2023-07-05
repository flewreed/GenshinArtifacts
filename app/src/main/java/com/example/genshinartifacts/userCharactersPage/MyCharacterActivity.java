package com.example.genshinartifacts.userCharactersPage;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.adapters.CommentListAdapter;
import com.example.genshinartifacts.adapters.SpisokAdapter;
import com.example.genshinartifacts.adapters.StatsSpisokAdapter;
import com.example.genshinartifacts.adapters.UserArtifactTableAdapter;
import com.example.genshinartifacts.helpers.Analise;
import com.example.genshinartifacts.objectModels.ConstantData;
import com.example.genshinartifacts.objectModels.UserCharacter;

import java.util.ArrayList;
import java.util.Map;

public class MyCharacterActivity extends AppCompatActivity {

	private ConstraintLayout start_anal;
	private UserCharacter character;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_character);
		setCharacterData(getIntent().getExtras().getInt("character_position"));
		openResult();
	}

	private void setCharacterData(int position) {
		character = ConstantData.usersCharacters.get(position);

		ImageView char_img = findViewById(R.id.character_full_image);
		TextView char_name = findViewById(R.id.char_name);
		TextView level = findViewById(R.id.character_level);
		TextView constellations = findViewById(R.id.constellations);
		TextView weapon_name = findViewById(R.id.weapon_name);
		RecyclerView artifacts = findViewById(R.id.rvMyArtifacts);
		RecyclerView bonuses = findViewById(R.id.recViewActiveBonuses);

		char_img.setImageBitmap(BitmapFactory.decodeByteArray(character.getFull_img(), 0, character.getFull_img().length));

		char_name.setText(character.getCharacter_name());
		level.setText(Integer.toString(character.getLevel()));
		constellations.setText(Integer.toString(character.getConstellation()));
		weapon_name.setText(character.getWeapon());

		pointsListAdapter(bonuses, character.getCharacterBonuses());

		if (character.getArtifacts().size() == 0){
			ArrayList<String> art = new ArrayList<>();
			art.add("Артефактов нет");
			pointsListAdapter(artifacts, art);
		} else characterArtifactsAdapter(artifacts, character.getArtifacts());
	}

	private void characterArtifactsAdapter(RecyclerView pointsView, ArrayList<Integer> artifacts_id) {
		RecyclerView characterList = pointsView;
		UserArtifactTableAdapter userArtifactTableAdapter;

		userArtifactTableAdapter = new UserArtifactTableAdapter(this, artifacts_id);
		characterList.setLayoutManager(new GridLayoutManager(this, 5));
		characterList.setAdapter(userArtifactTableAdapter);
	}

	private void openResult(){
		start_anal = findViewById(R.id.start_anal);
		start_anal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Dialog dialog = new Dialog(MyCharacterActivity.this);
				dialog.setContentView(R.layout.dialog_analysis);
				startAnalysis(dialog);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				dialog.show();

				Button btn_close = (Button) dialog.findViewById(R.id.btn_close);
				btn_close.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						dialog.dismiss();
					}
				});
			}
		});
	}

	public void startAnalysis(Dialog dialog){
		Map<ArrayList<Integer>, String> analiseResult = new Analise(character).startAnalise();
		String mainComment = Analise.mainComment;

		TextView mainCommentTV = dialog.findViewById(R.id.result_txt);
		RecyclerView analiseResultRV = dialog.findViewById(R.id.images_and_comments);
		RecyclerView stats = dialog.findViewById(R.id.statsRecyclerView);

		mainCommentTV.setText(mainComment);

		StatsSpisokAdapter statsSpisokAdapter = new StatsSpisokAdapter(this, ConstantData.findCharacterByNameANDElement(character.getCharacter_name(), character.getElement()));
		stats.setLayoutManager(new LinearLayoutManager(this));
		stats.setAdapter(statsSpisokAdapter);

		CommentListAdapter commentListAdapter = new CommentListAdapter(this, analiseResult);
		analiseResultRV.setLayoutManager(new LinearLayoutManager(this));
		analiseResultRV.setAdapter(commentListAdapter);
	}

	private void pointsListAdapter(RecyclerView pointsView, ArrayList<String> points) {
		RecyclerView characterList = pointsView;
		SpisokAdapter spisokAdapter;

		spisokAdapter = new SpisokAdapter(this, points);
		characterList.setLayoutManager(new LinearLayoutManager(this));
		characterList.setAdapter(spisokAdapter);
	}
}