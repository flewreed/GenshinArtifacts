package com.example.genshinartifacts.helpers;

import android.content.Context;

import com.example.genshinartifacts.R;
import com.example.genshinartifacts.objectModels.ConstantData;
import com.example.genshinartifacts.objectModels.UserCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class UsersDataJSONParserHelper {

   public static boolean getUsersData(Context context, String jsonText) {
      boolean success = false;
      try {
         success = readUsersJSONFile(context, jsonText);
      } catch (IOException e) {
         e.printStackTrace();
      } catch (JSONException e) {
         e.printStackTrace();
      }
      return success;
   }

   public static boolean readUsersJSONFile(Context context, String jsonText) throws IOException, JSONException {
      boolean success = true;

      if (jsonText.length() == 0) {
         jsonText = readText(context, R.raw.user_data);
      }

      JSONObject userObject = new JSONObject(jsonText);

      ConstantData.usersCharacters = new ArrayList<>();
      ConstantData.uid = userObject.getInt("uid");
      ConstantData.ltuid = userObject.getInt("ltuid");
      ConstantData.ltoken = userObject.getString("ltoken");
      JSONArray jsonCharactersArray = userObject.getJSONArray("characters");

      for(int j = 0; j < jsonCharactersArray.length(); j++) {
         JSONObject characterObject = jsonCharactersArray.getJSONObject(j);
         UserCharacter character = new UserCharacter(
                 characterObject.getString("name"),
                 characterObject.getString("weapon"),
                 characterObject.getString("element"),
                 characterObject.getInt("level"),
                 characterObject.getInt("constellation")
         );

         JSONArray jsonArtifactsArray = characterObject.getJSONArray("artifacts");
         ArrayList<Integer> artifacts_id = new ArrayList<>();

         for(int k = 0; k < jsonArtifactsArray.length(); k++) {
            JSONObject artifactObject = jsonArtifactsArray.getJSONObject(k);
            artifacts_id.add(ConstantData.findArtifactByName(artifactObject.getString("name")).getId());
         }
         character.setArtifacts(artifacts_id);
         ConstantData.usersCharacters.add(character);
      }
      return success;
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
}
