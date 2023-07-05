package com.example.genshinartifacts.charactersInfoPage;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.adapters.CharactersListAdapter;
import com.example.genshinartifacts.adapters.other.HiddenRecyclerView;
import com.example.genshinartifacts.objectModels.Character;
import com.example.genshinartifacts.objectModels.ConstantData;

import java.util.ArrayList;

public class CharactersActivity extends AppCompatActivity {

	private ConstraintLayout filter_panel;
	private RecyclerView characterList;
	private SearchView search;
	private CharactersListAdapter charactersListAdapter;

	private RadioGroup rgElements;
	private RadioButton alphabet_rbtn;
	private RadioButton rarity_rbtn;
	private RadioButton element_rbtn;

	private RadioButton rbKrio;
	private RadioButton rbElectro;
	private RadioButton rbAnemo;
	private RadioButton rbPiro;
	private RadioButton rbGidro;
	private RadioButton rbGeo;
	private RadioButton rbDendro;

	private ArrayList<Character> sortedCharactersList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_characters);

		sortedCharactersList = new ArrayList<>(ConstantData.characters);
		characterList = findViewById(R.id.characterList);
		filter_panel = findViewById(R.id.filter_panel);
		characterListAdapter(sortedCharactersList);
		setOnScrollListener(characterList);

		alphabet_rbtn = findViewById(R.id.rbAlphabet);
		alphabet_rbtn.setChecked(true);
		rarity_rbtn = findViewById(R.id.rbRarity);
		element_rbtn = findViewById(R.id.rbElement);
		alphabet_rbtn.setOnClickListener(setRButtonClickListener);
		rarity_rbtn.setOnClickListener(setRButtonClickListener);
		element_rbtn.setOnClickListener(setRButtonClickListener);

		rgElements = findViewById(R.id.rgElements);

		rbKrio = findViewById(R.id.rbKrio);
		rbElectro = findViewById(R.id.rbElectro);
		rbAnemo = findViewById(R.id.rbAnemo);
		rbPiro= findViewById(R.id.rbPiro);
		rbGidro = findViewById(R.id.rbGidro);
		rbGeo = findViewById(R.id.rbGeo);
		rbDendro = findViewById(R.id.rbDendro);
		rbKrio.setOnClickListener(setRButtonElementsClickListener);
		rbElectro.setOnClickListener(setRButtonElementsClickListener);
		rbAnemo.setOnClickListener(setRButtonElementsClickListener);
		rbPiro.setOnClickListener(setRButtonElementsClickListener);
		rbGidro.setOnClickListener(setRButtonElementsClickListener);
		rbGeo.setOnClickListener(setRButtonElementsClickListener);
		rbDendro.setOnClickListener(setRButtonElementsClickListener);

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

	private void characterListAdapter(ArrayList<Character> characters) {
		charactersListAdapter = new CharactersListAdapter(this, characters);
		characterList.setLayoutManager(new LinearLayoutManager(this));
		characterList.setAdapter(charactersListAdapter);
	}

	private void filterList(String text) {
		ArrayList<Character> filteredList = new ArrayList<>();
		for (Character character: sortedCharactersList){
			if(character.getName().toLowerCase().contains(text.toLowerCase())){
				filteredList.add(character);
			}
		}

		if(filteredList.isEmpty()){
			charactersListAdapter.setFilteredList(filteredList);
			makeToast("Персонажи не найдены");
		} else {
			charactersListAdapter.setFilteredList(filteredList);
		}
	}

	View.OnClickListener setRButtonClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			boolean isSelected = ((RadioButton) view).isChecked();
			if(!element_rbtn.isChecked()) {
				element_rbtn.setText("Элемент");
				rgElements.setVisibility(View.GONE);
				rgElements.clearCheck();
			}
			switch (view.getId()) {
				case R.id.rbAlphabet:
					if (isSelected) {
						sortedCharactersList = ConstantData.characters;
						characterListAdapter(sortedCharactersList);
					}
					break;

				case R.id.rbRarity:
					if (isSelected) {
						sortedCharactersList = (ArrayList<Character>) ConstantData.sortListByRarity(ConstantData.characters);
						characterListAdapter(sortedCharactersList);
					}
					break;

				case R.id.rbElement:
					if (isSelected) {
						System.out.println("Элемент");
						rgElements.setVisibility(View.VISIBLE);
					}
					break;
			}
		}
	};

	View.OnClickListener setRButtonElementsClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			rgElements.setVisibility(View.GONE);
			boolean isSelected = ((RadioButton) view).isChecked();
			switch (view.getId()) {
				case R.id.rbKrio:
					if (isSelected) {
						element_rbtn.setText(rbKrio.getText());
						sortedCharactersList = ConstantData.sortCharacterListByElement(rbKrio.getText().toString());
						characterListAdapter(sortedCharactersList);
						element_rbtn.setText(rbKrio.getText());
					}
					break;

				case R.id.rbElectro:
					if (isSelected) {
						element_rbtn.setText(rbElectro.getText());
						sortedCharactersList = ConstantData.sortCharacterListByElement(rbElectro.getText().toString());
						characterListAdapter(sortedCharactersList);
						characterListAdapter(sortedCharactersList);
					}
					break;

				case R.id.rbAnemo:
					if (isSelected) {
						element_rbtn.setText(rbAnemo.getText());
						sortedCharactersList = ConstantData.sortCharacterListByElement(rbAnemo.getText().toString());
						characterListAdapter(sortedCharactersList);
						element_rbtn.setText(rbAnemo.getText());
					}
					break;

				case R.id.rbPiro:
					if (isSelected) {
						element_rbtn.setText(rbPiro.getText());
						sortedCharactersList = ConstantData.sortCharacterListByElement(rbPiro.getText().toString());
						characterListAdapter(sortedCharactersList);
						element_rbtn.setText(rbPiro.getText());
					}
					break;

				case R.id.rbGidro:
					if (isSelected) {
						element_rbtn.setText(rbGidro.getText());
						sortedCharactersList = ConstantData.sortCharacterListByElement(rbGidro.getText().toString());
						characterListAdapter(sortedCharactersList);
						element_rbtn.setText(rbGidro.getText());
					}
					break;

				case R.id.rbGeo:
					if (isSelected) {
						element_rbtn.setText(rbGeo.getText());
						sortedCharactersList = ConstantData.sortCharacterListByElement(rbGeo.getText().toString());
						characterListAdapter(sortedCharactersList);
						element_rbtn.setText(rbGeo.getText());
					}
					break;

				case R.id.rbDendro:
					if (isSelected) {
						element_rbtn.setText(rbDendro.getText());
						sortedCharactersList = ConstantData.sortCharacterListByElement(rbDendro.getText().toString());
						characterListAdapter(sortedCharactersList);
						element_rbtn.setText(rbDendro.getText());
					}
					break;
			}
		}
	};

	private void setOnScrollListener(RecyclerView recyclerView){
		recyclerView.setOnScrollListener(new HiddenRecyclerView() {
			@Override
			public void show() {
				filter_panel.animate().translationY(0).setInterpolator(new DecelerateInterpolator(3)).start();
				recyclerView.setPadding(0,385,0,0);
			}

			@Override
			public void hide() {
				if(!(rgElements.getVisibility() == View.VISIBLE)) {
					search.clearFocus();
					recyclerView.setPadding(0,0,0,0);
					filter_panel.animate().translationY(-(filter_panel.getHeight() + 8)).setInterpolator(new AccelerateInterpolator(3)).start();
				}
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