package com.lyu.inoodles.infrastructure;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONException;
import org.json.JSONObject;

import com.lyu.inoodles.logic.Noodles;

public class NoodlesData {

    private static final String BASE_URL = "http://www.lightyourselfup.com/inoodles/server/";
    
    public static int getNoodlesIdByBarcode(String barcode)
    {
        int res = 0;
        
        // acceso http
        URL url = null;
        try {
            url = new URL(BASE_URL + "IN_getNoodlesIdByBarcode.php?barcode=" + barcode);
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
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
                
        res = Integer.parseInt(mString);
        
        return res;
    }

	public static Noodles getNoodlesByNoodlesId(int noodlesId)
	{
		Noodles res = null;
		
        // acceso http
        URL url = null;
        try {
            url = new URL(BASE_URL + "IN_getNoodlesByNoodlesId.php?noodlesId=" + noodlesId);
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
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
        
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(mString);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        String attributeId = null;
        try {
            attributeId = jObject.getString("id");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        String attributeName = null;
        try {
            attributeName = jObject.getString("name");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        res = new Noodles();
        res.setNoodlesId(Integer.parseInt(attributeId));
        res.setName(attributeName);
       
        
		
		return res;
	}
	
   private static byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
          byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }
	
	/**
     * @param id Instant Noodles identifier
     * @return If an instant noodles with that id exist, return an 
     * array of bytes with the picture of it.
     */
	public static byte[] getPictureByNoodlesId(int noodlesId)
	{
        byte[] res = null;
        
        URL url = null;
        try {
            url = new URL(BASE_URL + "IN_getPictureById.php?id=" + noodlesId);
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
       
        try {
            res = readBytes(conn.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
           
        return res;
	}
	
}
