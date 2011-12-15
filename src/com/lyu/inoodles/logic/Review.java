package com.lyu.inoodles.logic;

import com.lyu.inoodles.infrastructure.ReviewData;

/**
 * A review for a Noodles
 */
public class Review {

    float mFlav;
    float mSpic;
    float mOverall;
    String mComment;

    public static int AddReview(byte[] picture, float flavour, float spicy,
            float overall, String comment) {
        return ReviewData.AddReview(picture, flavour, spicy, overall, comment);
    }

}
