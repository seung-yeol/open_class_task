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
    static String targetapp[] ={null,null,null,null,null,null,null,null,null,null} ;
    static String packname[] = {null,null,null,null,null,null,null,null,null,null} ;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long row) {
        PackageInfo packageInfo = (PackageInfo) parent.getItemAtPosition(position);
        //targetapp[i]가 처음에 null이라서 적어도 한 번은 실행시키기 위해 do while 사용
        do {
            //저장모드
            if(GlobalVariable.Delete_mode==false) {

                targetapp[i] = getStringPreferences("APP" + i);
                if (i >= 10) {
                    Toast.makeText(getApplicationContext(), "10개 이상을 등록하실 수 없습니다 ㅠㅠ", Toast.LENGTH_LONG).show();
                    finish();
                    break;

                } else if (targetapp[i] == null) {

                    targetapp[i] = "APP" + i;
                    packname[i] = packageInfo.packageName;

                    saveStringPreferences(targetapp[i], packname[i]);

                    GlobalVariable.list = i;

                    Toast.makeText(getApplicationContext(),packname[i]+ "앱을 저장할 패턴을 입력하세요", Toast.LENGTH_SHORT).show();
                    i++;

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);
                    break;
                }
            }
            //삭제모드
            else {
                GlobalVariable.Delete_mode = false;

                String imsiapp = packageInfo.packageName;
                for(int i=0;i<10;i++) {

                    packname[i] = getStringPreferences("APP" + i);
                    //null객체 때문에 계속 오류나서 null아닐때만 비교하는걸로
                    if(packname[i]!=null) {
                        if (packname[i].equals(imsiapp)) {
                            saveStringPreferences("APP" + i, null);
                            saveStringPreferences("pattern" + i, null);
                            Toast.makeText(getApplicationContext(), packname[i] + "앱과 패턴이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }while(targetapp[i] !=null);
        finish();




    }
}