package com.dowellcomputer.fcmexample.fcmexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class Menu_Adapter extends PagerAdapter {
    private int[] images = {R.drawable.q_menu};
    private LayoutInflater inflater;
    private Context context;

    public Menu_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_menu_slider, container , false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView4);
        imageView.setImageResource(images[position]);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container,int position, Object object){
        container.invalidate();
    }
}
