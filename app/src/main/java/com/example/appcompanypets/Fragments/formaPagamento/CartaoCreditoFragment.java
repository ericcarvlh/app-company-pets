package com.example.appcompanypets.Fragments.formaPagamento;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcompanypets.DTO.DtoCartaoCredito;
import com.example.appcompanypets.Fragments.compra.CompraFragment2;
import com.example.appcompanypets.R;
import com.google.android.material.textfield.TextInputEditText;

public class CartaoCreditoFragment extends Fragment
{
    FragmentActivity context;
    DtoCartaoCredito dto = new DtoCartaoCredito();
    Button buttonContinuar;
    Spinner spinnerParcela, spinnerMes;
    TextInputEditText editTextNomeImpresso, editTextNumeroCartao, editTextCVV, editTextAnoValidade;
    String formaPagamento = "Cartão de Crédito";
    String tipoEntrega;

    public CartaoCreditoFragment(String tipoEntrega)
    {
        this.tipoEntrega = tipoEntrega;
    }

    public CartaoCreditoFragment()
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
        View view = inflater.inflate(R.layout.fragment_cartao_credito, container, false);

        editTextNomeImpresso = view.findViewById(R.id.editTextNomeImpresso_CartaoCredito);
        editTextNumeroCartao = view.findViewById(R.id.editTextNumero_CartaoCredito);
        editTextCVV = view.findViewById(R.id.editTextCVV_CartaoCredito);
        editTextAnoValidade = view.findViewById(R.id.editTextAnoValidade_CartaoCredito);

        buttonContinuar = view.findViewById(R.id.buttonContinuar_CartaoCredito);

        spinnerParcela = view.findViewById(R.id.spinnerParcela_CartaoCredito);
        spinnerMes = view.findViewById(R.id.spinnerMes_CartaoCredito);

        ArrayAdapter<CharSequence> parcelamento = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_parcelamento, android.R.layout.simple_spinner_dropdown_item);
        parcelamento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParcela.setAdapter(parcelamento);

        ArrayAdapter<CharSequence> mes = ArrayAdapter.createFromResource(getActivity(), R.array.spinner_mes, android.R.layout.simple_spinner_dropdown_item);
        mes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(mes);

        spinnerParcela.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position>0)
                    dto.setNo_Parcelas(position+"");
                else
                    dto.setNo_Parcelas("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        spinnerMes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position>0)
                    dto.setDt_MesValidade(position+"");
                else
                    dto.setDt_MesValidade("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonContinuar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dto.setNm_Cartao(editTextNomeImpresso.getText().toString());
                dto.setNo_Cartao(editTextNumeroCartao.getText().toString());
                dto.setNo_CVV(editTextCVV.getText().toString());
                dto.setDt_AnoValidade(editTextAnoValidade.getText().toString());

                if (dto.getNm_Cartao().equals(""))
                    Toast.makeText(context, "Este campo é obrigatório.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_Parcelas().equals("0"))
                    Toast.makeText(context, "Escolha a quantidade de parcelas que você deseja.", Toast.LENGTH_SHORT).show();
                else if(dto.getNo_CVV().length()<3 || dto.getNo_CVV().equals(""))
                    Toast.makeText(context, "Este campo é obrigatório. Tendo no mínimo 3 e no máximo 3 digitos.", Toast.LENGTH_SHORT).show();
                else if(dto.getDt_AnoValidade().equals("") || dto.getDt_AnoValidade().length()<4)
                    Toast.makeText(context, "Este campo é obrigatório. Tendo no mínimo 4 e no máximo 4 digitos.", Toast.LENGTH_SHORT).show();
                else if(dto.getDt_MesValidade().equals(""))
                    Toast.makeText(context, "Escolha o mês de validade.", Toast.LENGTH_SHORT).show();
                else {
                    FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment2(tipoEntrega, formaPagamento, null, dto));
                    transaction.commit();
                }
            }
        });

        return view;
    }
}