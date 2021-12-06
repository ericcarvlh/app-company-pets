package com.example.appcompanypets.Fragments.cadastro;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CadastroFragment1 extends Fragment
{
    private DtoUsuario dto = new DtoUsuario();
    FragmentActivity context;
    Spinner spinner_sexo;
    Button buttonAvancar;
    TextInputEditText editTextNome, editTextTelefone, editTextCelular,
            editTextDataNascimento, editTextCPF;
    int ano, mes, dia, idadeUsuario;

    public CadastroFragment1()
    {

    }

    @Override
    public void onAttach(Activity activity)
    {
        context = (FragmentActivity) activity;

        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cadastro1, container, false);

        buttonAvancar = view.findViewById(R.id.buttonAvancar);
        dto.setDt_Nascimento("");
        editTextNome = view.findViewById(R.id.editTextNome_Cadastro);
        editTextDataNascimento = view.findViewById(R.id.editTextDataNascimento_Cadastro);
        editTextCPF = view.findViewById(R.id.editTextCPF_Cadastro);
        editTextCelular = view.findViewById(R.id.editTextCelular_Cadastro);
        editTextTelefone = view.findViewById(R.id.editTextTelefone_Cadastro);
        spinner_sexo = view.findViewById(R.id.spinnerParcela_CartaoCredito);

        editTextDataNascimento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day)
                    {
                        idadeUsuario = Calendar.getInstance().get(Calendar.YEAR) - year ;
                        month = month+1;
                        String dataNascDemonstrativa = day+"/"+month+"/"+year;
                        dto.setDt_Nascimento(year+"-"+month+"-"+day);
                        editTextDataNascimento.setText(dataNascDemonstrativa);
                    }
                }, ano, mes, dia);
                datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_sexo, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sexo.setAdapter(adapter);

        spinner_sexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        dto.setSg_Sexo("");
                        break;
                    case 1:
                        dto.setSg_Sexo("M");
                        break;
                    case 2:
                        dto.setSg_Sexo("F");
                        break;
//                    case 3:
//                        dto.setSg_Sexo("P");
//                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        buttonAvancar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setNm_Usuario(editTextNome.getText().toString());
                dto.setNo_Telefone(editTextTelefone.getText().toString());
                dto.setNo_Celular(editTextCelular.getText().toString());
                dto.setNo_CPF(editTextCPF.getText().toString());

                if(dto.getNm_Usuario().equals(""))
                Toast.makeText(getActivity(), "É obrigatório informar o nome.", Toast.LENGTH_SHORT).show();
                else if(dto.getDt_Nascimento().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar a data de nascimento.", Toast.LENGTH_SHORT).show();
                else if(idadeUsuario < 18 )
                    Toast.makeText(getActivity(), "Você precisa ter no mínimo 18 anos para prosseguir.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_CPF().equals("") || dto.getNo_CPF().length()<11)
                    Toast.makeText(getActivity(), "É obrigatório informar o CPF, mínimo de 11 e maxímo de 11 digitos.", Toast.LENGTH_SHORT).show();
                else if(dto.getSg_Sexo().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório selecionar um sexo.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_Telefone().equals("") || dto.getNo_Telefone().length()<10)
                    Toast.makeText(getActivity(), "É obrigatório informar o número de telefone, mínimo de 10 e maxímo de 10 digitos.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_Celular().equals("") || dto.getNo_Celular().length()<11)
                    Toast.makeText(getActivity(), "É obrigatório informar o número de celular, mínimo de 11 e maxímo de 11 digitos.", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        CadastroFragment2 fragment = new CadastroFragment2(dto);
                        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frameLayoutConteudoCadastro, fragment);
                        transaction.commit();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        Log.d("ExNoCadastro1", e.toString());
                        Toast.makeText(getActivity(), "Parece que ocorreu um exception: " +e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return  view;
    }
}