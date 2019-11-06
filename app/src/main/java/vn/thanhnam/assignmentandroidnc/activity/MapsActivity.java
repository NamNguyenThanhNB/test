package vn.thanhnam.assignmentandroidnc.activity;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import vn.thanhnam.assignmentandroidnc.R;
import vn.thanhnam.assignmentandroidnc.dao.MapsDAO;
import vn.thanhnam.assignmentandroidnc.model.Maps;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AutoCompleteTextView autoAddress;
    private ImageView imgSearch;
    private MapsDAO mapsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mapsDAO = new MapsDAO(this);

        autoAddress = findViewById(R.id.autoAddress);
        imgSearch = findViewById(R.id.imgSearch);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        adrMap();
        List<Maps> mapsList = mapsDAO.selectMAPS();
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Kiểm tra quyền hạn
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        }
        LatLng mapsLng = new LatLng(Double.parseDouble(mapsList.get(2).getLatitude()), Double.parseDouble(mapsList.get(2).getLongtitude()));
        mMap.addMarker(new MarkerOptions().position(mapsLng).title(mapsList.get(2).getNameAdr() + ""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mapsLng));
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        try {
            String autoMaps[] = new String[mapsList.size()];
            for (int i = 0; i < mapsList.size(); i++) {
                autoMaps[i] = mapsList.get(i).getNameAdr();
            }

            ArrayAdapter<String> sachAdapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, autoMaps);
            autoAddress.setAdapter(sachAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = autoAddress.getText().toString().trim();
                Maps maps = mapsDAO.selectMAPSByNameADR(name);
                try {
                    double latitude = Double.parseDouble(maps.getLatitude());
                    double longtitude = Double.parseDouble(maps.getLongtitude());


                    LatLng mapsLng = new LatLng(latitude, longtitude);
                    mMap.addMarker(new MarkerOptions().position(mapsLng).title(name + ""));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(mapsLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("e loi du lieu", e + "");
                }

            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapsActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
    }

    private void adrMap() {
        Maps hotay = new Maps("Hồ Tây", "105.8062693", "21.0580711");
        Maps hohoankiem = new Maps("Hồ Hoàn Kiếm", "105.8501706", "21.0287797");
        Maps cs1_CDFPT = new Maps("Trường Cao đẳng thực hành FPT Polytechnic, CS1", "105.7630956", "21.0355605");
        Maps cs2_CDFPT = new Maps("Trường Cao đẳng thực hành FPT Polytechnic, CS2", "105.8017078", "21.0395869");
        Maps langbac = new Maps("Lăng chủ tịch Hồ Chí Minh", "105.8324506", "21.0367839");
        Maps timecity = new Maps("Khu đô thị Times City, Vĩnh Tuy, Hai Bà Trưng, Hà Nội", "105.8635068", "20.9943877");
        Maps rouyalcity = new Maps("Khu đô thị Royal City, Thượng Đình, Thanh Xuân, Hà Nội", "105.8130437", "21.002476");
        Maps svdMyDinh = new Maps("Sân vận động Quốc gia Mỹ Đình, Đường Lê Đức Thọ, Mỹ Đình 1, Nam Từ Liêm, Hà Nội", "105.7617706", "21.0204522");
        Maps sbNoiBai = new Maps("Sân bay Nội Bài, Sóc Sơn, Hà Nội", "105.8019768", "21.2187199");
        Maps bxMyDinh = new Maps("Bến xe Mỹ Đình, Mỹ Đình 2, Nam Từ Liêm, Hà Nội", "105.7760746", "21.0284347");
        Maps bxGiapBat = new Maps("Bến xe Giáp Bát, Giải Phóng, Hoàng Mai, Hà Nội", "105.8392603", "20.9802198");


        boolean result1 = mapsDAO.insertMAPS(hotay);
        boolean result2 = mapsDAO.insertMAPS(hohoankiem);
        boolean result3 = mapsDAO.insertMAPS(cs1_CDFPT);
        boolean result4 = mapsDAO.insertMAPS(cs2_CDFPT);
        boolean result5 = mapsDAO.insertMAPS(langbac);
        boolean result6 = mapsDAO.insertMAPS(timecity);
        boolean result7 = mapsDAO.insertMAPS(rouyalcity);
        boolean result8 = mapsDAO.insertMAPS(svdMyDinh);
        boolean result9 = mapsDAO.insertMAPS(sbNoiBai);
        boolean result10 = mapsDAO.insertMAPS(bxMyDinh);
        boolean result11 = mapsDAO.insertMAPS(bxGiapBat);
    }

}
