package br.usjt.ucsist.armazena_lugares.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.usjt.ucsist.armazena_lugares.R;
import br.usjt.ucsist.armazena_lugares.model.Lugar;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView listaFirestore;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;



    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public interface LongKeyPressedEventListner {
        void longKeyPressed(int position);
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        firebaseFirestore = FirebaseFirestore.getInstance();
        listaFirestore = view.findViewById(R.id.listaFirestore);

        Query query = firebaseFirestore.collection("Lugares").orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Lugar> options = new FirestoreRecyclerOptions.Builder<Lugar>().
                setQuery(query, Lugar.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Lugar, HomeFragment.LugarViewHolder>(options) {

            LongKeyPressedEventListner longKeyPressedEventListner;

            @NonNull
            @Override
            public HomeFragment.LugarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lugar_item, parent, false);
                return new HomeFragment.LugarViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull HomeFragment.LugarViewHolder holder, int position, @NonNull Lugar model) {
                holder.listaEndereco.setText(model.getEndereco());
                holder.listaLatitude.setText(model.getLatitude());
                holder.listaLongitude.setText(model.getLongitude());
                holder.listaDescricao.setText(model.getDescricao());
                holder.listaDataCadastro.setText(model.getDataCadastro());

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        holder.deleta();
                        getSnapshots().getSnapshot(holder.deleta()).getReference().delete();
                        Toast.makeText(getActivity(),"Lugar deletado",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        };

        listaFirestore.setHasFixedSize(true);
        listaFirestore.setLayoutManager(new LinearLayoutManager(getActivity()));
        listaFirestore.setAdapter(adapter);
    }

    public class LugarViewHolder extends RecyclerView.ViewHolder{

        private TextView listaEndereco;
        private TextView listaLatitude;
        private TextView listaLongitude;
        private TextView listaDescricao;
        private TextView listaDataCadastro;


        public LugarViewHolder(@NonNull View itemView) {
            super(itemView);

            listaEndereco =  itemView.findViewById(R.id.listaEndereco);
            listaLatitude  = itemView.findViewById(R.id.listaLatitude);
            listaLongitude = itemView.findViewById(R.id.listaLongitude);
            listaDescricao = itemView.findViewById(R.id.listaDescricao);
            listaDataCadastro = itemView.findViewById(R.id.listaDataCadastro);

        }

        public int deleta() {
            //Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();
            return getAdapterPosition();


        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void deleta(int position){

    }

}