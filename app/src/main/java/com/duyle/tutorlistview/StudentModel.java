package com.duyle.tutorlistview;

import java.io.Serializable;

public class StudentModel implements Serializable {

    private String sName;
    private String mssv;
    private int iconId;

    public StudentModel(String sName, String mssv, int iconId) {
        this.sName = sName;
        this.mssv = mssv;
        this.iconId = iconId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
