package com.example.genshinartifacts.setsInfoPage;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.adapters.SetsListAdapter;
import com.example.genshinartifacts.adapters.other.HiddenRecyclerView;
import com.example.genshinartifacts.objectModels.ArtifactsSet;
import com.example.genshinartifacts.objectModels.ConstantData;

import java.util.ArrayList;

public class ArtifactsActivity extends AppCompatActivity {

	private SearchView search;
	private SetsListAdapter setsListAdapter;
	private RecyclerView setList;
	private ConstraintLayout filter_panel;

	private RadioButton alphabet_rbtn;
	private RadioButton rarity_rbtn;

	private ArrayList<ArtifactsSet> sortedArtifactsSets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_artifacts);

		sortedArtifactsSets = (ArrayList<ArtifactsSet>) ConstantData.sortListByRarity(ConstantData.artifactsSets);
		setList = findViewById(R.id.setsList);
		filter_panel = findViewById(R.id.filter_panel);

		alphabet_rbtn = findViewById(R.id.rbAlphabet);
		rarity_rbtn = findViewById(R.id.rbRarity);
		rarity_rbtn.setChecked(true);
		alphabet_rbtn.setOnClickListener(setRButtonClickListener);
		rarity_rbtn.setOnClickListener(setRButtonClickListener);

		setListAdapter(sortedArtifactsSets);
		setOnScrollListener(setList);

		search = findViewById(R.id.search);
		search.clearFocus();
		search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				filterList(newText);
				return true;
			}
		});
	}

	private void setListAdapter(ArrayList<ArtifactsSet> artifactsSets) {
		setsListAdapter = new SetsListAdapter(this, artifactsSets);
		setList.setLayoutManager(new LinearLayoutManager(this));
		setList.setAdapter(setsListAdapter);
	}

	private void filterList(String text) {
		ArrayList<ArtifactsSet> filteredList = new ArrayList<>();
		for (ArtifactsSet set: sortedArtifactsSets){
			if(set.getName().toLowerCase().contains(text.toLowerCase())){
				filteredList.add(set);
			}
		}

		if(filteredList.isEmpty()){
			setsListAdapter.setFilteredList(filteredList);
			makeToast("Артефакты не найдены");
		} else {
			setsListAdapter.setFilteredList(filteredList);
		}
	}

	View.OnClickListener setRButtonClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			boolean isSelected = ((RadioButton) view).isChecked();
			switch (view.getId()) {
				case R.id.rbAlphabet:
					if (isSelected) {
						sortedArtifactsSets = ConstantData.artifactsSets;
						setListAdapter(sortedArtifactsSets);
						System.out.println("Алфавит");
					}
					break;

				case R.id.rbRarity:
					if (isSelected) {
						System.out.println("Редкость");
						sortedArtifactsSets = (ArrayList<ArtifactsSet>) ConstantData.sortListByRarity(ConstantData.artifactsSets);
						setListAdapter(sortedArtifactsSets);
					}
					break;
			}
		}
	};

	private void setOnScrollListener(RecyclerView recyclerView){
		recyclerView.setOnScrollListener(new HiddenRecyclerView() {
			@Override
			public void show() {
				recyclerView.setPadding(0,385,0,0);
				filter_panel.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3)).start();
			}

			@Override
			public void hide() {
				search.clearFocus();
				recyclerView.setPadding(0,0,0,0);
				filter_panel.animate().translationY(-(filter_panel.getHeight() + 8)).setInterpolator(new AccelerateInterpolator(3)).start();
			}
		});
	}

	private void makeToast(String message) {
		final Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.BOTTOM, 0, 100);
		toast.setDuration(Toast.LENGTH_LONG);

		LayoutInflater inflanter = getLayoutInflater();
		View toast_l = inflanter.inflate(R.layout.custom_toast,  (ViewGroup) findViewById(R.id.custom_toast_container));
		toast.setView(toast_l);
		TextView textToast = toast_l.findViewById(R.id.toast_text);
		textToast.setText(message);
		toast.show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		search.clearFocus();
	}
}