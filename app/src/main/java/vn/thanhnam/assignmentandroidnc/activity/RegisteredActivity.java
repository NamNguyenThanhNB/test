package vn.thanhnam.assignmentandroidnc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import vn.thanhnam.assignmentandroidnc.R;
import vn.thanhnam.assignmentandroidnc.adapter.CourseAdapter;
import vn.thanhnam.assignmentandroidnc.adapter.RegisterdAdapter;
import vn.thanhnam.assignmentandroidnc.dao.CourseDAO;
import vn.thanhnam.assignmentandroidnc.model.Course;

public class RegisteredActivity extends AppCompatActivity {
    private List<Course> courseList;
    private RecyclerView rvListRegistered;
    private CourseDAO courseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        rvListRegistered = findViewById(R.id.rvListRegistered);
        courseDAO = new CourseDAO(RegisteredActivity.this);
        courseList = courseDAO.selectCourse(CourseDAO.TABLE_NAME_NEW);
        if (courseList == null) {
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        } else {

            RegisterdAdapter registerdAdapter = new RegisterdAdapter(RegisteredActivity.this, courseList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            rvListRegistered.setLayoutManager(linearLayoutManager);
            rvListRegistered.setAdapter(registerdAdapter);
        }
    }

    public void back(View view) {
        Intent intent = new Intent(RegisteredActivity.this, CourseActivity.class);
        startActivity(intent);
    }
}
