/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chordant.mytravelskill.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.User;
import com.amazon.ask.request.Predicates;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author groov
 */
public class StartJourneyIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("StartJourneyIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        
        String email = "peter.thompson@touchtechconsulting.com";
        String name = "Pete";

        //String accessToken = input
       //         .getRequestEnvelope()
        //        .getContext()
        //        .getSystem()
       //         .getUser()
       //         .getAccessToken();
        
        User alexaUser = input.getRequestEnvelope().getSession().getUser();
        String accessToken = alexaUser.getAccessToken();        
        
        System.out.println("accessToken: "+accessToken);
        
        String url = "https://api.amazon.com/user/profile?access_token=" + accessToken;
        try {
            JSONObject json = readJsonFromUrl(url);
            email = json.getString("email");
            name = json.getString("name");
            
        } catch (IOException ioe) {
            System.out.println("Unable to access Alexa User Token");
        }
        
        //Set user active to periodically log user's location
        //UserHandler ul = new UserHandler();
        //ul.setUserActiveByEmail("peter.thompson@touchtechconsulting.com");        
        
        String speechText = "Starting your journey Logging Now, "+name;
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .build();
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

}
