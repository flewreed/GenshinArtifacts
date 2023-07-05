package com.example.genshinartifacts.objectModels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Character {
   private int id;
   private String name;
   private byte[] small_img;
   private byte[] full_img;
   private String element_name;
   private String weapon;
   private int rarity;
   private String region;
   private String description;
   private ArrayList<String> strong_points;
   private ArrayList<String> weak_points;
   private ArrayList<Integer> sets;
   private ArrayList<String> stats_type;
   private Map<String, ArrayList<String>> stats;


   public Character(int id, String name, byte[] small_img, byte[] full_img, String element_name, String weapon, int rarity, String region, String description, ArrayList<String> strong_points, ArrayList<String> weak_points, ArrayList<Integer> sets) {
      this.id = id;
      this.name = name;
      this.small_img = small_img;
      this.full_img = full_img;
      this.element_name = element_name;
      this.weapon = weapon;
      this.rarity = rarity;
      this.region = region;
      this.description = description;
      this.strong_points = strong_points;
      this.weak_points = weak_points;
      this.sets = sets;
   }

   public Character() {
   }

   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public byte[] getSmall_img() {
      return small_img;
   }
   public void setSmall_img(byte[] small_img) {
      this.small_img = small_img;
   }

   public byte[] getFull_img() {
      return full_img;
   }
   public void setFull_img(byte[] full_img) {
      this.full_img = full_img;
   }

   public String getElement_name() {
      return element_name;
   }
   public void setElement_name(String element_name) {
      this.element_name = element_name;
   }

   public String getWeapon() {
      return weapon;
   }
   public void setWeapon(String weapon) {
      this.weapon = weapon;
   }

   public int getRarity() {
      return rarity;
   }
   public void setRarity(int rarity) {
      this.rarity = rarity;
   }

   public String getRegion() {
      return region;
   }
   public void setRegion(String region) {
      this.region = region;
   }

   public String getDescription() {
      return description;
   }
   public void setDescription(String description) {
      this.description = description;
   }

   public Map<String, ArrayList<String>> getStats() {
      return stats;
   }
   public void setStats(Map<String, ArrayList<String>> stats) {
      this.stats = stats;
   }

   public ArrayList<String> getStats_type() {
      return stats_type;
   }
   public void setStats_type(ArrayList<String> stats_type) {
      this.stats_type = stats_type;
   }

   public ArrayList<String> getStrong_points() {
      return strong_points;
   }
   public void setStrong_points(String strong_points) {
      this.strong_points = new ArrayList<>(Arrays.asList(strong_points.split("\\\\n")));
   }

   public ArrayList<String> getWeak_points() {
      return weak_points;
   }
   public void setWeak_points(String weak_points) {
      this.weak_points = new ArrayList<>(Arrays.asList(weak_points.split("\\\\n")));
   }

   public ArrayList<Integer> getSets() {
      return sets;
   }
   public void setSets(ArrayList<Integer> sets) {
      this.sets = sets;
   }


   public byte[] getElementImage() {
      return ElementImage.getElementImage(this.element_name);
   }

   public String getRarityString() {
      if (rarity == 5) return "⭐⭐⭐⭐⭐";
      else return "⭐⭐⭐⭐";
   }
}
