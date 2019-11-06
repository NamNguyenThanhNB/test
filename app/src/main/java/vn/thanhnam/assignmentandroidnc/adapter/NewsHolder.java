package vn.thanhnam.assignmentandroidnc.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import vn.thanhnam.assignmentandroidnc.R;

class NewsHolder extends RecyclerView.ViewHolder {

    public TextView tvText1, tvText2, tvText3;
    public ImageView imgIcon;

    public NewsHolder(@NonNull View itemView) {
        super(itemView);
        imgIcon = itemView.findViewById(R.id.imgIcon);
        tvText1 = itemView.findViewById(R.id.tvText1);
        tvText2 = itemView.findViewById(R.id.tvText2);
        tvText3 = itemView.findViewById(R.id.tvText3);
    }
}
