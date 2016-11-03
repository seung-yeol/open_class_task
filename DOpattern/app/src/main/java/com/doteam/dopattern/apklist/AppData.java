//출처 : http://itmir.tistory.com/382

package com.doteam.dopattern.apklist;
 
import android.app.Application;
import android.content.pm.PackageInfo;

public class AppData extends Application {
 
    PackageInfo packageInfo;
 
    public PackageInfo getPackageInfo() {
        return packageInfo;
    }
 
    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }
}