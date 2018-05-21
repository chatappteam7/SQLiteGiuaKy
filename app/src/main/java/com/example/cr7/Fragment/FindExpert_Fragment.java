package com.example.cr7.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cr7.Adapter.AdapterExpert;
import com.example.cr7.Model.Expert;
import com.example.cr7.database.DBManager;
import com.example.cr7.sqlitegiuaky.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CR7 on 4/2/2018.
 */

public class FindExpert_Fragment extends Fragment {
    private List<Expert> listExpert;
    private RecyclerView rvExpert;
    private AdapterExpert adapterExpert;
    DBManager dbManager;
    SQLiteDatabase sqLiteDatabase = null;
    FloatingActionButton fab;
    //EditText txtSearch;
    //ImageView imgSearch;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_find_expert, container, false);
        addControls(view);
        addEvents();
        return view;
    }

    private void addEvents() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                NewExpert_Fragment newExpert_fragment = new NewExpert_Fragment();
                fragmentTransaction.replace(R.id.layout_container,newExpert_fragment,"new_expert");
                fragmentTransaction.addToBackStack("new_expert");
                fragmentTransaction.commit();
            }
        });

    }

    private void addControls(View view) {
        dbManager = new DBManager(getActivity());
        fab =  view.findViewById(R.id.fab);
        listExpert = new ArrayList<>();

        //txtSearch = view.findViewById(R.id.txtSearch);
        //imgSearch = view.findViewById(R.id.imgSearch);
        rvExpert = view.findViewById(R.id.rvExpert);
        listExpert = new ArrayList<>();
//        listExpert.add(new Expert("duyhuy@gmail.com", "123", "Huy", " Duy", "0169862357", "Mobile Developer App Android", "Việt Nam", "http://cdni.condenast.co.uk/320x480/d_f/Emma-Watson-close-up-Vogue-28Aug15-Getty_b_320x480.jpg", 1, 10.847588, 106.775818));
//        listExpert.add(new Expert("xuanhuong@gmail.com", "123", "Huong", " To", "0167777999", "Mobile Developer", "Việt Nam", "http://www.elle.vn/wp-content/uploads/2015/10/30/emma-stone.jpg", 1, 10.847588, 106.775818));
//        listExpert.add(new Expert("khoanguyen@gmail.com", "123", "Khoa", " Nguyen", "0163987669", "AI", "Việt Nam", "https://genknews.genkcdn.vn/k:thumb_w/640/2015/a-1436946541628/truyen-tranh-naruto-gaara-sasuke-hai-cuoc-doi-mot-noi-dau.png", 0, 10.847588, 106.775818));
//        listExpert.add(new Expert("trinhtran@gmail.com", "123", "Tran", " Trinh", "0169862357", "Nodejs", "Việt Nam", "https://cdn1.thr.com/sites/default/files/2017/03/chris_evans_captain_america_uk_getty_h_2016.jpg", 0, 10.847588, 106.775818));
//        listExpert.add(new Expert("naruto@gmail.com", "123", "Naruto", " Oz", "0162657789", "Ninja", "Japan", "https://pbs.twimg.com/media/DX1Rd1xUMAABmVm.jpg", 0, 10.847588, 106.775818));
        listExpert = dbManager.getAllExpert();
        adapterExpert = new AdapterExpert(listExpert, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvExpert.setLayoutManager(mLayoutManager);
        rvExpert.setAdapter(adapterExpert);

    }


}
