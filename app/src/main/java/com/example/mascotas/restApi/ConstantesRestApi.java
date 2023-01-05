package com.example.mascotas.restApi;

public class ConstantesRestApi {
    //https://graph.instagram.com/me/media
    public static final String VERSION = "/v15.0/";
    public static final String ME = "/me/";
    public static final String MEDIA = "media";
    public static final String USER_ID = "5649402008522057";

    public static final String ROOT_URL_MEDIA = "https://graph.instagram.com" + ME;
    public static final String ROOT_URL_USER = "https://graph.instagram.com" + VERSION;

    public static final String ACCESS_TOKEN = "IGQVJYazU4N1NjRlJrR1gzYmhaSUZAhcUlxUEktcEZA6MEdDWUhwSEZAIckRTMGhnRGdEeEs0b09Gc04yejVmN2F4cUphNHpUcXpDSDFxcEFkcm1Fb1VFSUx0QXdtVlROTmJWbFZAIRzE4TnlTVVNQbTBDQQZDZD";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";

    public static final String KEY_GET_INFORMATION_USER = USER_ID + "?fields=id,username";
    public static final String KEY_GET_MEDIA_USER = MEDIA + "?fields=id,media_type,media_url,caption";

    public static final String URL_GET_INFORMATION_USER = KEY_GET_INFORMATION_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_MEDIA_USER = KEY_GET_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //https://graph.instagram.com/v15.0/5649402008522057?fields=id,username&access_token=IGQVJYazU4N1NjRlJrR1gzYmhaSUZAhcUlxUEktcEZA6MEdDWUhwSEZAIckRTMGhnRGdEeEs0b09Gc04yejVmN2F4cUphNHpUcXpDSDFxcEFkcm1Fb1VFSUx0QXdtVlROTmJWbFZAIRzE4TnlTVVNQbTBDQQZDZD

    //https://graph.instagram.com/me/media?fields=id,media_type,media_url,caption&access_token=IGQVJYazU4N1NjRlJrR1gzYmhaSUZAhcUlxUEktcEZA6MEdDWUhwSEZAIckRTMGhnRGdEeEs0b09Gc04yejVmN2F4cUphNHpUcXpDSDFxcEFkcm1Fb1VFSUx0QXdtVlROTmJWbFZAIRzE4TnlTVVNQbTBDQQZDZD
}
