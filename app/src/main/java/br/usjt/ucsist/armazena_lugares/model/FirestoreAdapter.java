package br.usjt.ucsist.armazena_lugares.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import br.usjt.ucsist.armazena_lugares.R;
import br.usjt.ucsist.armazena_lugares.ui.HomeFragment;

public class FirestoreAdapter extends FirestoreRecyclerAdapter<Lugar, FirestoreAdapter.LugarViewHolder>  {

    public FirestoreAdapter(@NonNull FirestoreRecyclerOptions<Lugar> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull LugarViewHolder holder, int position, @NonNull Lugar model) {
        holder.listaLatitude.setText(model.getLatitude());
        holder.listaLongitude.setText(model.getLongitude());
        holder.listaDescricao.setText(model.getDescricao());
        holder.listaDataCadastro.setText(model.getDataCadastro());

    }

    @NonNull
    @Override
    public LugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }


    public class LugarViewHolder extends RecyclerView.ViewHolder {

        private TextView listaLatitude;
        private TextView listaLongitude;
        private TextView listaDescricao;
        private TextView listaDataCadastro;


        public LugarViewHolder(@NonNull View itemView) {
            super(itemView);

            listaLatitude  = itemView.findViewById(R.id.listaLatitude);
            listaLongitude = itemView.findViewById(R.id.listaLongitude);
            listaDescricao = itemView.findViewById(R.id.listaDescricao);
            listaDataCadastro = itemView.findViewById(R.id.listaDataCadastro);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int posicao = getAdapterPosition();
                    // adapter.deleta(posicao);
                }
            });

        }
    }
}
