package com.example.genshinartifacts.objectModels;

import java.util.ArrayList;

public class Equipping {
   private int id;
   private int character_id;
   private ArrayList<Integer> sets;
   private String comment;

   public Equipping() {
   }
   public Equipping(int id, int character_id, ArrayList<Integer> sets, String comment) {
      this.id = id;
      this.character_id = character_id;
      this.sets = sets;
      this.comment = comment;
   }

   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }

   public int getCharacter_id() {
      return character_id;
   }
   public void setCharacter_id(int character_id) {
      this.character_id = character_id;
   }

   public ArrayList<Integer> getSets() {
      return sets;
   }
   public void setSets(ArrayList<Integer> sets) {
      this.sets = sets;
   }

   public String getComment() {
      return comment;
   }
   public void setComment(String comment) {
      this.comment = comment;
   }
}
