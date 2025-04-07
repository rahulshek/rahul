package com.example.collegecompanion.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.collegecompanion.R;
import com.example.collegecompanion.data.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private List<Note> notes;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notes, container, false);

        // Initialize RecyclerView
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize notes list with dummy data
        notes = new ArrayList<>();
        notes.add(new Note("Mathematics Notes", "Algebra and Calculus basics", "Mathematics"));
        notes.add(new Note("Physics Notes", "Newton's Laws of Motion", "Physics"));
        notes.add(new Note("Computer Science Notes", "Data Structures and Algorithms", "Computer Science"));

        // Set up adapter
        adapter = new NotesAdapter(notes);
        recyclerView.setAdapter(adapter);

        // Set up FAB
        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            // TODO: Implement add note functionality
        });

        return root;
    }
} 