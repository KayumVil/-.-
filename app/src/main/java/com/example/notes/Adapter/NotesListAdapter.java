package com.example.notes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.Models.Notes;
import com.example.notes.NotesClickListener;
import com.example.notes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter <NotesViewHolder>{

    Context context;
    List<Notes> list;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    NotesClickListener listener;


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {

        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(list.get(position).getNotes());

        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);
        if(list.get(position).isPinned()){
            holder.imageView_pin.setImageResource(R.drawable.pin_icon);
        }
        else{
            holder.imageView_pin.setImageResource(0);
        }

        holder.notes_conteiner.setCardBackgroundColor(holder.itemView.getResources().getColor(getRandomColor(), null));

        holder.notes_conteiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });
        holder.notes_conteiner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.notes_conteiner);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void filterList(List<Notes> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }

    private int getRandomColor(){
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);
        colorCode.add(R.color.color6);
        Random random = new Random();
        return colorCode.get(random.nextInt(colorCode.size()));
    }
}
class NotesViewHolder extends RecyclerView.ViewHolder {
    CardView notes_conteiner;
    TextView textView_title;
    TextView textView_notes;
    ImageView imageView_pin;
    TextView textView_date;
    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        notes_conteiner = itemView.findViewById(R.id.notes_conteiner);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_notes = itemView.findViewById(R.id.textView_notes);
        imageView_pin = itemView.findViewById(R.id.imageView_pin);
        textView_date = itemView.findViewById(R.id.textView_date);
    }
}
