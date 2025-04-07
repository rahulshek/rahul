package com.example.collegecompanion.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.collegecompanion.R;
import com.example.collegecompanion.data.Assignment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AssignmentsFragment extends Fragment {
    private RecyclerView recyclerView;
    private AssignmentsAdapter adapter;
    private List<Assignment> assignments;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_assignments, container, false);

        // Initialize RecyclerView
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize assignments list with dummy data
        assignments = new ArrayList<>();
        assignments.add(new Assignment("Math Homework", "Complete exercises 1-10", "2024-04-15", "Mathematics"));
        assignments.add(new Assignment("Physics Lab", "Submit lab report", "2024-04-20", "Physics"));
        assignments.add(new Assignment("Programming Project", "Complete the final project", "2024-04-25", "Computer Science"));

        // Set up adapter
        adapter = new AssignmentsAdapter(assignments);
        recyclerView.setAdapter(adapter);

        // Set up FAB
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(v -> showAddAssignmentDialog());

        return root;
    }

    private void showAddAssignmentDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_assignment, null);
        EditText editTitle = dialogView.findViewById(R.id.editTitle);
        EditText editSubject = dialogView.findViewById(R.id.editSubject);
        EditText editDescription = dialogView.findViewById(R.id.editDescription);
        EditText editDueDate = dialogView.findViewById(R.id.editDueDate);

        // Set up date picker for due date
        editDueDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(requireContext(), (view, year, month, dayOfMonth) -> {
                String date = String.format("%d-%02d-%02d", year, month + 1, dayOfMonth);
                editDueDate.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Add New Assignment")
                .setView(dialogView)
                .setPositiveButton("Add", (dialog, which) -> {
                    String title = editTitle.getText().toString();
                    String subject = editSubject.getText().toString();
                    String description = editDescription.getText().toString();
                    String dueDate = editDueDate.getText().toString();

                    if (!title.isEmpty() && !subject.isEmpty() && !description.isEmpty() && !dueDate.isEmpty()) {
                        Assignment newAssignment = new Assignment(title, description, dueDate, subject);
                        assignments.add(newAssignment);
                        adapter.updateData(assignments);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
} 