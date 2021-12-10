package com.example.appcompanypets.Fragments.delivery;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcompanypets.Api.CEP.AsyncCEP;
import com.example.appcompanypets.DTO.DtoCartaoCredito;
import com.example.appcompanypets.DTO.DtoCartaoDebito;
import com.example.appcompanypets.DTO.DtoDeliveryPresente;
import com.example.appcompanypets.Fragments.compra.CompraFragment3;
import com.example.appcompanypets.R;
import com.google.android.material.textfield.TextInputEditText;

public class DeliveryPreFragment extends Fragment
{
    DtoDeliveryPresente dto = new DtoDeliveryPresente();
    FragmentActivity context;
    Button buttonContinuar;
    TextInputEditText editTextLogradouro, editTextBairro, editTextNumero, editTextCEP, editTextUF, editTextCidade, editTextComplemento;
    String formaPagamento;
    String tipoEntrega = "deliveryPre";
    DtoCartaoDebito dtoCartaoDebito;
    DtoCartaoCredito dtoCartaoCredito;

    public DeliveryPreFragment(String formaPagamento, DtoCartaoDebito dtoCartaoDebito, DtoCartaoCredito dtoCartaoCredito)
    {
        this.formaPagamento = formaPagamento;
        this.dtoCartaoDebito = dtoCartaoDebito;
        this.dtoCartaoCredito = dtoCartaoCredito;
    }

    public DeliveryPreFragment()
    {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_delivery_pre, container, false);

        buttonContinuar = view.findViewById(R.id.buttonContinuar_Delivery);

        editTextLogradouro = view.findViewById(R.id.editTextLogradouro_Delivery);
        editTextBairro = view.findViewById(R.id.editTextBairro_Delivery);
        editTextNumero = view.findViewById(R.id.editTextNumero_Delivery);
        editTextCEP = view.findViewById(R.id.editTextCEP_Delivery);
        editTextUF = view.findViewById(R.id.editTextUF_Delivery);
        editTextCidade = view.findViewById(R.id.editTextCidade_Delivery);
        editTextComplemento = view.findViewById(R.id.editTextComplemento_Delivery);

        editTextCEP.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                dto.setNo_CEP(editTextCEP.getText().toString());
                if(dto.getNo_CEP().length()==8)
                {
                    AsyncCEP async = new AsyncCEP(editTextLogradouro, editTextBairro, editTextCidade, editTextUF, dto.getNo_CEP());
                    async.execute();
                }
            }
        });

        buttonContinuar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setNo_UF(editTextUF.getText().toString());
                dto.setNm_Cidade(editTextCidade.getText().toString());
                dto.setNo_CEP(editTextCEP.getText().toString());
                dto.setNm_Logradouro(editTextLogradouro.getText().toString());
                dto.setNm_Bairro(editTextBairro.getText().toString());
                dto.setNo_Logradouro(editTextNumero.getText().toString());
                dto.setDs_Complemento(editTextComplemento.getText().toString());

                if(dto.getNo_UF().equals("") || dto.getNo_UF().length() != 2)
                    Toast.makeText(getActivity(), "É obrigatório informar o UF.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_CEP().equals("") || dto.getNo_CEP().length()<8)
                    Toast.makeText(getActivity(), "É obrigatório informar o CEP, mínimo de 8 e máximo de 8 digitos", Toast.LENGTH_SHORT).show();
                else if(dto.getNm_Logradouro().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o logradouro.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_Logradouro().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o numero, mínimo de 1 e máximo de 5 digitos", Toast.LENGTH_SHORT).show();
                else if(dto.getNm_Bairro().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar o bairro.", Toast.LENGTH_SHORT).show();
                else if(dto.getNm_Cidade().equals(""))
                    Toast.makeText(getActivity(), "É obrigatório informar A cidade.", Toast.LENGTH_SHORT).show();
                else {
                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment3(tipoEntrega, formaPagamento, dtoCartaoDebito, dtoCartaoCredito, dto));
                    transaction.commit();
                }
            }
        });

        return view;
    }
}