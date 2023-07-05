package com.example.genshinartifacts;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.genshinartifacts.charactersInfoPage.CharactersActivity;
import com.example.genshinartifacts.setsInfoPage.ArtifactsActivity;
import com.example.genshinartifacts.userCharactersPage.UserCharactersActivity;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		TabHost tabHost = getTabHost();

		TabHost.TabSpec tabSpec;

		tabSpec = tabHost.newTabSpec("tag1");
		tabSpec.setIndicator("Персонажи");
		tabSpec.setContent(new Intent(this, CharactersActivity.class));
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag2");
		tabSpec.setIndicator("Артефакты");
		tabSpec.setContent(new Intent(this, ArtifactsActivity.class));
		tabHost.addTab(tabSpec);

		tabSpec = tabHost.newTabSpec("tag3");
		tabSpec.setIndicator("Профиль");
		tabSpec.setContent(new Intent(this, UserCharactersActivity.class));
		tabHost.addTab(tabSpec);
	}
}