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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Base64;

import com.lyu.inoodles.logic.Reviews;

public class ReviewData extends GlobalData {

    public static Reviews getReviewsByFkNoodles(int fkNoodles) {
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

    public static String AddReview(String barcode, byte[] picture,
            float flavour, float spicy, float overall, String comment) {
        
        String res = "";

        if (FAKE_PICTURE_TRANSFER) {
            
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return res;
            
        } else {

            try {
                // Create a new HttpClient and Post Header
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(BASE_URL + "AddReview.php");

                // Add your data

                /*
                 * barcode picture flavour spicy overall comment
                 */

                MultipartEntity multipartContent = new MultipartEntity(
                        HttpMultipartMode.BROWSER_COMPATIBLE);

                StringBody sbBarcode = new StringBody(barcode);
                multipartContent.addPart("barcode", sbBarcode);

                if (picture != null) {
                    ByteArrayBody babPicture = new ByteArrayBody(Base64.encode(
                            picture, Base64.DEFAULT), "nombre_chingon");
                    multipartContent.addPart("picture", babPicture);
                }

                StringBody sbFlavour = new StringBody(Float.toString(flavour));
                multipartContent.addPart("flavour", sbFlavour);

                StringBody sbSpicy = new StringBody(Float.toString(spicy));
                multipartContent.addPart("spicy", sbSpicy);

                StringBody sbOverall = new StringBody(Float.toString(overall));
                multipartContent.addPart("overall", sbOverall);

                StringBody sbComment = new StringBody(comment);
                multipartContent.addPart("comment", sbComment);

                httppost.setEntity(multipartContent);

                // Execute HTTP Post Request
                HttpResponse r = httpclient.execute(httppost);

                // res = r.getStatusLine().getStatusCode();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(r.getEntity().getContent(),
                                "UTF-8"));
                String sResponse;
                StringBuilder s = new StringBuilder();

                while ((sResponse = reader.readLine()) != null) {
                    s = s.append(sResponse);
                }
                res = s.toString();

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }

            return res;
        }
    }
}
