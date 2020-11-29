package br.usjt.ucsist.armazena_lugares.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import br.usjt.ucsist.armazena_lugares.model.Usuario;
import br.usjt.ucsist.armazena_lugares.model.UsuarioViewModel;
import br.usjt.ucsist.armazena_lugares.ui.CadastroActivity;
import br.usjt.ucsist.armazena_lugares.R;


public class PerfilFragment extends Fragment {

    private UsuarioViewModel usuarioViewModel;
    private Usuario usuarioCorrente;
    private EditText editTextNome;
    private EditText editTextCPF;
    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button buttonSalvar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }


    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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

        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Hawk.init(getActivity()).build();

        editTextNome = view.findViewById(R.id.editTextNomeF);
        editTextEmail = view.findViewById(R.id.editTextEmailF);
        editTextSenha = view.findViewById(R.id.editTextSenhaF);
        buttonSalvar = view.findViewById(R.id.buttonConcluirF);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });

        usuarioCorrente = new Usuario();

        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);

        usuarioViewModel.getUsuario().observe(getActivity(), new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable final Usuario usuario) {
                updateView(usuario);
            }
        });

    }

    private void updateView(Usuario usuario) {
        if (usuario != null && usuario.getId() > 0) {
            usuarioCorrente = usuario;
            editTextNome.setText(usuario.getNome());
            editTextEmail.setText(usuario.getEmail());
            editTextSenha.setText(usuario.getSenha());
        }
    }

    public void salvar() {

        usuarioCorrente.setNome(editTextNome.getText().toString());
        usuarioCorrente.setEmail(editTextEmail.getText().toString());
        usuarioCorrente.setSenha(editTextSenha.getText().toString());
        usuarioViewModel.insert(usuarioCorrente);

        Hawk.put("ten_cadastro", true);
        Toast.makeText(getActivity(), "Cadastro alterado!",
                Toast.LENGTH_SHORT).show();

    }

}