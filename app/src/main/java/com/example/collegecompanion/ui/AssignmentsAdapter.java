package com.example.collegecompanion.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.collegecompanion.R;
import com.example.collegecompanion.data.Assignment;
import java.util.List;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.AssignmentViewHolder> {
    private List<Assignment> assignments;

    public AssignmentsAdapter(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public AssignmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_assignment, parent, false);
        return new AssignmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssignmentViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        holder.titleTextView.setText(assignment.getTitle());
        holder.descriptionTextView.setText(assignment.getDescription());
        holder.dueDateTextView.setText(assignment.getDueDate());
        holder.subjectTextView.setText(assignment.getSubject());
        holder.completedCheckBox.setChecked(assignment.isCompleted());

        holder.completedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            assignment.setCompleted(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public void updateData(List<Assignment> newAssignments) {
        this.assignments = newAssignments;
        notifyDataSetChanged();
    }

    static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView dueDateTextView;
        TextView subjectTextView;
        CheckBox completedCheckBox;

        AssignmentViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textTitle);
            descriptionTextView = itemView.findViewById(R.id.textDescription);
            dueDateTextView = itemView.findViewById(R.id.textDueDate);
            subjectTextView = itemView.findViewById(R.id.textSubject);
            completedCheckBox = itemView.findViewById(R.id.checkboxCompleted);
        }
    }
} 