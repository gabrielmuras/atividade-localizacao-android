package br.usjt.ucsist.armazena_lugares.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.usjt.ucsist.armazena_lugares.R;
import br.usjt.ucsist.armazena_lugares.model.Lugar;

public class LugarActivity extends AppCompatActivity {

    private RecyclerView listaFirestore;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugar);

        firebaseFirestore = FirebaseFirestore.getInstance();
        listaFirestore = findViewById(R.id.listaFirestore);

        Query query = firebaseFirestore.collection("Lugares");

        FirestoreRecyclerOptions<Lugar> options = new FirestoreRecyclerOptions.Builder<Lugar>().
                setQuery(query, Lugar.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Lugar, LugarViewHolder>(options) {
            @NonNull
            @Override
            public LugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lugar_item, parent, false);
                return new LugarViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull LugarViewHolder holder, int position, @NonNull Lugar model) {
                holder.listaLatitude.setText(model.getLatitude());
                holder.listaLongitude.setText(model.getLongitude());
                holder.listaDescricao.setText(model.getDescricao());
                holder.listaDataCadastro.setText(model.getDataCadastro());
            }
        };

        listaFirestore.setHasFixedSize(true);
        listaFirestore.setLayoutManager(new LinearLayoutManager(this));
        listaFirestore.setAdapter(adapter);
    }

    private class LugarViewHolder extends RecyclerView.ViewHolder{

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

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}