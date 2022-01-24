package com.example.appcompanypets.ui.itensPedido;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.appcompanypets.DTO.DtoCompra;
import com.example.appcompanypets.R;

import java.util.ArrayList;

public class ItensPedidoFragment extends Fragment
{
    ListView listViewCompras;
    ArrayList<DtoCompra> arrayList;

    public ItensPedidoFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_itens_pedido, container, false);

        listViewCompras = view.findViewById(R.id.listViewCompras);

        atualizarListView();

        return view;
    }

    private void atualizarListView() {
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, arrayList);

        listViewCompras.setAdapter(adapter);
    }
}