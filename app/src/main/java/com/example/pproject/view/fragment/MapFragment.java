package com.example.pproject.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.pproject.R;
import com.example.pproject.view.MainActivity;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class MapFragment extends Fragment implements MapView.CurrentLocationEventListener {
    private static final String TAG = "Map_Fragment";
    private static int REQUEST_ACCESS_FINE_LOCATION = 1000;
    private MapView mapView;
    private double mCurrentLng; //Long = X, Lat = Yㅌ
    private double mCurrentLat;
    MapPoint currentMapPoint;
    boolean isTrackingMode = false;
    RelativeLayout mLoaderLayout;
    ImageButton btnCurrentLocation;
    private LocationManager lm;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mapView.setShowCurrentLocationMarker(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.map, container, false);
        permissionListener();
        mapView = new MapView(getActivity());

        ViewGroup mapViewContainer = rootView.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
        btnCurrentLocation = rootView.findViewById(R.id.btn_current_location);

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.157713, 129.059129), true);
        // 줌 레벨 변경
        mapView.setZoomLevel(3, true);
        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);

        MapPoint MARKER_POINT1 = MapPoint.mapPointWithGeoCoord(35.098615, 129.029486); // 코드네임블랙
        MapPoint MARKER_POINT2 = MapPoint.mapPointWithGeoCoord(35.155297, 129.059549); // 비밀의화원 서면점
        MapPoint MARKER_POINT3 = MapPoint.mapPointWithGeoCoord(35.099105, 129.029111); // 부산이스케이프남포이호점
        MapPoint MARKER_POINT4 = MapPoint.mapPointWithGeoCoord(35.154641, 129.061601); // 비트포비아 서면점
        MapPoint MARKER_POINT5 = MapPoint.mapPointWithGeoCoord(35.154157, 129.062139); // 다이아에그
        MapPoint MARKER_POINT6 = MapPoint.mapPointWithGeoCoord(35.154205, 129.061298); // 덤앤더머



        MapPOIItem marker1 = new MapPOIItem();
        marker1.setItemName("코드네임블랙");
        marker1.setTag(0);
        marker1.setMapPoint(MARKER_POINT1);
        marker1.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPOIItem marker2 = new MapPOIItem();
        marker2.setItemName("비밀의화원 서면점");
        marker2.setTag(0);
        marker2.setMapPoint(MARKER_POINT2);
        marker2.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPOIItem marker3 = new MapPOIItem();
        marker3.setItemName("부산이스케이프남포이호점");
        marker3.setTag(0);
        marker3.setMapPoint(MARKER_POINT3);
        marker3.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker3.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPOIItem marker4 = new MapPOIItem();
        marker4.setItemName("비트포비아 서면점");
        marker4.setTag(0);
        marker4.setMapPoint(MARKER_POINT4);
        marker4.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker4.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPOIItem marker5 = new MapPOIItem();
        marker5.setItemName("다이아에그");
        marker5.setTag(0);
        marker5.setMapPoint(MARKER_POINT5);
        marker5.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker5.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPOIItem marker6 = new MapPOIItem();
        marker6.setItemName("덤앤더머");
        marker6.setTag(0);
        marker6.setMapPoint(MARKER_POINT6);
        marker6.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker6.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker1);
        mapView.addPOIItem(marker2);
        mapView.addPOIItem(marker3);
        mapView.addPOIItem(marker4);
        mapView.addPOIItem(marker5);
        mapView.addPOIItem(marker6);

        setShowCurrentLocationMarker(true);

        btnCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading); // 현재 위치값 가져오기
               // setCurrentLocationTrackingMode(isTrackingMode);
            }
        });

            return rootView;
        }
    public void setShowCurrentLocationMarker(boolean show) { }
    public void setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode trackingMode){}
    public void setCustomCurrentLocationMarkerTrackingImage(int id, MapPOIItem.ImageOffset anchorPointOffset){}
    private void permissionListener() {
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(getActivity(), "권한 허가", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getActivity(), "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        TedPermission.with(getActivity())
                .setPermissionListener(permissionListener)
                .setRationaleMessage("현재위치를 가져오기 위해서는 권한이 필요합니다 동의하시겠습니까?")
                .setDeniedMessage("권한을 거부하셨습니다. [설정] > [권한]에서 권한을 허용할 수 있습니다.")
                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                .check();
    }


        @Override
        public void onCurrentLocationUpdate (MapView mapView, MapPoint mapPoint,float v){
            MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
            Log.i(TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, v));
            currentMapPoint = MapPoint.mapPointWithGeoCoord(mapPointGeo.latitude, mapPointGeo.longitude);
            //이 좌표로 지도 중심 이동
            mapView.setMapCenterPoint(currentMapPoint, true);
            //전역변수로 현재 좌표 저장
            mCurrentLat = mapPointGeo.latitude;
            mCurrentLng = mapPointGeo.longitude;
            Log.d(TAG, "현재위치 => " + mCurrentLat + "  " + mCurrentLng);
            mLoaderLayout.setVisibility(View.GONE);
            //트래킹 모드가 아닌 단순 현재위치 업데이트일 경우, 한번만 위치 업데이트하고 트래킹을 중단시키기 위한 로직
            if (!isTrackingMode) {
                mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
            }
        }


        @Override
        public void onCurrentLocationDeviceHeadingUpdate (MapView mapView,float v){

        }

        @Override
        public void onCurrentLocationUpdateFailed (MapView mapView){
            Log.i(TAG, "onCurrentLocationUpdateFailed");
            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        }

        @Override
        public void onCurrentLocationUpdateCancelled (MapView mapView){
            Log.i(TAG, "onCurrentLocationUpdateCancelled");
            mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        }
    }



