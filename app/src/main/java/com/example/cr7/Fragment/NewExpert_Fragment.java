package com.example.cr7.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cr7.Model.Expert;
import com.example.cr7.database.DBManager;
import com.example.cr7.sqlitegiuaky.R;

/**
 * Created by CR7 on 4/11/2018.
 */

public class NewExpert_Fragment extends Fragment {

    ImageView imgAva;
    EditText txtFirstName,txtLastName,txtEmail,txtSDT, txtCareer,txtCountry;
    String strAvatar;
    Button btnSave;
    DBManager dbManager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.expert_info,container,false);
        addControls(view);
        addEvents();
        return view;
    }

    private void addControls(View view) {
        dbManager = new DBManager(getActivity());
        btnSave= view.findViewById(R.id.btnSave);
        imgAva = view.findViewById(R.id.imgSUAvatar);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtFirstName = view.findViewById(R.id.txtFName);
        txtLastName = view.findViewById(R.id.txtLName);
        txtCareer = view.findViewById(R.id.txtCareer);
        txtCountry = view.findViewById(R.id.txtCountry);
        txtSDT = view.findViewById(R.id.txtSdt);

    }

    private void addEvents() {
        imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(getActivity(), imgAva);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_avatar, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        //Toast.makeText(ContainerActivity.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        if(item.getItemId()==R.id.dakota){
                            strAvatar="https://znews-photo-td.zadn.vn/w960/Uploaded/mdf_drkydd/2017_04_12/8up.jpg";

                        }else if(item.getItemId()==R.id.eva){
                            strAvatar = "https://media.wmagazine.com/photos/58540202c7188f9b26c951c5/2:3/w_640/0816.w.MM_.eva_.lo30_View5-copy-copy_RGB.jpg";

                        }else if(item.getItemId()==R.id.scarlet){
                            strAvatar ="http://hot97svg.com/wp-content/uploads/2018/01/Scarlett-Johansson-2014-1170x658.jpg";

                        }else if(item.getItemId()==R.id.olsen){
                            strAvatar = "https://i-giaitri.vnecdn.net/2016/05/06/elizabeth-olsen-01-1462524673_680x0.jpg";

                        }else if(item.getItemId()==R.id.taylor){
                            strAvatar ="https://wallpapersmug.com/large/8ef669/sunglasses_taylor_swift_white_dress.jpg";
                        }

                        Glide.with(getActivity()).load(strAvatar).into(imgAva);
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idExpert = txtEmail.getText().toString() ;
                String password = "123";
                String lName= txtLastName.getText().toString();
                String fName= txtFirstName.getText().toString();
                String sdt= txtSDT.getText().toString();
                String career= txtCareer.getText().toString();
                String country= txtCountry.getText().toString();
                String image= strAvatar;
                int isOnline= 1;
                double lat= 10.847588;
                double lon= 106.775818;
                Expert expert= new Expert(idExpert,password,lName,fName,sdt,career,country,image,isOnline,lat,lon);
                dbManager.addExpert(expert);
                getFragmentManager().popBackStackImmediate();

            }
        });
    }


}
