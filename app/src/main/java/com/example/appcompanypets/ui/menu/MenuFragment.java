package com.example.appcompanypets.ui.menu;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Slide.SlideAdapter;

public class MenuFragment extends Fragment
{
    ViewPager viewPager;
    SlideAdapter slideAdapter;
    DtoUsuario dto = new DtoUsuario();
    private FragmentActivity context;

    public MenuFragment(DtoUsuario dto)
    {
        this.dto = dto;
    }

    public MenuFragment()
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
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        int imagens[] = {
                R.drawable.imgboasvindas1,
                R.drawable.imgboasvindas2,
                R.drawable.imgboasvindas3,
                R.drawable.imgboasvindas4,
                R.drawable.imgboasvindas5
        };

        int descricao[] = {
                R.string.descricaoBoasvindas1,
                R.string.descricaoBoasvindas2,
                R.string.descricaoBoasvindas3,
                R.string.descricaoBoasvindas4,
                R.string.descricaoBoasvindas5
        };

        viewPager = view.findViewById(R.id.viewPagerMenu);
        slideAdapter = new SlideAdapter(context, imagens, descricao, R.layout.slide_adapter);

        viewPager.setAdapter(slideAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        Toast.makeText(context, "O seu código é: " +dto.getCd_Usuario(), Toast.LENGTH_SHORT).show();

        return view;
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position)
        {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}