package br.usjt.ucsist.armazena_lugares.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.orhanobut.hawk.Hawk;

import br.usjt.ucsist.armazena_lugares.R;
import br.usjt.ucsist.armazena_lugares.model.Usuario;
import br.usjt.ucsist.armazena_lugares.model.UsuarioViewModel;

public class MainActivity extends AppCompatActivity {


    private UsuarioViewModel usuarioViewModel;
    private Usuario usuarioCorrente;
    private TextView editTextNome;
    private TextView editTextEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         {
            
        };
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editar:
                Intent intent = new Intent(this, CadastroActivity.class);
                startActivity(intent);
                return (true);
            case R.id.sair:
                finish();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }


}
