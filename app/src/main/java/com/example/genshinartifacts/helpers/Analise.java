package com.example.genshinartifacts.helpers;

import com.example.genshinartifacts.objectModels.ConstantData;
import com.example.genshinartifacts.objectModels.Equipping;
import com.example.genshinartifacts.objectModels.UserCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Analise {
   private UserCharacter character;
   public static String mainComment = "";
   private ArrayList<Integer> usersSets;
   private Map<ArrayList<Integer>, String> result;
   private ArrayList<Equipping> equippings;


   public Analise(UserCharacter character) {
      this.character = character;
   }

   public Map<ArrayList<Integer>, String> startAnalise(){
      mainComment = "";
      usersSets = character.getUniqueSets();
      result = new HashMap<>();
      equippings = ConstantData.getEquippingsByCharacterId(ConstantData.findCharacterByNameANDElement(character.getCharacter_name(), character.getElement()).getId());
      switch (usersSets.size()) {
         case 0:
            case0();
            break;
         case 1:
            if (character.getCharacterBonuses().size() == 2){
               for (Equipping equipping: equippings) {
                  if (equipping.getSets().size() == 1){
                     if(equipping.getSets().get(0) == usersSets.get(0)) {
                        mainComment = "Вы хорошо подобрали сет артефактов:";
                        result.put(equipping.getSets(), equipping.getComment());
                     }
                  }
               }
               if(result.size() == 0) case0();
            } else {
               findOneOfTwo(0);

               if (result.size() == 0) {
                  case0();
               } else mainComment = "Ваша сборка неполная. Можете дополнить этот сет другими:";
            }
            break;
         case 2:
            //два из двух
            boolean match = false;
            for (Equipping equipping: equippings) {
               if (equipping.getSets().size() == 2){
                  match = false;
                  if(usersSets.get(0) == equipping.getSets().get(0) || usersSets.get(0) == equipping.getSets().get(1)) {
                     if(usersSets.get(1) == equipping.getSets().get(0) || usersSets.get(1) == equipping.getSets().get(1)) {
                        match = true;
                        mainComment = "Вы хорошо подобрали сеты артефактов:";
                        result.put(equipping.getSets(), equipping.getComment());
                        break;
                     }
                  }
               }
            }

            if (!match) {
               findOneOfTwo(0);
               findOneOfTwo(1);
               if (result.size() != 0) mainComment = "Вместе эти сеты лучше не использовать. Можете попробовать следующие варианты:";
            }

            if(result.size() == 0) case0();
            break;
      }
      return result;
   }

   public void case0(){
      mainComment = "Ваш персонаж плохо собран. Попробуйте подобрать артефакты из следующих сетов:";
      if(character.getArtifacts().size() == 0) mainComment = "Ваш персонаж не собран. Попробуйте подобрать артефакты из следующих сетов:";
      for(int i = 0; i < equippings.size(); i++){
         result.put(equippings.get(i).getSets(), equippings.get(i).getComment());
      }
   }

   public void findOneOfTwo(int iterator){
      for (Equipping equipping: equippings) {
         if (equipping.getSets().size() == 2){
            for (int set_id: equipping.getSets()){
               if(set_id == usersSets.get(iterator)){
                  result.put(equipping.getSets(), equipping.getComment());
               }
            }
         }
      }
   }
}
