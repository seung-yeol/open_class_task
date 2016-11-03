package com.doteam.map;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;

        import com.google.android.gms.maps.GoogleMap;

        import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

        import com.google.android.gms.maps.SupportMapFragment;

        import com.google.android.gms.maps.model.LatLng;

        import com.google.android.gms.maps.model.Marker;

        import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends FragmentActivity {


    GoogleMap mGoogleMap; // 구글 맵 객체


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        // 1. SDK manager 에서 Google play services 설치

        // 2. import google-play-services_lib

        // 3. 프로젝트에 library 등록 : 메뉴 properites Android-library

        // 4. 구글 API Key 발급

        // 5. Manifest.xml 설정


        // 맵 객체 생성

        mGoogleMap = ((SupportMapFragment) getSupportFragmentManager()

                .findFragmentById(R.id.fragment)).getMap();


        // 맵 위치를 이동하기

        CameraUpdate update = CameraUpdateFactory.newLatLng(

                new LatLng(37.478911, 127.012339));

        // 위도,경도

        mGoogleMap.moveCamera(update);


        // 보기 좋게 확대 zoom 하기

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        mGoogleMap.animateCamera(zoom);


        // 마커 표시하기 : 위치지정, 풍선말 설정

        MarkerOptions markerOptions = new MarkerOptions()

                .position(new LatLng(37.479097, 127.011784))

                .title("Great DavidJ!")

                .snippet("Really Great!");


        // 마커를 추가하고 말풍선 표시한 것을 보여주도록 호출

        mGoogleMap.addMarker(markerOptions).showInfoWindow();


        // 마커 클릭했을 떄 처리 : 리스너 달기

        mGoogleMap.setOnMarkerClickListener(new OnMarkerClickListener() {


            @Override

            public boolean onMarkerClick(Marker marker) {

                // TODO Auto-generated method stub

                Toast.makeText(MainActivity.this, "Oh my god!!", Toast.LENGTH_SHORT).show();

                return false;

            }

        });

    } // end onCreate()

} // end class

[출처]Android-Google Map V2 좌표 얻기,마커 찍기,마커 이벤트|작성자 장스

