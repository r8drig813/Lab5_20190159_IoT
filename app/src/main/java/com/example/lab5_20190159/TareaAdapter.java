package com.example.lab5_20190159;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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




        holder.buttonSave.setOnClickListener(v -> {
            Intent intent = new Intent(context, Task_Activity.class);
            intent.putExtra("tarea", (CharSequence) tarea);
            intent.putExtra("tareaIndex", position);
            ((List_tasks) context).startActivityForResult(intent, List_tasks.EDIT);
        });

        // Configurar el botón de eliminación
        holder.buttonCancel.setOnClickListener(v -> {
            tareaList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, tareaList.size());
            ((List_tasks) context).guardar(tareaList);
        });


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

        @SuppressLint("WrongViewCast")
        public TareaViewHolder(@NotNull View itemView) {
            super(itemView);

            editTextTitle = itemView.findViewById(R.id.titulo);
            editTextDescription = itemView.findViewById(R.id.descripcion);
            datePicker = itemView.findViewById(R.id.date);
            buttonSave = itemView.findViewById(R.id.Edit);
            buttonCancel = itemView.findViewById(R.id.Delete);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }
}
