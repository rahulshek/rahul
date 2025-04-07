package com.example.collegecompanion.ui;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegecompanion.R;
import com.example.collegecompanion.data.TimetableEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TimetableFragment extends Fragment {
    private RecyclerView recyclerView;
    private TimetableAdapter adapter;
    private List<TimetableEntry> timetableEntries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize with dummy data
        timetableEntries = new ArrayList<>();
        timetableEntries.add(new TimetableEntry("Monday", "09:00", "Mathematics", "Room 101", "Dr. Smith"));
        timetableEntries.add(new TimetableEntry("Tuesday", "10:30", "Physics", "Room 203", "Prof. Johnson"));
        timetableEntries.add(new TimetableEntry("Wednesday", "14:00", "Chemistry", "Room 105", "Dr. Williams"));

        adapter = new TimetableAdapter(timetableEntries);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = view.findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> showAddTimetableDialog());

        return view;
    }

    private void showAddTimetableDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_timetable, null);
        
        Spinner spinnerDay = dialogView.findViewById(R.id.spinnerDay);
        EditText editTime = dialogView.findViewById(R.id.editTime);
        EditText editSubject = dialogView.findViewById(R.id.editSubject);
        EditText editClassroom = dialogView.findViewById(R.id.editClassroom);
        EditText editLecturer = dialogView.findViewById(R.id.editLecturer);

        // Set up day spinner
        ArrayAdapter<CharSequence> dayAdapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.days_of_week,
                android.R.layout.simple_spinner_item
        );
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(dayAdapter);

        // Set up time picker
        editTime.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    requireContext(),
                    (view, hourOfDay, minute1) -> {
                        String time = String.format("%02d:%02d", hourOfDay, minute1);
                        editTime.setText(time);
                    },
                    hour,
                    minute,
                    true
            );
            timePickerDialog.show();
        });

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(requireContext());
        builder.setView(dialogView)
                .setTitle("Add Timetable Entry")
                .setPositiveButton("Add", (dialog, which) -> {
                    String day = spinnerDay.getSelectedItem().toString();
                    String time = editTime.getText().toString();
                    String subject = editSubject.getText().toString();
                    String classroom = editClassroom.getText().toString();
                    String lecturer = editLecturer.getText().toString();

                    if (time.isEmpty() || subject.isEmpty() || classroom.isEmpty() || lecturer.isEmpty()) {
                        Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    TimetableEntry newEntry = new TimetableEntry(day, time, subject, classroom, lecturer);
                    timetableEntries.add(newEntry);
                    adapter.updateData(timetableEntries);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
} 