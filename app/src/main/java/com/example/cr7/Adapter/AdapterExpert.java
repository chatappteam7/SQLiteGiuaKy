package com.example.cr7.Adapter;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cr7.Fragment.Appointment_Fragment;
import com.example.cr7.Fragment.EditInfo_Fragment;
import com.example.cr7.Model.Expert;
import com.example.cr7.database.DBManager;
import com.example.cr7.sqlitegiuaky.R;

import java.util.List;

/**
 * Created by CR7 on 3/12/2018.
 */

public class AdapterExpert extends RecyclerView.Adapter<AdapterExpert.ExpertViewHolder>{
    private List<Expert> listExpert;
    private Context context;

    public AdapterExpert(List<Expert> listExpert, Context context) {
        this.listExpert = listExpert;
        this.context = context;
    }

    @Override
    public AdapterExpert.ExpertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expert,parent,false);
        return new ExpertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterExpert.ExpertViewHolder holder, int position) {
        Expert expert = listExpert.get(position);
        holder.txtName.setText(expert.getfName()+" "+expert.getlName());
        holder.txtCareer.setText(expert.getCareer());
        holder.txtCountry.setText(expert.getCountry());
        if(expert.getIsOnline()==1){
            holder.imgOnOff.setImageResource(R.drawable.icon_online);
            holder.txtOnOff.setText("Online");
        }else{
            holder.imgOnOff.setImageResource(R.drawable.icon_offline);
            holder.txtOnOff.setText("Offline");
        }

        Glide.with(context)
                .load(expert.getImage())
                .into(holder.imgAvatar);

        holder.imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateAppointment();
            }
        });

    }

    private void dateAppointment() {
        FragmentTransaction ft = ((Activity) context).getFragmentManager().beginTransaction();
        Fragment prev = (((Activity) context).getFragmentManager()).findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new Appointment_Fragment();
        dialogFragment.show(ft, "dialog");
    }

    @Override
    public int getItemCount() {
        return listExpert.size();
    }
    public class ExpertViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,View.OnClickListener{
        private TextView txtName,txtCareer,txtCountry,txtOnOff;
        private ImageView imgAvatar,imgOnOff,imgDate;
        public ExpertViewHolder(View itemView) {
            super(itemView);
            txtName= itemView.findViewById(R.id.txtName);
            txtCareer= itemView.findViewById(R.id.txtCareer);
            txtCountry= itemView.findViewById(R.id.txtCountry);
            txtOnOff= itemView.findViewById(R.id.txtOnOff);
            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            imgOnOff = itemView.findViewById(R.id.imgOnOff);
            imgDate = itemView.findViewById(R.id.btnDate);
            itemView.setOnLongClickListener( this);
            itemView.setOnClickListener( this);

        }
        @Override
        public boolean onLongClick(View view) {
            Expert expert = listExpert.get(this.getAdapterPosition());
            myLongClickItem(expert);
            return true;
        }

        @Override
        public void onClick(View view) {
//            DBManager dbManager = new DBManager(context);
//            dbManager.addExpert(listExpert.get(getAdapterPosition()));
            Expert expert = listExpert.get(this.getAdapterPosition());
            Bundle bundle =new Bundle();
            bundle.putSerializable("expert",expert);
            FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            EditInfo_Fragment editInfo_fragment = new EditInfo_Fragment();
            editInfo_fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.layout_container,editInfo_fragment,"edit");
            fragmentTransaction.addToBackStack("edit");
            fragmentTransaction.commit();

        }

    }

    private void myLongClickItem(final Expert expert) {


        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Delete Expert")
                .setMessage("Are you sure you want to delete this Expert?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DBManager dbManager = new DBManager(context);
                        dbManager.deleteExpert(expert.getIdExpert());
                        listExpert= dbManager.getAllExpert();
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();



    }
}

