package com.example.genshinartifacts.objectModels;

public class ArtifactsSet {
   private int id;
   private String name;
   private String bonus_2;
   private String bonus_4;
   private int rarity;

   public ArtifactsSet(){ }

   public ArtifactsSet(int id, String name, String bonus_2, String bonus_4, int rarity) {
      this.id = id;
      this.name = name;
      this.bonus_2 = bonus_2;
      this.bonus_4 = bonus_4;
      this.rarity = rarity;
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

   public String getBonus_2() {
      return "2 предмета: " + bonus_2;
   }
   public void setBonus_2(String bonus_2) {
      this.bonus_2 = bonus_2;
   }

   public String getBonus_4() {
      return "4 предмета: " + bonus_4;
   }
   public void setBonus_4(String bonus_4) {
      this.bonus_4 = bonus_4;
   }

   public int getRarity() {
      return rarity;
   }
   public void setRarity(int rarity) {
      this.rarity = rarity;
   }

   public String getSimpleBonus_2() {
      return bonus_2;
   }
   public String getSimpleBonus_4() {
      return bonus_4;
   }

   public byte[] getImg() {
      return ConstantData.findArtifactFromListByType(ConstantData.findArtifactsBySetId(id), 1).getImg();
   }
}
