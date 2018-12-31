package com.dabinu.app.jot.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dabinu.app.jot.R;
import com.dabinu.app.jot.models.NoteModel;
import java.util.ArrayList;


/**
 * Created by Taslim Oseni on 12/30/18.
 */

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder>{


    Context context;
    ArrayList<NoteModel> noteModels;
    private static ClickListener clickListener;


    public NoteListAdapter(Context context, ArrayList<NoteModel> noteModels){
        this.context = context;
        this.noteModels = noteModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.note_layout, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){

        if(noteModels.get(position).getNote().length() <= 30){
            holder.summary.setText(noteModels.get(position).getNote());
        }
        else{
            holder.summary.setText(noteModels.get(position).getNote().substring(0, Math.min((noteModels.get(position).getNote().length()), 30)));
        }
        holder.date.setText(noteModels.get(position).getDate());
        holder.time.setText(noteModels.get(position).getTime());
    }

    @Override
    public int getItemCount(){
        return noteModels.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        public TextView summary, date, time;

        public MyViewHolder(View itemView) {
            super(itemView);

            summary = itemView.findViewById(R.id.summary);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }


    }

    public void setOnItemClickListener(ClickListener clickListener){
        NoteListAdapter.clickListener = clickListener;
    }


    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

}