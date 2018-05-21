package com.example.cr7.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.cr7.Model.Expert;

import java.util.ArrayList;

/**
 * Created by CR7 on 4/15/2018.
 */

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="ListExpert";
    private static final String TABLE_NAME ="expert";
    private static final String ID_EXPERT ="id_expert";
    private static final String PASSWORD ="password";
    private static final String LNAME ="lname";
    private static final String FNAME ="fname";
    private static final String SDT ="sdt";
    private static final String CARRER ="career";
    private static final String COUNTRY ="country";
    private static final String IMAGE ="image";
    private static final String ISONLINE ="isonline";
    private static final String LAT ="lat";
    private static final String LON ="lon";

    private Context context;



    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID_EXPERT +" TEXT PRIMARY KEY, "+
                PASSWORD + " TEXT, "+
                LNAME +" TEXT, "+
                FNAME+" TEXT," +
                SDT+" TEXT," +
                CARRER+" TEXT," +
                COUNTRY+" TEXT," +
                IMAGE+" TEXT," +
                ISONLINE+" INTEGER," +
                LAT+" DOUBLE," +
                LON +" DOUBLE)";
        sqLiteDatabase.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

    //Add new a Expert
    public void addExpert(Expert expert){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_EXPERT, expert.getIdExpert());
        values.put(PASSWORD, expert.getPassword());
        values.put(LNAME, expert.getlName());
        values.put(FNAME, expert.getfName());
        values.put(SDT, expert.getSdt());
        values.put(CARRER, expert.getCareer());
        values.put(COUNTRY, expert.getCountry());
        values.put(IMAGE, expert.getImage());
        values.put(ISONLINE, expert.getIsOnline());
        values.put(LAT, expert.getLat());
        values.put(LON, expert.getLon());

        long row = db.insert(TABLE_NAME, null, values);
        if(row<0){
            Toast.makeText(context, "Can't insert " + expert.getfName(), Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Insert succesfull!!! " + expert.getfName(), Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public ArrayList<Expert> getAllExpert(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        ArrayList<Expert> arrExpert= new ArrayList<>();
        while(cursor.moveToNext()){
             String idExpert = cursor.getString(0);
             String password = cursor.getString(1);
             String lName= cursor.getString(2);
             String fName= cursor.getString(3);
             String sdt= cursor.getString(4);
             String career= cursor.getString(5);
             String country= cursor.getString(6);
             String image= cursor.getString(7);
             Integer isOnline= cursor.getInt(8);
             double lat= cursor.getDouble(9);
             double lon= cursor.getDouble(10);
            Expert expert = new Expert(idExpert,password,lName,fName,sdt,career,country,image,isOnline,lat,lon);
            arrExpert.add(expert);
        }
        cursor.close();
        return arrExpert;
    }
   public void updateExpert(Expert expert, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID_EXPERT, expert.getIdExpert());
        values.put(PASSWORD, expert.getPassword());
        values.put(LNAME, expert.getlName());
        values.put(FNAME, expert.getfName());
        values.put(SDT, expert.getSdt());
        values.put(CARRER, expert.getCareer());
        values.put(COUNTRY, expert.getCountry());
        values.put(IMAGE, expert.getImage());
        values.put(ISONLINE, expert.getIsOnline());
        values.put(LAT, expert.getLat());
        values.put(LON, expert.getLon());
        try{
            long row= db.update(TABLE_NAME,values,ID_EXPERT +"=?",new String[]{id});
            Toast.makeText(context, "Update Thành Công !!!!" + expert.getfName(), Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(context, "Không thể update! because: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        db.close();

    }
    public void deleteExpert(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            long row= db.delete(TABLE_NAME, ID_EXPERT + " = ?", new String[] { id });
            Toast.makeText(context, "Xóa Thành Công !!!!" , Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(context, "Không thể xóa, vì : " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        db.close();
    }


}
