package com.example.smd_assignment_2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddTaskFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddTaskFrag extends Fragment {
//    EditText etTaskName;
//    EditText etTaskDescription;
//    Button btnSave;

//    public interface OnTaskAddedListener{
//        void onTaskAdded(Task task);
//    }
//    private OnTaskAddedListener listener;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddTaskFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddTaskFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static AddTaskFrag newInstance(String param1, String param2) {
        AddTaskFrag fragment = new AddTaskFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof OnTaskAddedListener) {
//            listener = (OnTaskAddedListener) context;
//            System.out.println("Attached add task frag");
//        } else {
//            throw new RuntimeException(context.toString() + " must implement OnTaskAddedListener");
//        }
//    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        return inflater.inflate(R.layout.fragment_add_task, container, false);
//        etTaskName = view.findViewById(R.id.etTaskName);
//        etTaskDescription = view.findViewById(R.id.etTaskDescription);
//        btnSave = view.findViewById(R.id.btnSave);
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = etTaskName.getText().toString().trim();
//                String description = etTaskDescription.getText().toString().trim();
//
//                if(!name.isEmpty() && !description.isEmpty()){
//                    Task newTask = new Task(name,description);
//                    listener.onTaskAdded(newTask);
//                    //closing fragment after getting input
//                    getActivity().getSupportFragmentManager().popBackStack();
//                }
//                else {
//                    Toast.makeText(getContext(), "Please enter both name and description for task", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        return view;
    }
}