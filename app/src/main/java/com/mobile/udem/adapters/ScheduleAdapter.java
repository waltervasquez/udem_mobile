package com.mobile.udem.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.udem.R;
import com.mobile.udem.models.Schedule;

import java.util.List;

/**
 * Created by osmar on 09-03-17.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>{
    private List<Schedule> schedules;
    private Context context;

    public ScheduleAdapter(List<Schedule> schedules, Context context){

        this.schedules = schedules;
        this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_schedule, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Schedule schedule = schedules.get(position);

        holder.mClassName.setText(schedule.getAsignaura());
        holder.mGroup.setText(schedule.getGrupo());
        holder.mClassroom.setText(schedule.getAula());

        String day = "";
        String start = "";
        String finish = "";
        switch (schedule.getTurno()){
            case "M":
                start = "8:30 am";
                finish = "12:00 md";
                switch (schedule.getDia()){
                    case "1":
                        day = "Lunes";
                        break;
                    case "2":
                        day = "Martes";
                        break;
                    case "3":
                        day = "Miércoles";
                        break;
                    case "4":
                        day = "Jueves";
                        break;
                    case "5":
                        day = "Viernes";
                        break;

                    default:
                        start = "ND";
                        finish = "ND";
                        break;
                }
                break;
            case "N":
                start = "6:15 pm";
                finish = "8:15 pm";
                switch (schedule.getDia()){
                    case "1":
                        day = "Lunes";
                        break;
                    case "2":
                        day = "Martes";
                        break;
                    case "3":
                        day = "Miércoles";
                        break;
                    case "4":
                        day = "Jueves";
                        break;
                    case "5":
                        day = "Viernes";
                        break;

                    default:
                        start = "ND";
                        finish = "ND";
                        break;
                }
                break;
            case "S":
                day = "Sábado";
                switch(schedule.getDia()) {
                    case "1":
                        start = "7:30 am";
                        finish = "9:15 am";
                        break;
                    case "2":
                        start = "9:30 am";
                        finish = "11:15 am";
                        break;
                    case "3":
                        start = "11:30 am";
                        finish = "1:15 am";
                        break;
                    case "4":
                        start = "2:00 pm";
                        finish = "3:45 pm";
                        break;
                    default:
                        start = "ND";
                        finish = "ND";
                        break;
                }
                break;
            case "D":
                day = "Domingo";
                switch(schedule.getDia()) {
                    case "1":
                        start = "7:30 am";
                        finish = "9:15 am";
                        break;
                    case "2":
                        start = "9:30 am";
                        finish = "11:15 am";
                        break;
                    case "3":
                        start = "11:30 am";
                        finish = "1:15 am";
                        break;
                    case "4":
                        start = "2:00 pm";
                        finish = "3:45 pm";
                        break;
                    default:
                        start = "ND";
                        finish = "ND";
                        break;
                }
                break;
        }

        holder.mDay.setText(day);
        holder.mTimeStart.setText(start);
        holder.mTimeFinish.setText(finish);
    }

    @Override
    public int getItemCount() {
        return schedules.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        private TextView mClassName, mGroup, mClassroom, mDay, mTimeStart, mTimeFinish;

        ViewHolder(View v) {
            super(v);
            mView = v;
            mClassName = (TextView) v.findViewById(R.id.class_name);
            mClassroom = (TextView) v.findViewById(R.id.class_room);
            mGroup = (TextView) v.findViewById(R.id.class_group);
            mDay = (TextView) v.findViewById(R.id.class_day);
            mTimeStart = (TextView) v.findViewById(R.id.class_time_start);
            mTimeFinish = (TextView) v.findViewById(R.id.class_time_finish);
        }
    }
}
