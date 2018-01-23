package com.jhorje18.navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ConfigFragment extends Fragment {

    //Variables
    RadioGroup rgIdioma;
    EditText editNombre;
    Button btnGuardar;

    public ConfigFragment() {
        // Required empty public constructor
    }

    public static ConfigFragment newInstance(String param1, String param2) {
        ConfigFragment fragment = new ConfigFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_config, container, false);

        //Variables vista
        editNombre = (EditText) v.findViewById(R.id.editUsuario);
        rgIdioma = (RadioGroup) v.findViewById(R.id.rgIdiomas);
        btnGuardar = (Button) v.findViewById(R.id.btnGuardar);

        //Eventos
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtener idioma seleccionado
                String idiomaElejido = "";
                switch (rgIdioma.getCheckedRadioButtonId()){
                    case R.id.rbCastellano:
                        idiomaElejido = "Castellano";
                        break;
                    case R.id.rbValencia:
                        idiomaElejido = "Valenciano";
                        break;
                    case R.id.rbIngles:
                        idiomaElejido = "Ingles";
                        break;
                }

                //Obtener nombre usuario
                String usuarioName = editNombre.getText().toString();

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("idioma", idiomaElejido);
                editor.putString("user", usuarioName);
                editor.commit();

                Toast.makeText(getContext(), "Preferencias guardadas", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
