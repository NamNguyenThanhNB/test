package vn.thanhnam.assignmentandroidnc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vn.thanhnam.assignmentandroidnc.R;
import vn.thanhnam.assignmentandroidnc.activity.insert.AddCourseActivity;
import vn.thanhnam.assignmentandroidnc.adapter.CourseAdapter;
import vn.thanhnam.assignmentandroidnc.dao.CourseDAO;
import vn.thanhnam.assignmentandroidnc.model.Course;

public class CourseActivity extends AppCompatActivity {

    private List<Course> courseList;
    private RecyclerView rvListCourse;
    private CourseDAO courseDAO;
    private Toolbar tooCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
//        tooCourse = findViewById(R.id.tooCourse);
//        tooCourse.setTitle("");
//        setSupportActionBar(tooCourse);


        rvListCourse = findViewById(R.id.rvListCourse);
        courseDAO = new CourseDAO(CourseActivity.this);
        Course course = new Course("ADR_01", "Android cơ bản", "Andriod programming", "5/10/2019 - 5/1/2020");
        Course course1 = new Course("ADR_02", "Android nâng cao", "Andriod programming", "25/10/2019 - 19/2/2020");
        Course course2 = new Course("WEB_01", "HTML5 & CSS3 cơ bản", "Web design", "28/9/2019 - 28/12/2020");
        Course course3 = new Course("WEB_02", "HTML5 & CSS3 nâng cao", "Web design", "7/10/2019 - 7/12/2020");

        boolean result = courseDAO.insertCourse(CourseDAO.TABLE_NAME, course);
        boolean result1 = courseDAO.insertCourse(CourseDAO.TABLE_NAME, course1);
        boolean result2 = courseDAO.insertCourse(CourseDAO.TABLE_NAME, course2);
        boolean result3 = courseDAO.insertCourse(CourseDAO.TABLE_NAME, course3);
        courseList = courseDAO.selectCourse(CourseDAO.TABLE_NAME);

        CourseAdapter courseAdapter = new CourseAdapter(CourseActivity.this, courseList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListCourse.setLayoutManager(linearLayoutManager);
        rvListCourse.setAdapter(courseAdapter);

    }

    public void open(View view) {
        Intent intent = new Intent(CourseActivity.this, RegisteredActivity.class);
        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(CourseActivity.this, MainActivity.class);
        startActivity(intent);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_course, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_Registered:
//
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
