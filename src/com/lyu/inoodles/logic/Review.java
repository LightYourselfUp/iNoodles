package com.lyu.inoodles.logic;

import java.util.List;

import com.lyu.inoodles.infrastructure.ReviewData;

public class Review {

    int mFlav;
    int mSpic;
    int mOverall;
    List<String> mComments;

    public static Review getReviewByFkNoodles(int fkNoodles) {
        return ReviewData.getReviewByFkNoodles(fkNoodles);
    }

    public int getFlav() {
        return mFlav;
    }

    public void setFlav(int mFlav) {
        this.mFlav = mFlav;
    }

    public int getSpic() {
        return mSpic;
    }

    public void setSpic(int mSpic) {
        this.mSpic = mSpic;
    }

    public int getOverall() {
        return mOverall;
    }

    public void setOverall(int mOverall) {
        this.mOverall = mOverall;
    }

    public List<String> getComments() {
        return mComments;
    }

    public void setComments(List<String> mComments) {
        this.mComments = mComments;
    }

}
