package com.example.appcompanypets.ui.veterinarios;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.appcompanypets.DAO.DaoVeterinarios;
import com.example.appcompanypets.DTO.DtoVeterinarios;
import com.example.appcompanypets.R;
import com.example.appcompanypets.RecyclerItemClickListener;
import com.example.appcompanypets.RecyclerViewAdapter;

import java.util.ArrayList;

public class VeterinariosFragment extends Fragment
{
    RecyclerView recyclerViewVeterinario;
    ArrayList<DtoVeterinarios> arrayList = new ArrayList<>();
    DaoVeterinarios daoContato = new DaoVeterinarios(getActivity());
    DtoVeterinarios dto;

    public VeterinariosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_veterinarios, container, false);

        recyclerViewVeterinario = view.findViewById(R.id.recyclerViewVeterinario);

        arrayList = daoContato.consultarTodos();

        try {
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(null, 3, R.layout.veterinario_adapter, arrayList);

            recyclerViewVeterinario.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewVeterinario.setAdapter(adapter);

            recyclerViewVeterinario.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerViewVeterinario, new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }

                @Override
                public void onLongItemClick(View view, int position) {
                    dto = arrayList.get(position);
                    registerForContextMenu(recyclerViewVeterinario);
                }

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            }));
        }
        catch (Exception e){
            Log.d("Errofrag", e.toString());
        }



        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,0,0, "Ligar");
    }

    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        if(item.getItemId()==0)
        {
            Intent ligar = new Intent(Intent.ACTION_DIAL);
            ligar.setData(Uri.parse("tel:"+ dto.getTelefone()));
            startActivity(ligar);
        }
        return super.onContextItemSelected(item);
    }
}