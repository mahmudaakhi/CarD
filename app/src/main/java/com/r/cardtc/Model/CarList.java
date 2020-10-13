package com.r.cardtc.Model;

public class CarList {

    private String carName,mKey;

    public CarList() {
    }

    public CarList(String carName, String mKey) {
        this.carName = carName;
        this.mKey = mKey;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
