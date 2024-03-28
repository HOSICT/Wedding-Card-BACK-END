package com.example.weddingCard.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ParsingEditorState {

    public String parsingEditorStateToString(String jsonRequest, String key) {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        JSONArray jsonArray = jsonObject.getJSONArray(key);

        return jsonArray.toString();
    }

    public String parsingInsideEditorStateToString(String jsonRequest, String key1, String key2) {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        JSONObject insideJsonObject = jsonObject.getJSONObject(key1);
        JSONArray jsonArray = insideJsonObject.getJSONArray(key2);

        return jsonArray.toString();
    }

    public String parsingInsideJsonObject(String jsonRequest, String key1, String key2) {
        JSONObject jsonObject = new JSONObject(jsonRequest);
        JSONObject coverJsonObject = jsonObject.getJSONObject(key1);
        String insideJsonObject = coverJsonObject.getString(key2);

        return insideJsonObject;
    }

}
