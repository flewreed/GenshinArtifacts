package com.example.genshinartifacts.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSession {
   public static final String STORAGE_NAME = "userSession";

   private static final String UID = "userUID";
   private static final String ACCOUNT_ID = "userACCOUNT_ID";
   private static final String COOKIE_TOKEN = "userCOOKIE_TOKEN";

   private static SharedPreferences userData = null;
   private static SharedPreferences.Editor editor = null;
   private static Context context = null;

   public static void init(Context cntxt){
      context = cntxt;
   }

   private static void init(){
      userData = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
      editor = userData.edit();
   }

   public static boolean isUserSessionExist(){
      if(userData == null){
         init();
      }
      if(userData.contains(UID)){
         return true;
      }
      return false;
   }

   public static void saveUserSession(String _UID, String _ACCOUNT_ID, String _COOKIE_TOKEN){
      if(userData == null){
         init();
      }
      editor.putString(UID, _UID);
      editor.putString(ACCOUNT_ID, _ACCOUNT_ID);
      editor.putString(COOKIE_TOKEN, _COOKIE_TOKEN);
      editor.apply();
   }

   public static String getUserUID(){
      if(userData == null){
         init();
      }
      return userData.getString(UID, null);
   }

   public static String getUserACCOUNT_ID(){
      if(userData == null){
         init();
      }
      return userData.getString(ACCOUNT_ID, null);
   }

   public static String getUserCOOKIE_TOKEN(){
      if(userData == null){
         init();
      }
      return userData.getString(COOKIE_TOKEN, null);
   }

   public static void removeUserSession(){
      editor.clear();
      editor.apply();
   }

   public static String getUserSession(String name){
      if(userData == null){
         init();
      }
      return userData.getString(name, null);
   }
}
