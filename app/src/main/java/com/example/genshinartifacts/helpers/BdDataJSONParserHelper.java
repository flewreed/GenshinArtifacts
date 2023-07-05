package com.example.genshinartifacts.helpers;

import android.content.Context;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.objectModels.Artifact;
import com.example.genshinartifacts.objectModels.ArtifactsSet;
import com.example.genshinartifacts.objectModels.Character;
import com.example.genshinartifacts.objectModels.ConstantData;
import com.example.genshinartifacts.objectModels.ElementImage;
import com.example.genshinartifacts.objectModels.Equipping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class BdDataJSONParserHelper {

	public static void readCharactersJSONFile(Context context) throws IOException, JSONException {
		String jsonText = readText(context, R.raw.characters);
		ConstantData.characters = new ArrayList<>();

		JSONObject jsonRoot = new JSONObject(jsonText);

		JSONArray jsonCharactersArray = jsonRoot.getJSONArray("characters");
		for(int i = 0; i < jsonCharactersArray.length(); i++) {
			JSONObject characterObject = jsonCharactersArray.getJSONObject(i);

			Character character = new Character();
			character.setId(characterObject.getInt("character_id"));
			character.setName(characterObject.getString("character_name"));
			character.setSmall_img(Base64.getMimeDecoder().decode(characterObject.getString("character_small_img")));
			character.setFull_img(Base64.getMimeDecoder().decode(characterObject.getString("character_large_img")));
			character.setElement_name(characterObject.getString("element"));
			character.setWeapon(characterObject.getString("weapon"));
			character.setRarity(characterObject.getInt("rarity"));
			character.setDescription(characterObject.getString("description"));
			character.setStrong_points(characterObject.getString("strong_points"));
			character.setWeak_points(characterObject.getString("weak_points"));
			character.setRegion(characterObject.getString("region"));

			ArrayList<Integer> sets_id = new ArrayList<>();
			JSONArray jsonSetsArray = characterObject.getJSONArray("sets");
			for (int j = 0; j < jsonSetsArray.length(); j++) {
				sets_id.add(Integer.parseInt(jsonSetsArray.get(j).toString()));
			}
			character.setSets(sets_id);

			Map<String, ArrayList<String>> stats = new HashMap<>();
			ArrayList<String> stats_type = new ArrayList<>();
			JSONArray jsonStatsArray = characterObject.getJSONArray("char_stats");
			for (int j = 0; j < jsonStatsArray.length(); j++) {
				JSONObject statsObject = jsonStatsArray.getJSONObject(j);

				ArrayList<String> statsArray = new ArrayList<>();
				JSONArray jsonStatArray = statsObject.getJSONArray("stats");
				for (int k = 0; k < jsonStatArray.length(); k++) {
					statsArray.add(jsonStatArray.get(k).toString());
				}
				stats.put(statsObject.getString("type"), statsArray);
				stats_type.add(statsObject.getString("type"));
			}
			character.setStats_type(stats_type);
			character.setStats(stats);
			ConstantData.characters.add(character);
		}
	}

	public static void readElementJSONFile(Context context) throws IOException, JSONException {
		ElementImage.element_images = new HashMap<>();
		String jsonText = readText(context, R.raw.element);

		JSONObject jsonRoot = new JSONObject(jsonText);

		JSONArray jsonElementsArray = jsonRoot.getJSONArray("elements");
		for(int i = 0; i < jsonElementsArray.length(); i++) {
			JSONObject elementObject = jsonElementsArray.getJSONObject(i);

			ElementImage.putElement(
					elementObject.getString("element_name"),
					Base64.getMimeDecoder().decode(elementObject.getString("element_img"))
			);
		}
	}

	public static void readSetsJSONFile(Context context) throws IOException, JSONException {
		String jsonText = readText(context, R.raw.sets);
		ConstantData.artifactsSets = new ArrayList<>();

		JSONObject jsonRoot = new JSONObject(jsonText);

		JSONArray jsonSetsArray = jsonRoot.getJSONArray("sets");
		for(int i = 0; i < jsonSetsArray.length(); i++) {
			JSONObject setObject = jsonSetsArray.getJSONObject(i);

			ArtifactsSet set = new ArtifactsSet(
					setObject.getInt("set_id"),
					setObject.getString("set_name"),
					setObject.getString("2_piece_bonus"),
					setObject.getString("4_piece_bonus"),
					setObject.getInt("set_rarity")
			);
			ConstantData.artifactsSets.add(set);
		}
	}

	public static void readArtifactsJSONFile(Context context) throws IOException, JSONException {
		String jsonText = readText(context, R.raw.arts);
		ConstantData.artifacts = new ArrayList<>();

		JSONObject jsonRoot = new JSONObject(jsonText);

		JSONArray jsonArtifactsArray = jsonRoot.getJSONArray("arts");
		for(int i = 0; i < jsonArtifactsArray.length(); i++) {
			JSONObject artifactObject = jsonArtifactsArray.getJSONObject(i);

			Artifact artifact = new Artifact(
					artifactObject.getInt("art_id"),
					artifactObject.getInt("set_id"),
					artifactObject.getString("art_name"),
					Base64.getMimeDecoder().decode(artifactObject.getString("art_img")),
					artifactObject.getInt("type_id")
			);
			ConstantData.artifacts.add(artifact);
		}
	}

	public static void readEquippingJSONFile(Context context) throws IOException, JSONException {
		String jsonText = readText(context, R.raw.equipping);
		ConstantData.equippings = new ArrayList<>();

		JSONObject jsonRoot = new JSONObject(jsonText);

		JSONArray jsonElementsArray = jsonRoot.getJSONArray("equipping");
		for(int i = 0; i < jsonElementsArray.length(); i++) {
			JSONObject equippingObject = jsonElementsArray.getJSONObject(i);

			Equipping equipping = new Equipping();
			equipping.setId(equippingObject.getInt("equipping_id"));
			equipping.setCharacter_id(equippingObject.getInt("character_id"));
			equipping.setComment(equippingObject.getString("comment"));

			ArrayList<Integer> sets_id = new ArrayList<>();
			JSONArray jsonSetsArray = equippingObject.getJSONArray("sets");
			for (int j = 0; j < jsonSetsArray.length(); j++) {
				sets_id.add(Integer.parseInt(jsonSetsArray.get(j).toString()));
			}
			equipping.setSets(sets_id);
			ConstantData.equippings.add(equipping);
		}
	}

	private static String readText(Context context, int resId) throws IOException {
		InputStream is = context.getResources().openRawResource(resId);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String s = null;
		while((s = br.readLine())!=null) {
			sb.append(s);
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void getDataJSONFile(Context context) {
		try {
			readCharactersJSONFile(context);
			readElementJSONFile(context);
			readSetsJSONFile(context);
			readArtifactsJSONFile(context);
			readEquippingJSONFile(context);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
