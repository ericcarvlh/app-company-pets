package com.example.appcompanypets.ui.carrinho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.appcompanypets.DTO.DtoProduto;
import com.example.appcompanypets.DTO.DtoUsuario;

import java.util.ArrayList;

public class DaoBancoCarrinho extends SQLiteOpenHelper
{
    public final String TABELA_CARRINHO = "tbl_ProdutosCarrinho";

    public DaoBancoCarrinho(@Nullable Context context)
    {
        super(context, "db_companyPets", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db_companyPets)
    {
        String strquery = "CREATE TABLE " +TABELA_CARRINHO+ "(" +
                "cd_Produto INTEGER primary key," +
                "cd_Categoria INTEGER not null," +
                "nm_Produto varchar(60) not null," +
                "nm_Marca varchar(40) not null," +
                "vl_Produto float not null," +
                "ds_Produto varchar(80) not null," +
                "qt_Estoque INTEGER not null," +
                "ds_Foto varchar(150) not null," +
                "qt_Produto INTEGER not null," +
                "cd_Cliente INTEGER not null," +
                "vl_TotalCompra float not null)";
        db_companyPets.execSQL(strquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    // verifica se há o produto solicitado no carrinho
    public boolean verificaProdutoCarrinho(int cd_Produto)
    {
        String comando = "SELECT * FROM " +TABELA_CARRINHO +" where cd_Produto = " +cd_Produto;
        Cursor analise = getReadableDatabase().rawQuery(comando, null);

        return analise.getCount() != 0;
    }

    // insere o produto no carrinho pela primeira vez
    public long inserirProdutoCarrinho(DtoProduto dto)
    {
        ContentValues dados = colunas(dto);

        return getWritableDatabase().insert(TABELA_CARRINHO, null, dados);
    }

    // insere a quantidade e valor total do produto
    public boolean inserirQtdEValorTtProdutoCarrinho(DtoProduto dto)
    {
        String comando = "SELECT * FROM " +TABELA_CARRINHO+ " WHERE cd_Produto = "+ dto.getCd_Produto();
        Cursor analise = getReadableDatabase().rawQuery(comando, null);
        int quantidadeCarrinho = 0;
        double valor = 0;
        if(analise.moveToNext()) {
            quantidadeCarrinho = analise.getInt(8) + 1;
            valor = analise.getDouble(4) * quantidadeCarrinho;

            ContentValues dados = new ContentValues();
            dados.put("qt_Produto", quantidadeCarrinho);
            dados.put("vl_TotalCompra", valor);

            String WhereClause = "cd_Produto = ?";
            String[] args = {dto.getCd_Produto()+""};

            getWritableDatabase().update(TABELA_CARRINHO, dados, WhereClause, args);

            return true;
        }
        else
            return false;
    }

    // consulta os itens que se encontram no carrinho
    public ArrayList<DtoProduto> consultarItensCarrinho()
    {
        String comando = "SELECT * FROM " +TABELA_CARRINHO;
        Cursor analise = getReadableDatabase().rawQuery(comando, null);
        ArrayList<DtoProduto> arraylist = new ArrayList<>();

        leituraDeDados(analise, arraylist);

        return arraylist;
    }

    // consulta a quantidade do produto no carrinho p/ fazer uma comparação com a quantidade do estoque
    public int produtoQuantidadeNoCarrinho(DtoProduto dto)
    {
        String comando = "SELECT * FROM " +TABELA_CARRINHO+ " WHERE cd_Produto = "+ dto.getCd_Produto();
        Cursor analise = getReadableDatabase().rawQuery(comando, null);
        int quantidadeCarrinho = 0;

        if (analise.getCount()>0)
            quantidadeCarrinho = analise.getInt(8);

        return quantidadeCarrinho;
    }

    // remove o respectivo item do banco e portanto da lista
    public int remover(int cd_Produto, int cd_Cliente)
    {
        String where = "cd_Produto = "+cd_Produto+" and cd_Cliente = " +cd_Cliente;
        return getReadableDatabase().delete(TABELA_CARRINHO, where, null);
    }

    // consulta o valor total dos produtos
    public double consultaValorTotal(int cd_Cliente)
    {
        String comando = "SELECT sum(vl_TotalCompra) FROM " +TABELA_CARRINHO+ " WHERE cd_Cliente = "+ cd_Cliente;
        Cursor analise = getReadableDatabase().rawQuery(comando, null);

        if(analise.moveToNext())
        return analise.getDouble(0);
        else
            return 0;
    }

    private void leituraDeDados(Cursor analise, ArrayList<DtoProduto> arraylist)
    {
        while (analise.moveToNext())
        {
            DtoProduto dto = new DtoProduto();

            dto.setCd_Produto(analise.getInt(0));
            dto.setCd_Categoria(analise.getInt(1));
            dto.setNm_Produto(analise.getString(2));
            dto.setNm_Marca(analise.getString(3));
            dto.setVl_Produto(analise.getDouble(4));
            dto.setDs_Produto(analise.getString(5));
            dto.setQt_Estoque(analise.getInt(6));
            dto.setDs_Foto(analise.getString(7));
            dto.setQt_Produto(analise.getInt(8));

            arraylist.add(dto);
        }
    }

    private ContentValues colunas(DtoProduto dto)
    {
        ContentValues dados = new ContentValues();
        dados.put("cd_Produto", dto.getCd_Produto());
        dados.put("cd_Categoria", dto.getCd_Categoria());
        dados.put("nm_Produto", dto.getNm_Produto());
        dados.put("nm_Marca", dto.getNm_Marca());
        dados.put("vl_Produto", dto.getVl_Produto());
        dados.put("ds_Produto", dto.getDs_Produto());
        dados.put("qt_Estoque", dto.getQt_Estoque());
        dados.put("ds_Foto", dto.getDs_Foto());
        dados.put("qt_Produto", dto.getQt_Produto());
        dados.put("cd_Cliente", DtoUsuario.cd_UsuLogin);
        dados.put("vl_TotalCompra", dto.getVl_Produto());

        return dados;
    }
}
