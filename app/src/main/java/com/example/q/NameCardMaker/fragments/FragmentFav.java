package com.example.q.NameCardMaker.fragments;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.q.NameCardMaker.MainActivity;
import com.example.q.NameCardMaker.R;


public class FragmentFav extends Fragment {
    private static final String name_key = "name";
    private static final String number_key = "number";
    private static final String link_key = "link";

    private View v;
    private String name;
    private String number;
    private String link;

    public FragmentFav() {

    }

    public static Fragment newInstance(String link_parameter,String name_parameter,String number_parameter) {
        FragmentFav fragment = new FragmentFav();
        Bundle args = new Bundle();
        args.putString(link_key, link_parameter);
        args.putString(name_key, name_parameter);
        args.putString(number_key,number_parameter);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            link = getArguments().getString(link_key);
            name = getArguments().getString(name_key);
            number = getArguments().getString(number_key);
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_fav, container, false);
        TextView view_name =(TextView) v.findViewById(R.id.name);
        TextView view_number =(TextView) v.findViewById(R.id.number);
        Button button = (Button)v.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity activity = (MainActivity)getActivity();
                activity.refresh();
            }
        });
        Bitmap bm = BitmapFactory.decodeFile(link);
        Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bm,500,500);

        ImageView imageView = (ImageView) v.findViewById(R.id.picture);
        imageView.setImageBitmap(thumbnail);
        view_name.setText(name);
        view_number.setText(number);
        return v;
    }

}
