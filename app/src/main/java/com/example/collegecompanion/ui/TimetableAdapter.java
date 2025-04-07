package com.example.collegecompanion.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.collegecompanion.R;
import com.example.collegecompanion.data.TimetableEntry;
import java.util.List;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {
    private List<TimetableEntry> timetableEntries;

    public TimetableAdapter(List<TimetableEntry> timetableEntries) {
        this.timetableEntries = timetableEntries;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_timetable, parent, false);
        return new TimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        TimetableEntry entry = timetableEntries.get(position);
        holder.dayTextView.setText(entry.getDay());
        holder.timeTextView.setText(entry.getTime());
        holder.subjectTextView.setText(entry.getSubject());
        holder.classroomTextView.setText(entry.getClassroom());
        holder.lecturerTextView.setText(entry.getLecturer());
    }

    @Override
    public int getItemCount() {
        return timetableEntries.size();
    }

    public void updateData(List<TimetableEntry> newEntries) {
        this.timetableEntries = newEntries;
        notifyDataSetChanged();
    }

    static class TimetableViewHolder extends RecyclerView.ViewHolder {
        TextView dayTextView;
        TextView timeTextView;
        TextView subjectTextView;
        TextView classroomTextView;
        TextView lecturerTextView;

        TimetableViewHolder(View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.text_day);
            timeTextView = itemView.findViewById(R.id.text_time);
            subjectTextView = itemView.findViewById(R.id.text_subject);
            classroomTextView = itemView.findViewById(R.id.text_classroom);
            lecturerTextView = itemView.findViewById(R.id.text_lecturer);
        }
    }
} 