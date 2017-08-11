package com.mobile.udem.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.udem.R;
import com.mobile.udem.models.Notes;

import java.util.List;

/**
 * Created by osmar on 08-10-17.
 */

public class NoteAdapter  extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private List<Notes> notes;
    private Context context;
    public NoteAdapter(List<Notes> notes, Context context){

        this.notes = notes;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_notes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      Notes note = notes.get(position);
        holder.subject.setText(note.getAsignatura());
        holder.ac.setText(note.getAc());
        holder.ef.setText(note.getEf());
        holder.nf.setText(note.getNf());
        if(Integer.parseInt(note.getNf()) < 60)
            holder.nf.setTextColor(ContextCompat.getColor(context, R.color.red));
        else
            holder.nf.setTextColor(ContextCompat.getColor(context, R.color.green));

        holder.res.setText(note.getRes());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView subject, ac, ef, nf, res;
        private ViewHolder(View v) {
            super(v);
            subject = (TextView) v.findViewById(R.id.notes_subject);
            ac = (TextView) v.findViewById(R.id.notes_ac);
            ef = (TextView) v.findViewById(R.id.notes_ef);
            nf = (TextView) v.findViewById(R.id.notes_nf);
            res = (TextView) v.findViewById(R.id.notes_res);

        }
    }
}
