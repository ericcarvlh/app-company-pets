package com.example.appcompanypets.Fragments.compra;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcompanypets.Activities.MenuLateralActivity;
import com.example.appcompanypets.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CompraFragment4 extends Fragment
{
    FragmentActivity context;
    Button buttonVoltarAoMenu;
    String dataEntrega;
    LocalDate localDate;
    ImageView imageViewFotoErroOuSucesso;
    TextView textViewresultado, textViewMensagem;
    boolean respostaProcedimento;
    String mensagemErro;

    public CompraFragment4(boolean respostaProcedimento, String mensagemErro)
    {
        this.respostaProcedimento = respostaProcedimento;
        this.mensagemErro = mensagemErro;
    }

    public CompraFragment4(boolean respostaProcedimento)
    {
        this.respostaProcedimento = respostaProcedimento;
    }

    public CompraFragment4()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity)
    {
        context = (FragmentActivity) activity;

        super.onAttach(activity);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_compra4, container, false);

        buttonVoltarAoMenu = view.findViewById(R.id.buttonCompraFinalizada);
        imageViewFotoErroOuSucesso = view.findViewById(R.id.imageViewErroOuSucesso);
        textViewresultado = view.findViewById(R.id.textViewResultado);
        textViewMensagem = view.findViewById(R.id.textViewMensagem);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            localDate = LocalDate.now().plusDays(10);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataEntrega = localDate.format(formatter);
        }

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dataAtual = day+"/"+month+"/"+year;

        Toast.makeText(context, "O seu pedido chegará entre os dias "+ dataAtual + " e " + dataEntrega, Toast.LENGTH_SHORT).show();

        if (respostaProcedimento){
            imageViewFotoErroOuSucesso.setImageResource(R.drawable.sucesso);
            textViewresultado.setText("Sucesso ao realizar a compra!");
            textViewMensagem.setText("O seu pedido chegará entre os dias \n"+dataAtual+" e "+ dataEntrega +"." +"\n Agradecemos sua preferência ao comprar conosco.");
        }
        else{
            imageViewFotoErroOuSucesso.setImageResource(R.drawable.erro);
            textViewresultado.setText("Ops...ocorreu um erro inesperado.");
            textViewMensagem.setText("Parece que ocorreu algum erro interno, lamentamos. Porém você pode tentar novamente em breve. "+"\n"+mensagemErro);
        }

        buttonVoltarAoMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, MenuLateralActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}