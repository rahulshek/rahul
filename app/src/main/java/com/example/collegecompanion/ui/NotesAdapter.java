package com.example.collegecompanion.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.collegecompanion.R;
import com.example.collegecompanion.data.Note;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> notes;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.descriptionTextView.setText(note.getDescription());
        holder.subjectTextView.setText(note.getSubject());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void updateData(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView subjectTextView;

        NoteViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.textTitle);
            descriptionTextView = itemView.findViewById(R.id.textDescription);
            subjectTextView = itemView.findViewById(R.id.textSubject);
        }
    }
} 