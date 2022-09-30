package edu.eliott.android_app;

import org.json.JSONException;
import org.json.JSONObject;

public class Image {

    protected String url;

    public Image(String url) {
        this.url = url;
    }

    public Image(JSONObject json) throws JSONException {
        this.url = json.getString("url");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
