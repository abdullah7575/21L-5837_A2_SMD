package com.example.smd_assignment_2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TasksDashboard extends AppCompatActivity implements TaskAdapter.TaskSelected
{
    ListView lvTasks_list;
    ArrayList<Task> tasks;
    TextView tvName, tvDescription;

    //hooks for fragments
    TaskListFrag taskListFrag;
    TaskDetailFrag taskDetailFrag;
    AddTaskFrag addTaskFrag;
    FragmentManager fragmentManager;
    FloatingActionButton fab_add_task;

    // hooks for View of Fragments
    View viewTaskDetailFrag;
    View viewTaskListFrag;
    View viewAddTaskFrag;
    LinearLayout potrait;

    Button btnSave;
    ImageView ivDelIcon;
    ImageView ivDoneIcon;
    //TaskAdapptor hook
    TaskAdapter taskAdapter;
    ArrayList<Task> completedTasks;
    ArrayList<Task> deletedTasks;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save your tasks to the bundle
        outState.putSerializable("tasks", tasks);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if there is a saved instance state
        if (savedInstanceState != null) {
            // Restore tasks from the saved instance state
            tasks = (ArrayList<Task>) savedInstanceState.getSerializable("tasks");
        } else {
            // Initialize tasks as usual
            tasks = new ArrayList<>();
            tasks.add(new Task("Assignment 1 SMD", "Fragments Related Assignment due on 6th October"));
            tasks.add(new Task("Quiz 2 of SMD", "Fragments Related Quiz on 7th October"));
            tasks.add(new Task("FYP D2", "Do this earlier"));
            tasks.add(new Task("Proposal Submission", "Submit proposal of term project at earliest"));
        }
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tasks_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.potrait), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        completedTasks = new ArrayList<>();
        deletedTasks = new ArrayList<>();
        fab_add_task = findViewById(R.id.fab_add_task);
        fragmentManager = getSupportFragmentManager();
        taskListFrag = (TaskListFrag) fragmentManager.findFragmentById(R.id.task_list_frag);
        taskDetailFrag = (TaskDetailFrag) fragmentManager.findFragmentById(R.id.task_detail_frag);
        addTaskFrag = (AddTaskFrag) fragmentManager.findFragmentById(R.id.add_task_frag);
        if(taskDetailFrag!=null){
            viewTaskDetailFrag = taskDetailFrag.getView();
        }
        if(taskListFrag!=null){
            viewTaskListFrag = taskListFrag.getView();
        }
        if(addTaskFrag != null){
            viewAddTaskFrag = addTaskFrag.getView();
        }
        assert viewTaskDetailFrag != null;
        tvName = viewTaskDetailFrag.findViewById(R.id.tvName);
        tvDescription = viewTaskDetailFrag.findViewById(R.id.tvDescription);
        potrait = findViewById(R.id.potrait);

        if(potrait!=null){
            fragmentManager.beginTransaction()
                    .show(taskListFrag)
                    .hide(taskDetailFrag)
                    .hide(addTaskFrag)
                    .commit();
        }
        else{
            viewTaskDetailFrag.setVisibility(View.VISIBLE);
            fragmentManager.beginTransaction()
                    .hide(taskListFrag)
                    .hide(addTaskFrag)
                    .show(taskDetailFrag)
                    .commit();
        }

        tvName.setVisibility(View.GONE);
        tvDescription.setText("Select any task to show details");
        lvTasks_list = viewTaskListFrag.findViewById(R.id.lvTasks_list);
//        tasks = new ArrayList<>();
//        tasks.add(new Task("Assignment 1 SMD","Fragments Related Assignment due on 6th October"));
//        tasks.add(new Task("Quiz 2 of SMD","Fragments Related Quiz on 7th October"));
//        tasks.add(new Task("FYP D2","Do this earlier"));
//        tasks.add(new Task("Proposal Submission","Submit proposal of term project at earliest"));

        taskAdapter = new TaskAdapter(this,R.layout.single_task_design,tasks);
        lvTasks_list.setAdapter(taskAdapter);

        ivDoneIcon = viewTaskListFrag.findViewById(R.id.ivDoneIcon);
        if(ivDoneIcon !=null){
            Log.i("TAG", "This is an info message");
        }

        fab_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               fragmentManager.beginTransaction()
                       .hide(taskDetailFrag)
                       .hide(taskListFrag)
                       .show(addTaskFrag)
                       .addToBackStack(null)
                       .commit();
            }

        });
        btnSave = viewAddTaskFrag.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTaskAdded();
            }
        });

    }

    @Override
    public void onTaskClick(int position) {
        if(potrait!=null){
            fragmentManager.beginTransaction()
                    .hide(taskListFrag)
                    .show(taskDetailFrag)
                    .addToBackStack(null)
                    .commit();
        }
        tvName.setVisibility(View.VISIBLE);
        Task task = tasks.get(position);
        tvName.setText(task.getName());
        tvDescription.setText(task.getDescription());

    }

    @Override
    public void onDoneIconClick(Task t) {
        tasks.remove(t);
        taskAdapter.notifyDataSetChanged();
        completedTasks.add(t);
        Toast.makeText(this, "Task Completed Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteIconClick(Task t) {
        tasks.remove(t);
        taskAdapter.notifyDataSetChanged();
        deletedTasks.add(t);
        Toast.makeText(this, "Task Deleted Successfully", Toast.LENGTH_SHORT).show();
    }

    public void onTaskAdded() {
//        tasks.add(task);
//        taskAdapter.notifyDataSetChanged();
        EditText etName = viewAddTaskFrag.findViewById(R.id.etTaskName);
        EditText etDescription = viewAddTaskFrag.findViewById(R.id.etTaskDescription);
        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        if(!name.isEmpty() && !description.isEmpty()){
            etName.setText("");
            etDescription.setText("");
            Task newTask = new Task(name,description);
            tasks.add(newTask);
            taskAdapter.notifyDataSetChanged();
            if(potrait!=null){
                fragmentManager.beginTransaction()
                        .hide(addTaskFrag)
                        .hide(taskDetailFrag)
                        .show(taskListFrag)
                        .commit();
            }
            else{
                fragmentManager.beginTransaction()
                        .hide(taskListFrag)
                        .hide(addTaskFrag)
                        .show(taskDetailFrag)
                        .commit();
            }
        }
    }
}