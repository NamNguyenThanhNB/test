package vn.thanhnam.assignmentandroidnc.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.thanhnam.assignmentandroidnc.R;

class CourseHolder extends RecyclerView.ViewHolder {

    public TextView tvText1, tvText2;
    public ImageView imgClick;
    public LinearLayout lvInfo;

    public CourseHolder(@NonNull View itemView) {
        super(itemView);

        tvText1 = itemView.findViewById(R.id.tvText1);
        tvText2 = itemView.findViewById(R.id.tvText2);
        imgClick = itemView.findViewById(R.id.imgClick);
        lvInfo = itemView.findViewById(R.id.lvInfo);
    }
}
