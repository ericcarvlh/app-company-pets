package com.example.appcompanypets.Fragments.compra;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appcompanypets.DAO.DaoCompra;
import com.example.appcompanypets.DTO.DtoCartaoCredito;
import com.example.appcompanypets.DTO.DtoCartaoDebito;
import com.example.appcompanypets.DTO.DtoCompra;
import com.example.appcompanypets.DTO.DtoDeliveryPresente;
import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Retrofit.ConfigRetrofit;
import com.example.appcompanypets.DAO.DaoBancoCarrinho;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CompraFragment3 extends Fragment
{
    FragmentActivity context;
    LocalDate localDate;
    String dataEntrega;
    String tipoEntrega;
    String formaPagamento;
    DtoCartaoCredito dtoCartaoCredito;
    DtoCartaoDebito dtoCartaoDebito;
    DtoDeliveryPresente dtoDeliveryPresente;
    Retrofit retrofit;
    ArrayList<DtoCompra> arrayListCompra = new ArrayList<>();
    DtoCompra dtoCompra = new DtoCompra();
    String url;

    public CompraFragment3(String tipoEntrega, String formaPagamento, DtoCartaoDebito dtoCartaoDebito, DtoCartaoCredito dtoCartaoCredito, DtoDeliveryPresente dtoDeliveryPresente)
    {
        this.tipoEntrega = tipoEntrega;
        this.formaPagamento = formaPagamento;
        this.dtoCartaoDebito = dtoCartaoDebito;
        this.dtoCartaoCredito = dtoCartaoCredito;
        this.dtoDeliveryPresente = dtoDeliveryPresente;
    }

    public CompraFragment3()
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
        View view = inflater.inflate(R.layout.fragment_compra3, container, false);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            localDate = LocalDate.now().plusDays(10);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataEntrega = localDate.format(formatter);
        }

        DaoBancoCarrinho dao = new DaoBancoCarrinho(context);
        DtoCompra.ItensCarrinho = dao.consultarItensCarrinho();
        dtoCompra.setVl_TotalCompra(dao.consultaValorTotal(DtoUsuario.cd_UsuLogin));

        if (tipoEntrega.equals("deliveryCli") || tipoEntrega.equals("deliveryLoj"))
        {
            dtoDeliveryPresente = new DtoDeliveryPresente();
            dtoDeliveryPresente.setNo_CEP("vazio");
            dtoDeliveryPresente.setNm_Cidade("vazio");
            dtoDeliveryPresente.setNm_Bairro("vazio");
            dtoDeliveryPresente.setNm_Logradouro("vazio");
            dtoDeliveryPresente.setNo_Logradouro("vazio");
            dtoDeliveryPresente.setDs_Complemento("vazio");
            dtoDeliveryPresente.setDs_UF("vazio");
        }

        if (dtoCartaoCredito!=null)
            realizaCadastroDaCompra(formaPagamento, tipoEntrega, dtoCompra.getVl_TotalCompra(), DtoUsuario.cd_UsuLogin, localDate.toString(), dtoCartaoCredito.getNm_Cartao(), dtoCartaoCredito.getNo_Cartao(), dtoCartaoCredito.getNo_CVV(), dtoCartaoCredito.getNo_Parcelas(), dtoCartaoCredito.getDt_MesValidade(), dtoCartaoCredito.getDt_AnoValidade(), dtoDeliveryPresente.getDs_UF(), dtoDeliveryPresente.getNm_Cidade(), dtoDeliveryPresente.getNm_Bairro(), dtoDeliveryPresente.getNm_Logradouro(), dtoDeliveryPresente.getNo_Logradouro(), dtoDeliveryPresente.getNo_CEP(), dtoDeliveryPresente.getDs_Complemento());
        else
            realizaCadastroDaCompra(formaPagamento, tipoEntrega, dtoCompra.getVl_TotalCompra(), DtoUsuario.cd_UsuLogin, dataEntrega, dtoCartaoDebito.getNm_Cartao(), dtoCartaoDebito.getNo_Cartao(), dtoCartaoDebito.getNo_CVV(), "", dtoCartaoDebito.getDt_MesValidade(), dtoCartaoDebito.getDt_AnoValidade(), dtoDeliveryPresente.getDs_UF(), dtoDeliveryPresente.getNm_Cidade(), dtoDeliveryPresente.getNm_Bairro(), dtoDeliveryPresente.getNm_Logradouro(), dtoDeliveryPresente.getNo_Logradouro(), dtoDeliveryPresente.getNo_CEP(), dtoDeliveryPresente.getDs_Complemento());

        return view;
    }

    private void realizaCadastroDaCompra(String formaPagamento, String tipoEntrega, double vl_TotalItens, int cd_Usuario, String dt_Delivery, String nm_Cartao, String no_Cartao, String no_CVV, String no_Parcelas, String dt_MesValidade, String dt_AnoValidade, String UFEstado, String cidade, String bairro, String logradouro, String numero, String CEP, String complemento)
    {
        if (no_Parcelas.equals(""))
            no_Parcelas = "vazio";

        if (tipoEntrega.equals("deliveryCli") || tipoEntrega.equals("deliveryLoj"))
        {
            UFEstado = "vazio";
            cidade = "vazio";
            bairro = "vazio";
            logradouro = "vazio";
            numero = "vazio";
            CEP = "vazio";
            complemento = "vazio";
        }

        retrofit = ConfigRetrofit.getRetrofit();
        DaoCompra daoC = retrofit.create(DaoCompra.class);
        url = "https://appcompanypetsapi.000webhostapp.com/realizaCompra.php?formaPagamento="+formaPagamento+"&tipoEntrega="+tipoEntrega+"&cd_Usuario="+cd_Usuario+"&vl_TotalItens="+vl_TotalItens+"&dt_Delivery="+dt_Delivery+"&nm_Cartao="+nm_Cartao+"&no_Cartao="+no_Cartao+"&no_CVV="+no_CVV+"&no_Parcelas="+no_Parcelas+"&dt_MesValidade="+dt_MesValidade+"&dt_AnoValidade="+dt_AnoValidade+"&ds_UF="+UFEstado+"&nm_Cidade="+cidade+"&nm_Bairro="+bairro+"&nm_Logradouro="+logradouro+"&no_Logradouro="+numero+"&no_CEP="+CEP+"&ds_Complemento="+complemento;
        Call<ArrayList<DtoCompra>> call = daoC.realizaCadastroCompra(formaPagamento, tipoEntrega, cd_Usuario, vl_TotalItens, dt_Delivery, nm_Cartao, no_Cartao, no_CVV, no_Parcelas, dt_MesValidade, dt_AnoValidade, UFEstado, cidade, bairro, logradouro, numero, CEP, complemento);
        call.enqueue(new Callback<ArrayList<DtoCompra>>()
        {
            @Override
            public void onResponse(Call<ArrayList<DtoCompra>> call, Response<ArrayList<DtoCompra>> response)
            {
                arrayListCompra = response.body();
                if(arrayListCompra.size()!=0)
                {
                    dtoCompra.setCd_Compra(arrayListCompra.get(0).getCd_Compra());
                    DaoBancoCarrinho daoB = new DaoBancoCarrinho(context);
                    DtoCompra.ItensCarrinho = daoB.consultarItensCarrinho();
                    adicionaeRemoveItensCarrinho(daoB, dtoCompra);
                }
                else
                    Toast.makeText(context, "Algo deu errado."+ response.body(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ArrayList<DtoCompra>> call, Throwable throwable)
            {
                Toast.makeText(context, "Erro: " + throwable.toString(), Toast.LENGTH_SHORT).show();
                Log.e("LoginCallBooljsom", throwable.toString());
            }
        });

    }

    private void adicionaeRemoveItensCarrinho(DaoBancoCarrinho dao, DtoCompra dto)
    {
        if (dto.getCd_Compra()!=0)
        {
            for (int i = 0; i < DtoCompra.ItensCarrinho.size(); i++)
            {
                double vl_TotalProdutoIguais = DtoCompra.ItensCarrinho.get(i).getQt_Produto() * DtoCompra.ItensCarrinho.get(i).getVl_Produto();
                insereItensCompras(DtoCompra.ItensCarrinho.get(i).getCd_Produto(), DtoCompra.ItensCarrinho.get(i).getQt_Produto(), vl_TotalProdutoIguais, dtoCompra.getCd_Compra());
                dao.remover(DtoCompra.ItensCarrinho.get(i).getCd_Produto(), DtoUsuario.cd_UsuLogin);
            }
        }
    }

    private void insereItensCompras(int cd_Produto, int qt_Itens, double vl_TotalItens, int cd_Compra)
    {
        retrofit = ConfigRetrofit.getRetrofit();
        DaoCompra dao = retrofit.create(DaoCompra.class);
        Call<Boolean> call = dao.insereItensCompra(cd_Produto, qt_Itens,  vl_TotalItens, cd_Compra);
        call.enqueue(new Callback<Boolean>()
        {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response)
            {
                FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
                if (response.isSuccessful()){
                    transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment4(true));
                }
                else {
                    transaction.replace(R.id.frameLayoutConteudoCompra, new CompraFragment4(false, response.toString()));
                }
                transaction.commit();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable throwable)
            {
                Toast.makeText(context, "Erro fatal em Compra3: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Compra3FatalExInsereItn", throwable.toString());
            }
        });
    }
}