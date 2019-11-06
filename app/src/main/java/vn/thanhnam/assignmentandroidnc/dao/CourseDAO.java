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

public class CourseDAO {


    public static final String TABLE_NAME = "Course";
    public static final String TABLE_NAME_NEW = "CourseNew";
    private SQLiteDatabase dbW, dbR;
    private StudyAidSQLite dbHelper;
    public static final String TAG = "CourseDAO";


    public static final String SQL_COURSE = "" +
            "CREATE TABLE " + TABLE_NAME + " (id NVARCHAR primary key , " +
            "name NVARCHAR, type NVARCHAR, time NVARCHAR);";
    public static final String SQL_COURSE_NEW = "" +
            "CREATE TABLE " + TABLE_NAME_NEW + " (idReg NVARCHAR primary key , " +
            "nameReg NVARCHAR, typeReg NVARCHAR, timeReg NVARCHAR);";

    public CourseDAO(Context context) {
        dbHelper = new StudyAidSQLite(context);
        dbW = dbHelper.getWritableDatabase();
        dbR = dbHelper.getReadableDatabase();
    }


    public boolean insertCourse(String tablename, Course course) {
        ContentValues values = new ContentValues();
        values.put("id", course.getIdCourse());
        values.put("name", course.getNameCourse());
        values.put("type", course.getTypeCourse());
        values.put("time", course.getTimeCourse());

        long result = dbW.insert(tablename, null, values);

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "INSERT: " + e.toString());
            return false;
        }
        return true;
    }

    public boolean updateCourse(String tablename, Course course) {
        ContentValues values = new ContentValues();
        values.put("id", course.getIdCourse());
        values.put("name", course.getNameCourse());
        values.put("type", course.getTypeCourse());
        values.put("time", course.getTimeCourse());

        long result = dbW.update(tablename, values, "id" + " =?", new String[]{String.valueOf(course.getIdCourse())});

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "UPDATE: " + e.toString());
            return false;
        }
        return true;
    }

    public boolean deleteCourse(String tablename, String id) {

        long result = dbW.delete(tablename, "id" + " =?", new String[]{id});

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "DELETE: " + e.toString());
            return false;
        }
        return true;
    }

    public List<Course> selectCourse(String tablename) {
        List<Course> courseList = new ArrayList<>();
        // b2 : viet cau lenh select

        String select = "SELECT * FROM " + tablename;

        // b3 : su dung cau lenh rawQuery
        Cursor cursor = dbR.rawQuery(select, null);
        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setIdCourse(cursor.getString(0));
                course.setNameCourse(cursor.getString(1));
                course.setTypeCourse(cursor.getString(2));
                course.setTimeCourse(cursor.getString(3));

                courseList.add(course);
            } while (cursor.moveToNext());

            // dong ket noi con tro
            cursor.close();
        }
        return courseList;
    }

//    public Course selectById(String id) {
//
//        Course course = new Course();
//
//        String select = "SELECT * FROM " + TABLE_NAME + " WHERE  id =?";
//        // b3 : su dung cau lenh rawQuery
//        Cursor cursor = dbR.rawQuery(select, new String[]{id});
//        if (cursor.moveToFirst()) {
//            course.setIdCourse(cursor.getString(0));
//            course.setNameCourse(cursor.getString(1));
//            course.setTypeCourse(cursor.getString(2));
//            course.setTimeCourse(cursor.getString(3));
//            // dong ket noi con tro
//            cursor.close();
//        }
//
//        return course;
//    }




    public boolean insertReg(String tablename, Course course) {
        ContentValues values = new ContentValues();
        values.put("idReg", course.getIdCourse());
        values.put("nameReg", course.getNameCourse());
        values.put("typeReg", course.getTypeCourse());
        values.put("timeReg", course.getTimeCourse());

        long result = dbW.insert(tablename, null, values);

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "INSERTReg: " + e.toString());
            return false;
        }
        return true;
    }


    public boolean deleteReg(String tablename, String id) {

        long result = dbW.delete(tablename, "idReg" + " =?", new String[]{id});

        try {
            if (result < 0) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "DELETE: " + e.toString());
            return false;
        }
        return true;
    }
}
