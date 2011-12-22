package com.lyu.inoodles.logic;

import com.lyu.inoodles.infrastructure.NoodlesData;

public class Noodles {

	int mNoodlesId;
	String mBarcode;
	String mName;
	
	public static int getNoodlesIdByBarCode(String barcode)
	{
		return NoodlesData.getNoodlesIdByBarcode(barcode);
	}
	
	public static Noodles getNoodlesByNoodlesId(int noodlesId)
	{
		return NoodlesData.getNoodlesByNoodlesId(noodlesId);
	}
	
    /**
     * @param id Instant Noodles identifier
     * @return If an instant noodles with that id exist, return an 
     * array of bytes with the picture of it.
     */
    public static byte[] getPictureByNoodlesId(int id)
    {
        byte[] bb = NoodlesData.getPictureByNoodlesId(id);
        return bb;
    }

    public int getNoodlesId()
    {
        return mNoodlesId;
    }
    
    public String getNoodlesBarcode()
    {
        return mBarcode;
    }

    public String getNoodlesName()
    {
        return mName;
    }
    
    public void setNoodlesId(int noodlesId)
	{
		mNoodlesId = noodlesId;
	}
	
    public void setBarcode(String barcode)
    {
        mBarcode = barcode;
    }

    public void setName(String name)
	{
		mName = name;
	}
}
