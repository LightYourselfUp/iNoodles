package com.lyu.inoodles.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lyu.inoodles.logic.Review;

public class ReviewData {

    private static final String BASE_URL = "http://www.lightyourselfup.com/inoodles/server/";

    public static Review getReviewByFkNoodles(int fkNoodles) {
        Review res = null;

        // acceso http
        URL url = null;
        try {
            url = new URL(BASE_URL
                    + "Review_getReviewByFkNoodles.php?fkNoodles=" + fkNoodles);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        URLConnection conn = null;
        try {
            conn = url.openConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BufferedReader rd = null;
        try {
            rd = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String mString = null;
        try {
            mString = rd.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
         * jObject attFlav attSpic attOverall
         */
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(mString);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String attFlav = null;
        try {
            attFlav = jObject.getString("flav");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String attSpic = null;
        try {
            attSpic = jObject.getString("spic");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String attOverall = null;
        try {
            attSpic = jObject.getString("over");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
         * jArray attComments
         */

        JSONArray jArray = null;
        try {
            jArray = jObject.getJSONArray("comments");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<String> attComments = new ArrayList<String>();
        try {
            for (int i = 0; i < jArray.length(); i++) {
                attComments.add(jArray.getString(i));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        res = new Review();
        res.setFlav(Integer.parseInt(attFlav));
        res.setSpic(Integer.parseInt(attSpic));
        res.setOverall(Integer.parseInt(attOverall));
        res.setComments(attComments);

        return res;
    }

}
