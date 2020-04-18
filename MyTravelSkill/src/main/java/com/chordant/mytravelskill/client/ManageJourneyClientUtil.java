/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chordant.mytravelskill.client;

/**
 *
 * @author groov
 */
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ManageJourneyClientUtil {

    private static String JOURNEY_URL = "http://90.255.64.249:8084/MyTravel/rest/journey";

    String EMAIL = "peter.thompson@touchtechconsulting.com";

    // one instance, reuse
    private static final CloseableHttpClient httpClient = HttpClients.createDefault();

    public ManageJourneyClientUtil() {

    }

    public void activateJourney() throws Exception {
        ManageJourneyClientUtil obj = new ManageJourneyClientUtil();

        try {
            System.out.println("Testing 1 - Change User Status to Active");           
            String result = obj.changeUserStatus(EMAIL, "activate");
            System.out.println("Result: " + result);

        } finally {
            obj.close();
        }
    }

    public void deactivateJourney() throws Exception {

        ManageJourneyClientUtil obj = new ManageJourneyClientUtil();

        try {
            System.out.println("Testing 1 - Change User Status to Inactive");
            String result = obj.changeUserStatus(EMAIL, "deactivate");
            System.out.println("Result: " + result);

        } finally {
            obj.close();
        }

    }  

    private void close() throws IOException {
        httpClient.close();
    }

    public static String changeUserStatus(String email, String command) throws Exception {

        String result = null;

        String locationUrl = JOURNEY_URL + "?email=" + email + "&command=" + command;

        HttpGet request = new HttpGet(locationUrl);

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                result = EntityUtils.toString(entity);

            }

        } finally {
        
            //httpClient.close();
        }
        
        return result;

    }

}
