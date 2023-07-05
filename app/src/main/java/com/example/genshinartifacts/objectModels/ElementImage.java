package com.example.genshinartifacts.objectModels;

import java.util.HashMap;
import java.util.Map;

public class ElementImage {
   public static Map<String, byte[]> element_images = new HashMap<String, byte[]>();

   public ElementImage() {
   }

   public static void putElement(String name, byte[] img) {
      element_images.put(name, img);
   }

   public static void setElement_images(Map<String, byte[]> element_images) {
      ElementImage.element_images = element_images;
   }
   public static Map<String, byte[]> getElement_images() {
      return element_images;
   }

   // поиск каринки елемента
   public static byte[] getElementImage(String element) {
      byte[] image = element_images.get(element);
      return image;
   }
}