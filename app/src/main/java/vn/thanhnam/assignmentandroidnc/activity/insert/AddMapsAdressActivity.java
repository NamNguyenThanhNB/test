package vn.thanhnam.assignmentandroidnc.activity.insert;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vn.thanhnam.assignmentandroidnc.R;
import vn.thanhnam.assignmentandroidnc.dao.MapsDAO;
import vn.thanhnam.assignmentandroidnc.model.Maps;

public class AddMapsAdressActivity extends AppCompatActivity {
    private MapsDAO mapsDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_maps_adress);




    }
}
