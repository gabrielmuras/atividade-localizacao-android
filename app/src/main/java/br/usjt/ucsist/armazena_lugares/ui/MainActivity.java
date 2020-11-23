package br.usjt.ucsist.armazena_lugares.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.MapFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        replaceFragment(R.id.frameLayoutMain,
                HomeFragment.newInstance("",""),
                "HOMEFRAGMENT",
                "HOME");



        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        replaceFragment(R.id.frameLayoutMain,
                                HomeFragment.newInstance("",""),
                                "HOMEFRAGMENT",
                                "HOME");
                        return true;

                    case R.id.lugar:
                        replaceFragment(R.id.frameLayoutMain,
                                LugarFragment.newInstance("",""),
                                "LUGARFRAGMENT",
                                "LUGAR");
                        return true;

                    case R.id.perfil:
                        replaceFragment(R.id.frameLayoutMain,
                                PerfilFragment.newInstance("",""),
                                "PERFILFRAGMENT",
                                "PERFIL");
                        return true;

                    case R.id.mapa:
                        replaceFragment(R.id.frameLayoutMain,
                                new MapaFragment(),
                                "MAPAFRAGMENT",
                                "MAPA");
                        return true;


                }


                return false;
            }
        });

    }

    void openFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutMain, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sair:
                Intent intent = new Intent(this, LugarActivity.class);
                startActivity(intent);
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }



}
