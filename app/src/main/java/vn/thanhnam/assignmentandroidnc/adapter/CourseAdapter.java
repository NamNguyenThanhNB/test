package vn.thanhnam.assignmentandroidnc.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.thanhnam.assignmentandroidnc.R;
import vn.thanhnam.assignmentandroidnc.activity.insert.AddCourseActivity;
import vn.thanhnam.assignmentandroidnc.dao.CourseDAO;
import vn.thanhnam.assignmentandroidnc.model.Course;

public class CourseAdapter extends RecyclerView.Adapter<CourseHolder> {

    private Context context;
    private List<Course> courseList;
    private CourseDAO courseDAO;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
        courseDAO = new CourseDAO(context);
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_, parent, false);
        CourseHolder courseHolder = new CourseHolder(view);
        return courseHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, final int position) {
        holder.tvText1.setText(courseList.get(position).getIdCourse() + "");
        holder.tvText2.setText(courseList.get(position).getNameCourse());
        holder.imgClick.setImageResource(R.drawable.add);
        holder.lvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setView(R.layout.adapter_info_course);
                final AlertDialog dialog = alertDialog.show();

                EditText edtIdCourse = dialog.findViewById(R.id.edtIdCourse);
                EditText edtTypeCourse = dialog.findViewById(R.id.edtTypeCourse);
                EditText edtNameCourse = dialog.findViewById(R.id.edtNameCourse);
                EditText edtTimeCourse = dialog.findViewById(R.id.edtTimeCourse);

                edtIdCourse.setText(courseList.get(position).getIdCourse());
                edtTypeCourse.setText(courseList.get(position).getTypeCourse());
                edtNameCourse.setText(courseList.get(position).getNameCourse());
                edtTimeCourse.setText(courseList.get(position).getTimeCourse());

                ImageView imgBack_info = dialog.findViewById(R.id.imgBack_info);
                ImageView imgAdd_info = dialog.findViewById(R.id.imgAdd_info);

                imgBack_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                imgAdd_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Course course = new Course();
                        course.setIdCourse(courseList.get(position).getIdCourse());
                        course.setNameCourse(courseList.get(position).getNameCourse());
                        course.setTypeCourse(courseList.get(position).getTypeCourse());
                        course.setTimeCourse(courseList.get(position).getTimeCourse());

                        boolean result = courseDAO.insertReg(CourseDAO.TABLE_NAME_NEW, course);
                        if (result) {
                            Toast.makeText(context, "Bạn đã đăng kí khóa học thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, RegisterdAdapter.class);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, "Bạn đã đăng ký khóa học này rồi. Xin chọn khóa học khác", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        holder.imgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course = new Course();
                course.setIdCourse(courseList.get(position).getIdCourse());
                course.setNameCourse(courseList.get(position).getNameCourse());
                course.setTypeCourse(courseList.get(position).getTypeCourse());
                course.setTimeCourse(courseList.get(position).getTimeCourse());

                boolean result = courseDAO.insertReg(CourseDAO.TABLE_NAME_NEW, course);
                if (result) {
                    Toast.makeText(context, "Bạn đã đăng kí khóa học thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Bạn đã đăng ký khóa học này rồi. Xin chọn khóa học khác", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }


    private void showInfo() {

    }
}
