package br.usjt.ucsist.armazena_lugares.ui;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.usjt.ucsist.armazena_lugares.R;
import br.usjt.ucsist.armazena_lugares.model.Lugar;


public class InserirActivity extends AppCompatActivity {

    private Lugar lugar;
    private EditText editEndereco, editDescricao;
    //private Context context;
    private Button buttonSalvar;
   // private DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-mm-yyyy");
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir_activity);


        editEndereco = findViewById(R.id.editEnderecoL);
        editDescricao = findViewById(R.id.editDescricaoL);
        buttonSalvar = findViewById(R.id.buttonConcluirL);



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

        //editEndereco.getText().toString());
        //editDescricao.getText().toString());

        String endereco = editEndereco.getText().toString();
        //tDate data = Calendar.getInstance().getTime();

        //DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-mm-yyyy");
        //String date = dateFormat.format(data);

        Geocoder geoCoder = new Geocoder(this);

        List<Address> latLong = latLong = geoCoder.getFromLocationName(endereco, 1);;


        Address localizacao = latLong.get(0);

        String lat = Double.toString((localizacao.getLatitude()));
        String lon = Double.toString((localizacao.getLongitude()));

        lugar = new Lugar(endereco, lat, lon, "Teste", "date");

        db.collection("Lugares").add(lugar);
    }


}
