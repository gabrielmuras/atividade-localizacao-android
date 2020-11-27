package br.usjt.ucsist.armazena_lugares.ui;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.usjt.ucsist.armazena_lugares.R;
import br.usjt.ucsist.armazena_lugares.model.Lugar;


public class LugarFragment extends Fragment {

    private Lugar lugar;
    private EditText editEndereco, editDescricao;
    //private Context context;
    private Button buttonSalvar;
    // private DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-mm-yyyy");
    FirebaseFirestore db;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;





    public LugarFragment() {
        // Required empty public constructor
    }



    public static LugarFragment newInstance(String param1, String param2) {
        LugarFragment fragment = new LugarFragment();
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
        return inflater.inflate(R.layout.fragment_lugar, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        editEndereco = view.findViewById(R.id.editEnderecoL);
        editDescricao = view.findViewById(R.id.editDescricaoL);
        buttonSalvar = view.findViewById(R.id.buttonConcluirL);



        db = FirebaseFirestore.getInstance();

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    salvarL();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void salvarL() throws IOException {


        String endereco = editEndereco.getText().toString();
        String descricao = editDescricao.getText().toString();

        Geocoder geoCoder = new Geocoder(getActivity());

        List<Address> latLong = geoCoder.getFromLocationName(endereco, 1);;

        try {

            Address localizacao = latLong.get(0);

            String lat = Double.toString((localizacao.getLatitude()));
            String lon = Double.toString((localizacao.getLongitude()));

            lugar = new Lugar(endereco, lat, lon, descricao, pegaData(), pegaTimeStamp());

            try {

                db.collection("Lugares").add(lugar);

            } catch (Exception e) {

                Toast.makeText(getActivity(), "Não foi possível inserir no banco de dados, verifique sua conexão",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Lugar não encontrado",
                    Toast.LENGTH_SHORT).show();
        }



    }
    
    
    public String pegaData() {
        
        DateFormat dateFormat = new SimpleDateFormat("k:mm - dd/MM/yy");
        Date date = Calendar.getInstance().getTime();
        String data = dateFormat.format(date);
        
        return data;
    }

    public long pegaTimeStamp(){

        long epoch = System.currentTimeMillis()/1000;

        return epoch;
    }
}