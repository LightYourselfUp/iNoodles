package com.lyu.inoodles.logic;

import java.util.List;

import com.lyu.inoodles.infrastructure.ReviewData;

/**
 * Existing reviews of a Noodles
 */
public class Reviews {

    float mFlav;
    float mSpic;
    float mOverall;
    List<String> mComments;

    public static Reviews getReviewsByFkNoodles(int fkNoodles) {
        return ReviewData.getReviewsByFkNoodles(fkNoodles);
    }

    public float getFlav() {
        return mFlav;
    }

    public void setFlav(float mFlav) {
        this.mFlav = mFlav;
    }

    public float getSpic() {
        return mSpic;
    }

    public void setSpic(float mSpic) {
        this.mSpic = mSpic;
    }

    public float getOverall() {
        return mOverall;
    }

    public void setOverall(float mOverall) {
        this.mOverall = mOverall;
    }

    public List<String> getComments() {
        return mComments;
    }

    public void setComments(List<String> mComments) {
        this.mComments = mComments;
    }

}
