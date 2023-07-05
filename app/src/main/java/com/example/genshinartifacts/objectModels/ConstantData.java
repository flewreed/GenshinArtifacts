package com.example.genshinartifacts.objectModels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ConstantData {
	public static int uid;
	public static int ltuid;
	public static String ltoken;
	public static ArrayList<Character> characters = new ArrayList<>();
	public static ArrayList<ArtifactsSet> artifactsSets = new ArrayList<>();
	public static ArrayList<Artifact> artifacts = new ArrayList<>();
	public static ArrayList<UserCharacter> usersCharacters = new ArrayList<>();
	public static ArrayList<Equipping> equippings = new ArrayList<>();

	public static Character findCharacterById(int id) {
		for(int i = 0; i < characters.size(); i++) {
			if (characters.get(i).getId() == id)
				return characters.get(i);
		}
		return null;
	}

	public static Character findCharacterByName(String name) {
		for(int i = 0; i < characters.size(); i++) {
			if (characters.get(i).getName().equals(name)) {
				return characters.get(i);
			}
		}
		return characters.get(24);
	}

	public static Character findCharacterByNameANDElement(String name, String element) {
		boolean find = false;
		for(int i = 0; i < characters.size(); i++) {
			if (characters.get(i).getName().equals(name)) {
				find = true;
				return characters.get(i);
			}
		}
		if(!find) {
			for(int i = 0; i < characters.size(); i++) {
				if (characters.get(i).getName().startsWith("Пут")) {
					if(characters.get(i).getElement_name().equals(element)) {
						return characters.get(i);
					}
				}
			}
		}
		return characters.get(24);
	}

	public static UserCharacter findUserCharacterByName(String name) {
		for(int i = 0; i < usersCharacters.size(); i++) {
			if (usersCharacters.get(i).getCharacter_name().equals(name))
				return usersCharacters.get(i);
		}
		return null;
	}

	public static ArtifactsSet findSetById(int id) {
		for(int i = 0; i < artifactsSets.size(); i++) {
			if (artifactsSets.get(i).getId() == id)
				return artifactsSets.get(i);
		}
		return null;
	}

	public static Artifact findArtifactById(int id) {
		for(int i = 0; i < artifacts.size(); i++) {
			if (artifacts.get(i).getId() == id)
				return artifacts.get(i);
		}
		return null;
	}

	public static Artifact findArtifactByName(String name) {
		for(int i = 0; i < artifacts.size(); i++) {
			if (artifacts.get(i).getName().equals(name))
				return artifacts.get(i);
		}
		return artifacts.get(0);
	}

	public static ArrayList<Artifact> findArtifactsBySetId(int set_id) {
		ArrayList<Artifact> set = new ArrayList<>();
		for (Artifact art: artifacts) {
			if (art.getSet_id() == set_id) set.add(art);
		}
		return set;
	}

	public static Artifact findArtifactFromListByType(ArrayList<Artifact> set, int type) {
		for (Artifact art: set) {
			if (art.getType() == type) return art;
		}
		return null;
	}

	public static ArrayList<Equipping> getEquippingsByCharacterId(int id){
		ArrayList<Equipping> eq = new ArrayList<>();
		for (Equipping equipping: equippings) {
			if (equipping.getCharacter_id() == id) eq.add(equipping);
		}
		return eq;
	}

	public static ArrayList<?> sortListByRarity(ArrayList<?> list){
		if (list.get(0) instanceof Character){
			ArrayList<Character> sortedList = new ArrayList<Character>((Collection<? extends Character>) list);
			Comparator<Character> rarityComparator = Comparator.comparing(Character::getRarity).reversed().thenComparing(Character::getName);
			Collections.sort(sortedList, rarityComparator);
			return sortedList;
		} else if (list.get(0) instanceof ArtifactsSet){
			ArrayList<ArtifactsSet> sortedList = new ArrayList<ArtifactsSet>((Collection<? extends ArtifactsSet>) list);
			Comparator<ArtifactsSet> rarityComparator = Comparator.comparing(ArtifactsSet::getRarity).reversed().thenComparing(ArtifactsSet::getName);
			Collections.sort(sortedList, rarityComparator);
			return sortedList;
		}
		return new ArrayList<>();
	}

	public static ArrayList<Character> sortCharacterListByElement(String element){
		ArrayList<Character> sortedList = new ArrayList<>();

		for(int i = 0; i < characters.size(); i++) {
			if(characters.get(i).getElement_name().equals(element)) {
				sortedList.add(characters.get(i));
			}
		}
		Comparator<Character> nameComparator = Comparator.comparing(Character::getName);
		Collections.sort(sortedList, nameComparator);
		return sortedList;
	}
}
