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

}
