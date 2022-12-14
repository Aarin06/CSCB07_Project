package com.example.cscb07_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminEventAdapter extends RecyclerView.Adapter<AdminEventAdapter.ViewHolder> {

    Context context;
    ArrayList<Event> list;
    public AdminEventAdapter(Context context, ArrayList<Event> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdminEventAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.event_card_admin,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = list.get(position);
        holder.event_name.setText(event.getEventName());

        String creatorID = event.getCreatorID();
        FirebaseDatabase.getInstance().getReference("Users").child(creatorID).child("email").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                holder.creator.setText(String.valueOf(task.getResult().getValue()));
            }
        });
        holder.start_time.setText(event.getStartTime());
        holder.end_time.setText(event.getEndTime());
        holder.cur_players.setText(Long.toString(event.getNumPlayers()));
        holder.max_players.setText(Long.toString(event.getMaxPlayers()));
        String s1 = event.getDate();
        holder.date.setText(s1);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView event_name, creator, start_time, end_time, cur_players, max_players, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            event_name = itemView.findViewById(R.id.id_etAdminEventName);
            creator = itemView.findViewById(R.id.id_etAdminCreator);
            start_time = itemView.findViewById(R.id.id_etAdminStartTime);
            end_time = itemView.findViewById(R.id.id_etAdminEndTime);
            cur_players = itemView.findViewById(R.id.id_etAdminNumPlayers);
            max_players = itemView.findViewById(R.id.id_etAdminMaxPlayers);
            date = itemView.findViewById(R.id.id_etAdminDate);

        }
    }

}
