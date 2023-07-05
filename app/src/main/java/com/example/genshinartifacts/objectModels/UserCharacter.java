package com.example.genshinartifacts.objectModels;

import java.util.ArrayList;

public class UserCharacter {
   private String character_name;
   private String weapon;
   private String element;
   private int level;
   private int constellation;
   private ArrayList<Integer> artifacts_id = new ArrayList<>();
   private ArrayList<String> characterBonuses = new ArrayList<>();

   public UserCharacter(){}

   public UserCharacter(String character_name, String weapon, String element, int level, int constellation) {
      this.character_name = character_name;
      this.weapon = weapon;
      this.element = element;
      this.level = level;
      this.constellation = constellation;
   }

   public String getCharacter_name() {
      return character_name;
   }
   public void setCharacter_name(String character_name) {
      this.character_name = character_name;
   }

   public String getWeapon() {
      return weapon;
   }
   public void setWeapon(String weapon) {
      this.weapon = weapon;
   }

   public String getElement() {
      return element;
   }
   public void setElement(String element) {
      this.element = element;
   }

   public int getLevel() {
      return level;
   }
   public void setLevel(int level) {
      this.level = level;
   }

   public int getConstellation() {
      return constellation;
   }
   public void setConstellation(int constellation) {
      this.constellation = constellation;
   }

   public ArrayList<Integer> getArtifacts() {
      return artifacts_id;
   }
   public void setArtifacts(ArrayList<Integer> artifacts_id) {
      this.artifacts_id = artifacts_id;
   }

   public void addArtifactByName(String artifact) {
      artifacts_id.add(ConstantData.findArtifactByName(artifact).getId());
   }

   public byte[] getSmall_img() {
      return ConstantData.findCharacterByName(character_name).getSmall_img();
   }

   public byte[] getFull_img() {
      return  ConstantData.findCharacterByName(character_name).getFull_img();
   }

   public ArrayList<String> getCharacterBonuses(){
      ArrayList<String> bonuses = new ArrayList<>();
      int equals = 1;
      for (int i = 0; i < artifacts_id.size(); i++){
         for (int j = i+1; j < artifacts_id.size(); j++){
            if (ConstantData.findArtifactById(artifacts_id.get(i)).getSet_id() == ConstantData.findArtifactById(artifacts_id.get(j)).getSet_id()) {
               equals++;
            }
         }
         if(equals == 2) bonuses.add(ConstantData.findSetById(ConstantData.findArtifactById(artifacts_id.get(i)).getSet_id()).getSimpleBonus_2());
         if(equals == 4) {
            bonuses.add(ConstantData.findSetById(ConstantData.findArtifactById(artifacts_id.get(i)).getSet_id()).getSimpleBonus_2());
            bonuses.add(ConstantData.findSetById(ConstantData.findArtifactById(artifacts_id.get(i)).getSet_id()).getSimpleBonus_4());
            characterBonuses = bonuses;
            return bonuses;
         }
         equals = 1;
      }
      if (bonuses.size() == 0) bonuses.add("Бонусов нет");
      characterBonuses = bonuses;
      return bonuses;
   }

   public ArrayList<Integer> getUniqueSets() {
      ArrayList<Integer> buffer_sets = new ArrayList<>();
      for (int i = 0; i < artifacts_id.size(); i++){
         buffer_sets.add(ConstantData.findArtifactById(artifacts_id.get(i)).getSet_id());
      }

      int equals = 1;
      ArrayList<Integer> sets = new ArrayList<>();
      for (int i = 0; i < buffer_sets.size(); i++){
         equals = 1;
         for (int j = i+1; j < buffer_sets.size(); j++){
            if (buffer_sets.get(j) == buffer_sets.get(i)) {
               equals++;
            }
         }
         if(equals >= 2) {
            boolean find = false;
            for (int k = 0; k < sets.size(); k++) {
               if (buffer_sets.get(i) == sets.get(k)) find = true;
            }
            if (!find) sets.add(buffer_sets.get(i));
         }
      }
      return sets;
   }
}
