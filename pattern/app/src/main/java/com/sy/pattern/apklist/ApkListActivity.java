// 출처 : http://itmir.tistory.com/382

package com.sy.pattern.apklist;

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

import com.sy.pattern.GlobalVariable;
import com.sy.pattern.MainActivity;
import com.sy.pattern.R;

import java.util.ArrayList;
import java.util.List;

public class ApkListActivity extends Activity
        implements OnItemClickListener {

        PackageManager packageManager;
        ListView apkList;

    public void saveStringPreferences(String str,String str2){
        SharedPreferences pref = getSharedPreferences(str,0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(str,str2);
        editor.commit();
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
    static int i;
    static String targetapp[] ={null,null,null,null,null,null,null,null,null,null} ;
    static String packname[] = {null,null,null,null,null,null,null,null,null,null} ;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long row) {
        Toast.makeText(getApplicationContext(), "머지??"+i, Toast.LENGTH_LONG).show();
        PackageInfo packageInfo = (PackageInfo) parent
                .getItemAtPosition(position);


        do {
            //여기서 패키지이름을 프리퍼런스에다가 저장해야함.

            if (i >= 10) {
                Toast.makeText(getApplicationContext(), "10개 이상을 등록하실 수 없습니다 ㅠㅠ", Toast.LENGTH_LONG).show();
                finish();
                break;

            } else if (targetapp[i] == null) {

                Toast.makeText(getApplicationContext(), "저장됨" + i, Toast.LENGTH_LONG).show();
                targetapp[i] = "APP"+i;
                packname[i] = packageInfo.packageName;

                saveStringPreferences(targetapp[i], packname[i]);

                GlobalVariable.list = i;
                i++;

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                break;
            }
        }while(targetapp[i] !=null);
        finish();




    }
}