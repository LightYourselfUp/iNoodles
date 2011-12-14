package com.lyu.inoodles.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lyu.inoodles.logic.Reviews;

public class ReviewData extends GlobalData {

    public static Reviews getReviewByFkNoodles(int fkNoodles) {
        Reviews res = null;

        // acceso http
        URL url = null;
        try {
            url = new URL(BASE_URL
                    + "Review_getReviewsByFkNoodles.php?fkNoodles=" + fkNoodles);
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
            attOverall = jObject.getString("over");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /*
         * jArray attComments
         */

        JSONArray jArray = null;
        try {
            jArray = jObject.getJSONArray("comm");
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

        res = new Reviews();
        res.setFlav(Float.parseFloat(attFlav));
        res.setSpic(Float.parseFloat(attSpic));
        res.setOverall(Float.parseFloat(attOverall));
        res.setComments(attComments);

        return res;
    }

    public static int AddReview(float flavour, float spicy, float overall,
            String comment) {
        int res = 0;

        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(BASE_URL + "Review_AddReview.php");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("flavour", Float
                    .toString(flavour)));
            nameValuePairs.add(new BasicNameValuePair("spicy", Float
                    .toString(spicy)));
            nameValuePairs.add(new BasicNameValuePair("overall", Float
                    .toString(overall)));
            nameValuePairs.add(new BasicNameValuePair("comment", comment));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse r = httpclient.execute(httppost);
            res = r.getStatusLine().getStatusCode();

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

        return res;
    }

}
