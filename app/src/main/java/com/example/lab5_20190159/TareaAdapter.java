package com.example.lab5_20190159;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_20190159.entity.Tarea;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {


    private List<Tarea> tareaList;
    private Context context;

    public TareaAdapter(List<Tarea> tareaList, Context context) {
        this.tareaList = tareaList;
        this.context = context;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {

        Tarea tarea  = tareaList.get(position);
        holder.editTextTitle.setText(tarea.getTitle());
        holder.editTextDescription.setText(tarea.getDescription());
        holder.datePicker.setTextDirection(Integer.parseInt(tarea.getDueDate().toString()));


    }

    @Override
    public int getItemCount() {
        return tareaList.size();
    }


    public class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView editTextTitle, editTextDescription;
        DatePicker datePicker;
        Button buttonSave, buttonCancel;
        CardView cardView;
        Tarea tarea;

        public TareaViewHolder(@NotNull View itemView) {
            super(itemView);

            editTextTitle = itemView.findViewById(R.id.editTextTitle);
            editTextDescription = itemView.findViewById(R.id.editTextDescription);
            datePicker = itemView.findViewById(R.id.datePicker);
            buttonSave = itemView.findViewById(R.id.buttonSave);
            buttonCancel = itemView.findViewById(R.id.buttonCancel);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }
}
