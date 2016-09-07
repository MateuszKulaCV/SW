package com.example.maticzok.sw;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maticzok.sw.Model.User;
import com.example.maticzok.sw.Options.OptionsActivity;

/**
 * Created by Maticzok on 2016-07-09.
 */
public class UserInfoDF extends DialogFragment {
    TextView Username,Email,Number;
    ImageView userImage;
    Button PostBtn,AlbumBtn;
    User user;

    public UserInfoDF()
    {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userinfo_dialog,container,false);
        Username = (TextView) view.findViewById(R.id.username);
        Email = (TextView) view.findViewById(R.id.email);
        Number = (TextView) view.findViewById(R.id.numer);
        userImage = (ImageView) view.findViewById(R.id.userimage);
        PostBtn = (Button) view.findViewById(R.id.PostBtn);
        AlbumBtn = (Button) view.findViewById(R.id.AlbBtn);
        Username.setText(user.getName());
        Email.setText(user.getEmail());
        Number.setText(user.getPhone());

        getDialog().setTitle("");
        PostBtn.setOnClickListener(PostListener);
        AlbumBtn.setOnClickListener(AlbListener);
        return view;
    }

    private View.OnClickListener PostListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ((OptionsActivity)getActivity()).getUserPost(user);
            dismiss();
        }
    };

    private View.OnClickListener AlbListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ( (OptionsActivity)getActivity()).getUserAlbum(user);
            dismiss();
        }
    };

    public void setUser(User user){this.user = user;}

}
