package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {

            // get the whole sandwitch object
            JSONObject sandwitchObject = new JSONObject(json);

            // get the sandwitch name object (mainName + alsoKnownAs)
            JSONObject nameObject = sandwitchObject.getJSONObject("name");

            String mainName = nameObject.getString("mainName");

            JSONArray alsoKnownArray = nameObject.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownArray.length(); i++) {
                alsoKnownAs.add(alsoKnownArray.getString(i));
            }

            // get sandwitch place of origin
            String placeOfOrigin = sandwitchObject.getString("placeOfOrigin");

            // get sandwitch description
            String description = sandwitchObject.getString("description");

            // get sandwitch image
            String image = sandwitchObject.getString("image");

            // get sandwitch ingredients array
            List<String> ingredients = new ArrayList<>();
            JSONArray ingredientsArray = sandwitchObject.getJSONArray("ingredients");
            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
