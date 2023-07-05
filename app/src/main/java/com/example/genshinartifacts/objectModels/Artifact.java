package com.example.genshinartifacts.objectModels;

public class Artifact {
   private int id;
   private int set_id;
   private String name;
   private byte[] img;
   private int type;

   public Artifact() {
   }

   public Artifact(int id, int set_id, String name, byte[] img, int type) {
      this.id = id;
      this.set_id = set_id;
      this.name = name;
      this.img = img;
      this.type = type;
   }

   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }

   public int getSet_id() {
      return set_id;
   }
   public void setSet_id(int set_id) {
      this.set_id = set_id;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public byte[] getImg() {
      return img;
   }
   public void setImg(byte[] img) {
      this.img = img;
   }

   public int getType() {
      return type;
   }
   public void setType(int type) {
      this.type = type;
   }
}
