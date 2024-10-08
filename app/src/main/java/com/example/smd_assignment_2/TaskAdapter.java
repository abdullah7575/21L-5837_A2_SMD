package com.example.smd_assignment_2;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    Context context;
    int resource;

    public interface TaskSelected {
        void onTaskClick(int position);
        void onDoneIconClick(Task t);
        void onDeleteIconClick(Task t);
    }

    TaskSelected taskSelected;

    public TaskAdapter(Context context, int resource, List<Task> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        taskSelected = (TaskSelected) context;

    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            // attach view
            // parent.getContext() also by sir in from()?
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        }

        // set data to this view
        Task task = getItem(position);
        TextView tvTaskName = convertView.findViewById(R.id.tvTaskName);
        if (task != null) {
            tvTaskName.setText(task.getName());
        }
        tvTaskName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (taskSelected != null) {
                    taskSelected.onTaskClick(position);
                }

            }
        });
        ImageView ivDoneIcon = convertView.findViewById(R.id.ivDoneIcon);
        ivDoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskSelected.onDoneIconClick(task);
            }
        });

        ImageView ivDelIcon = convertView.findViewById(R.id.ivDelIcon);
        ivDelIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskSelected.onDeleteIconClick(task);
            }
        });
        return convertView;

    }
}
