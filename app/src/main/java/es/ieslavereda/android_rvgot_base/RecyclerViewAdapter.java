package es.ieslavereda.android_rvgot_base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import es.ieslavereda.android_rvgot_base.model.Personaje;
import es.ieslavereda.android_rvgot_base.model.PersonajeRepository;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context context;
    private ItemClick itemClic;

    public RecyclerViewAdapter(Context context, ItemClick itemClic) {
        this.context = context;
        this.itemClic = itemClic;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_item,parent,false);
        return new MyViewHolder(view,itemClic);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setPersonaje(PersonajeRepository.getInstance().get(position));
    }

    @Override
    public int getItemCount() {
        return PersonajeRepository.getInstance().size();
    }

    interface ItemClick {
        void myRecyclerViewOnClick(Personaje personaje);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivEscudo;
        private TextView tvNombre;
        private TextView tvCasa;
        private Personaje personaje;

        public MyViewHolder(@NonNull View itemView, ItemClick itemClic ) {
            super(itemView);
            ivEscudo = itemView.findViewById(R.id.ivEscudo);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCasa = itemView.findViewById(R.id.tvCasa);
            itemView.setOnClickListener(v->itemClic.myRecyclerViewOnClick(personaje));
        }
        public void setPersonaje(Personaje personaje){
            this.personaje=personaje;
            ivEscudo.setImageDrawable(context.getDrawable(personaje.getCasa().getEscudo()));
            tvNombre.setText(personaje.getNombre());
            tvCasa.setText(personaje.getCasa().getNombre());
        }
    }
}
