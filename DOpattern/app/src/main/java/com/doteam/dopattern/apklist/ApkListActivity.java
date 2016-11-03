// 출처 : http://itmir.tistory.com/382

package com.doteam.dopattern.apklist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.doteam.dopattern.MainActivity;
import com.doteam.dopattern.R;

import java.util.ArrayList;
import java.util.List;

public class ApkListActivity extends Activity
        implements OnItemClickListener {

        PackageManager packageManager;
        ListView apkList;

    public void saveStringPreferences(String str, String str2){
        SharedPreferences pref = getSharedPreferences(str,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(str,str2);
        editor.commit();
    }
    public String getStringPreferences(String str) {
        SharedPreferences pref = getSharedPreferences(str,0);
        String tempget = pref.getString(str,null);
        return tempget;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applist);

        packageManager = getPackageManager();
        List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);

        List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();

        /*To filter out System apps*/
        for(PackageInfo pi : packageList) {
            boolean b = isSystemPackage(pi);
            if(!b) {
                packageList1.add(pi);
            }
        }
        apkList = (ListView) findViewById(R.id.applist);
        apkList.setAdapter(new ApkAdapter(this, packageList1, packageManager));

        apkList.setOnItemClickListener(this);
    }

    /**
     * Return whether the given PackgeInfo represents a system package or not.
     * User-installed packages (Market or otherwise) should not be denoted as
     * system packages.
     *
     * @param pkgInfo
     * @return boolean
     */
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }
    static int i=0;
    static String targetapp[] ={null,null} ;
    static String packname[] = {null,null} ;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long row) {
        PackageInfo packageInfo = (PackageInfo) parent.getItemAtPosition(position);
        //targetapp[i]가 처음에 null이라서 적어도 한 번은 실행시키기 위해 do while 사용

            //저장모드
           if(MainActivity.Delete_mode==false) {

               targetapp[0] = getStringPreferences("APP" + 0);
                if (MainActivity.Isupbutton==true) {

                    targetapp[0] = "APP" + 0;
                    packname[0] = packageInfo.packageName;

                    saveStringPreferences(targetapp[0], packname[0]);
                }
                else if(MainActivity.Isupbutton==false){
                    targetapp[1] = "APP" + 1;
                    packname[1] = packageInfo.packageName;

                    saveStringPreferences(targetapp[1], packname[1]);
            }
            //삭제모드
           else if(MainActivity.Delete_mode==true){
                if(MainActivity.Isupbutton)

                        saveStringPreferences("APP" + 0, null);
                        saveStringPreferences("pattern" + 0, null);
                        Toast.makeText(getApplicationContext(), packname[0] + "앱과 볼륨up연동이 해제되었습니다.", Toast.LENGTH_SHORT).show();


                }
                else if(!MainActivity.Isupbutton){
                    saveStringPreferences("APP" + 0, null);
                    saveStringPreferences("pattern" + 0, null);
                    Toast.makeText(getApplicationContext(), packname[0] + "앱과 볼륨up연동이 해제되었습니다.", Toast.LENGTH_SHORT).show();
                }
           }
        finish();




    }
}