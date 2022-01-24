package com.example.appcompanypets.ui.menu;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcompanypets.DTO.DtoUsuario;
import com.example.appcompanypets.R;
import com.example.appcompanypets.Slide.SlideAdapter;

public class MenuFragment extends Fragment
{
    ViewPager viewPager;
    SlideAdapter slideAdapter;
    private FragmentActivity context;
    
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
                R.drawable.imgmenuinicial1,
                R.drawable.imgmenuinicial2,
                R.drawable.imgmenuinicial3
        };

        int descricao[] = {
                R.string.descricaoBoasvindas1,
                R.string.descricaoBoasvindas2,
                R.string.descricaoBoasvindas4
        };

        viewPager = view.findViewById(R.id.viewPagerMenu);
        slideAdapter = new SlideAdapter(context, imagens, descricao, R.layout.slide_adapter);

        viewPager.setAdapter(slideAdapter);
        viewPager.addOnPageChangeListener(viewListener);

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