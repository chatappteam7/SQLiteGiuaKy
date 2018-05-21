package com.example.cr7.sqlitegiuaky;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.cr7.Fragment.FindExpert_Fragment;


public class ContainerActivity extends AppCompatActivity {
    ImageView imgMenu;
    ImageView imgAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        getSupportActionBar().hide();
        addControls();
        addEvents();
    }

    private void addEvents() {

    }

    private void addControls() {
        imgMenu =findViewById(R.id.imgMenu);
        imgAvatar = findViewById(R.id.imgAvatar);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FindExpert_Fragment findExpert_fragment = new FindExpert_Fragment();
        fragmentTransaction.replace(R.id.layout_container,findExpert_fragment,"find_expert");
        fragmentTransaction.addToBackStack("find_expert");
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>1){
            getFragmentManager().popBackStack("find_expert",0);
        }else {
            finish();
        }


    }

}
