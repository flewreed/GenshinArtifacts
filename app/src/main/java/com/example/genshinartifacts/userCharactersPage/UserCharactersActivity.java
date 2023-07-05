package com.example.genshinartifacts.userCharactersPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.LoginActivity;
import com.example.genshinartifacts.R;
import com.example.genshinartifacts.adapters.CharacterTableAdapter;
import com.example.genshinartifacts.helpers.UserSession;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class UserCharactersActivity extends AppCompatActivity {

	UserCharactersActivity userCharactersActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_characters);
		userCharactersActivity = this;

		charactersTableAdapter();
		exit();
	}

	private void charactersTableAdapter() {
		RecyclerView characterTable = this.findViewById(R.id.userCharactersTable);
		CharacterTableAdapter characterTableAdapter;

		characterTableAdapter = new CharacterTableAdapter(this);
		characterTable.setLayoutManager(new GridLayoutManager(this, 2));
		characterTable.setAdapter(characterTableAdapter);
	}

	private void exit(){
		ExtendedFloatingActionButton exit_btn = findViewById(R.id.fabbutton);
		exit_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				UserSession.removeUserSession();
				Intent intent = new Intent(userCharactersActivity, LoginActivity.class);
				startActivity(intent);
				userCharactersActivity.finish();
			}
		});
	}
}