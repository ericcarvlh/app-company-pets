package com.example.appcompanypets.Slides;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.appcompanypets.R;


public class SlideAdapter extends PagerAdapter
{
    Context context;
    int layout;
    int[] imagens;
    int[] descricao;

    public SlideAdapter(Context context, int[] imagens, int[] descricao, int layout)
    {
        this.context = context;
        this.imagens = imagens;
        this.descricao = descricao;
        this.layout = layout;
    }

    @Override
    public int getCount()
    {
        return  imagens.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, container,false);

        ImageView slideImagem = view.findViewById(R.id.imageViewSlide);
        TextView slideDescricao = view.findViewById(R.id.textViewDescricao);

        slideImagem.setImageResource(imagens[position]);
        slideDescricao.setText(descricao[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((ConstraintLayout)object);
    }
}
