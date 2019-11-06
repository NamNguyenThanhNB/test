package vn.thanhnam.assignmentandroidnc.activity.insert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import vn.thanhnam.assignmentandroidnc.R;
import vn.thanhnam.assignmentandroidnc.activity.CourseActivity;
import vn.thanhnam.assignmentandroidnc.activity.MainActivity;
import vn.thanhnam.assignmentandroidnc.dao.CourseDAO;
import vn.thanhnam.assignmentandroidnc.model.Course;

public class AddCourseActivity extends AppCompatActivity {

    private EditText edtIdCourse_add, edtNameCourse_add, edtTypeCourse_add, edtTimeCourse_add;
    private ImageView btnBackCourse, btnAddCourse, btnCancelCourse;
    private CourseDAO courseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        courseDAO = new CourseDAO(AddCourseActivity.this);

        init();
        btnBackCourse();
        btnAddCourse();
        btnCancelCourse();
    }

    private void init() {

        edtIdCourse_add = findViewById(R.id.edtIdCourse_add);
        edtNameCourse_add = findViewById(R.id.edtNameCourse_add);
        edtTypeCourse_add = findViewById(R.id.edtTypeCourse_add);
        edtTimeCourse_add = findViewById(R.id.edtTimeCourse_add);


        btnBackCourse = findViewById(R.id.btnBackCourse);
        btnAddCourse = findViewById(R.id.btnAddCourse);
        btnCancelCourse = findViewById(R.id.btnCancelCourse);
    }

    private void btnBackCourse() {
        btnBackCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddCourseActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void btnAddCourse() {
        btnAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtIdCourse_add.getText().toString().trim();
                String name = edtNameCourse_add.getText().toString().trim();
                String type = edtTypeCourse_add.getText().toString().trim();
                String time = edtTimeCourse_add.getText().toString().trim();

                if (id.equals("")) {
                    edtIdCourse_add.setError("ID null");
                    edtIdCourse_add.requestFocus();
                    return;
                } else if (name.equals("")) {
                    edtNameCourse_add.setError("Name null");
                    edtNameCourse_add.requestFocus();
                    return;
                } else if (type.equals("")) {
                    edtTypeCourse_add.setError("Type null");
                    edtTypeCourse_add.requestFocus();
                    return;
                } else if (time.equals("")) {
                    edtTimeCourse_add.setError("Time null");
                    edtTimeCourse_add.requestFocus();
                    return;
                } else {
                    Course course = new Course(id, name, type, time);
                    boolean result = courseDAO.insertCourse(CourseDAO.TABLE_NAME,course);
                    if (result) {
                        Toast.makeText(AddCourseActivity.this, "true", Toast.LENGTH_SHORT).show();

                        edtIdCourse_add.setText("");
                        edtNameCourse_add.setText("");
                        edtTypeCourse_add.setText("");
                        edtTimeCourse_add.setText("");
                    } else {
                        Toast.makeText(AddCourseActivity.this, "false", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void btnCancelCourse() {
        btnCancelCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNameCourse_add.setText("");
                edtTypeCourse_add.setText("");
                edtTimeCourse_add.setText("");
            }
        });
    }
}
