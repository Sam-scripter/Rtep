package com.example.rtep2.Departments;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rtep2.R;

import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.DepartmentViewHolder> {

    private Context context;
    private List<DepartmentData.DepartmentClass> departmentDataList;
    private OnItemClickListener listener;

    public DepartmentAdapter(Context context, List<DepartmentData.DepartmentClass> departmentDataList) {
        this.context = context;
        this.departmentDataList = departmentDataList;
    }

    public interface OnItemClickListener {
        void onItemClick(DepartmentData.DepartmentClass department);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public DepartmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_department_adapter, parent, false);
        return new DepartmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentViewHolder holder, int position) {
        DepartmentData.DepartmentClass department = departmentDataList.get(position);
        holder.bind(department);
    }

    @Override
    public int getItemCount() {
        return departmentDataList.size();
    }

    public class DepartmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView departmentNameTextView;
        private TextView departmentIdTextView;

        public DepartmentViewHolder(@NonNull View itemView) {
            super(itemView);
            departmentNameTextView = itemView.findViewById(R.id.department_name);
            departmentIdTextView = itemView.findViewById(R.id.department_id);
            itemView.setOnClickListener(this);
        }

        public void bind(DepartmentData.DepartmentClass department) {
            departmentNameTextView.setText(department.getDepartment_name());
            departmentIdTextView.setText(department.getDepartment_id());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                DepartmentData.DepartmentClass department = departmentDataList.get(position);
                if (listener != null) {
                    listener.onItemClick(department);
                }
            }
        }
    }
}
