package edu.orangecoastcollege.cs273.dreyna3.tipcalculator;

/**
 * Created by dreyna3 on 9/7/2017.
 */

public class Bill {

    private double mTotalAmount;
    private double mTipPercent;
    private double mTipAmount;
    private double mSubTotal;

    public Bill() {
        mTotalAmount = 0.0;
        mTipPercent = 0.15;
        mTipAmount = 0.0;
        mSubTotal = 0.0;
    }

    private void recalculate(){
        mTipAmount = mTipPercent * mSubTotal;
        mTotalAmount = mSubTotal + mTipAmount;
    }

    public double getTotalAmount() {
        return mTotalAmount;
    }
    public double getTipAmount() {
        return mTipAmount;
    }
    public double getTipPercent() {
        return mTipPercent;
    }
    public double getSubTotal() {
        return mSubTotal;
    }

    public void setSubTotal(double subTotal) {
        mSubTotal = subTotal;
        recalculate();
    }
    public void setTipPercent(double tipPercent) {
        mTipPercent = tipPercent;
        recalculate();
    }
}
