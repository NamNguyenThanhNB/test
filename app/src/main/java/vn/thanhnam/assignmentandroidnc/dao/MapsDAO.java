package vn.thanhnam.assignmentandroidnc.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.thanhnam.assignmentandroidnc.database.StudyAidSQLite;
import vn.thanhnam.assignmentandroidnc.model.Course;
import vn.thanhnam.assignmentandroidnc.model.Maps;

public class MapsDAO {


    public static final String TABLE_NAME_MAPS = "Maps";
    private SQLiteDatabase dbW, dbR;
    private StudyAidSQLite dbHelper;
    public static final String TAG = "CourseDAO";


    public static final String SQL_MAPS = "" +
            "CREATE TABLE " + TABLE_NAME_MAPS + " (nameADR NVARCHAR primary key , " +
            "longtitude NVARCHAR, latitude NVARCHAR);";

    public MapsDAO(Context context) {
        dbHelper = new StudyAidSQLite(context);
        dbW = dbHelper.getWritableDatabase();
        dbR = dbHelper.getReadableDatabase();
    }


    public boolean insertMAPS(Maps maps) {
        ContentValues values = new ContentValues();
        values.put("nameADR", maps.getNameAdr());
        values.put("longtitude", maps.getLongtitude());
        values.put("latitude", maps.getLatitude());

        long result = dbW.insert(TABLE_NAME_MAPS, null, values);

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "INSERT MAPS" + e.toString());
            return false;
        }
        return true;
    }

    public boolean updateMAPS(Maps maps) {
        ContentValues values = new ContentValues();
        values.put("nameADR", maps.getNameAdr());
        values.put("longtitude", maps.getLongtitude());
        values.put("latitude", maps.getLatitude());

        long result = dbW.update(TABLE_NAME_MAPS, values, "nameADR" + " =?", new String[]{String.valueOf(maps.getNameAdr())});

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "UPDATE MAPS " + e.toString());
            return false;
        }
        return true;
    }

    public boolean deleteMAPS(String id) {

        long result = dbW.delete(TABLE_NAME_MAPS, "nameADR" + " =?", new String[]{id});

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "DELETE MAPS " + e.toString());
            return false;
        }
        return true;
    }

    public List<Maps> selectMAPS() {
        List<Maps> mapsList = new ArrayList<>();
        // b2 : viet cau lenh select

        String select = "SELECT * FROM " + TABLE_NAME_MAPS;

        // b3 : su dung cau lenh rawQuery
        Cursor cursor = dbR.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                Maps maps = new Maps();
                maps.setNameAdr(cursor.getString(0));
                maps.setLongtitude(cursor.getString(1));
                maps.setLatitude(cursor.getString(2));
                mapsList.add(maps);
            } while (cursor.moveToNext());

            // dong ket noi con tro
            cursor.close();
        }
        return mapsList;
    }

    public Maps selectMAPSByNameADR(String name) {
        // b2 : viet cau lenh select

        String select = "SELECT * FROM " + TABLE_NAME_MAPS + " WHERE nameADR = ?";

        Maps maps = new Maps();
        // b3 : su dung cau lenh rawQuery
        Cursor cursor = dbR.rawQuery(select, new String[]{name});
        cursor.moveToFirst();

        maps.setNameAdr(cursor.getString(0));
        maps.setLongtitude(cursor.getString(1));
        maps.setLatitude(cursor.getString(2));


        // dong ket noi con tro
        cursor.close();
        return maps;
    }

}
