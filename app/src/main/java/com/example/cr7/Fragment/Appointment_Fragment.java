package com.example.cr7.Fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.cr7.sqlitegiuaky.R;


/**
 * Created by CR7 on 4/6/2018.
 */

public class Appointment_Fragment extends DialogFragment {
    Button btnOk;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_appointment, container, false);
        // Do all the stuff to initialize your custom view
        btnOk= v.findViewById(R.id.btnOK);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Đặt lịch hẹn thành công!!!", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return v;
    }
}