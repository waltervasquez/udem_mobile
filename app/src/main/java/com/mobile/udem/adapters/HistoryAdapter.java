package com.mobile.udem.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.mobile.udem.R;
import com.mobile.udem.models.HistorialClaseGrado;
import com.mobile.udem.models.History;

import java.util.List;

/**
 * Created by osmar on 08-01-17.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<History> histories;
    private Context context;
    public static int totalApproved = 0;

    public HistoryAdapter(List<History> histories, Context context){

        this.histories = histories;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_history, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final History history = histories.get(position);

        holder.mNumber.setText(String.valueOf(history.getGrado()));

        for(int i=0;i<history.getHistorialClaseGrado().size();i++)
        {
            HistorialClaseGrado historial = history.getHistorialClaseGrado().get(i);

            TableRow row = new TableRow(context);
            View view = LayoutInflater.from(context).inflate(R.layout.table_row_history, null);

            TextView textView =  (TextView)view.findViewById(R.id.history_class);
            textView.setText(historial.getAsignatura());
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.history_state);
            if(historial.getAprobado()) {
                checkBox.setChecked(true);
                totalApproved = totalApproved + 1;
            }
            row.addView(view);
            holder.tableLayout.addView(row, i);
            // Adds the view to the layout
        }

    }


    @Override
    public int getItemCount() {
        return histories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View mView;
        private TextView mNumber;
        private TableLayout tableLayout;

        ViewHolder(View v) {
            super(v);
            mView = v;
            mNumber = (TextView) v.findViewById(R.id.row_history_number);
            tableLayout = (TableLayout) v.findViewById(R.id.history_table);
            tableLayout.setStretchAllColumns(true);
        }
    }
}
