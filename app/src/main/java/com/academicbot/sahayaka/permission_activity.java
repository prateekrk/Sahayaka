package com.academicbot.sahayaka;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class permission_activity extends AppCompatActivity {
Button b7,b8,b9,b10;
Spinner s1,s2;
EditText e14,e15,e16,e17,e18,e19;
String gender[]={"Don't want to disclose","Male","Female","Others"};
String answer[]={"Yes","No"};
int myear,mMonth,mDay,Mhour,mMinute;
String name,phone,age,g,ans,visiting_addr,purpose,out_date,out_time,ret_time,email;
Calendar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_activity);

        //initialising UI components
        b7=findViewById(R.id.b7);
        b8=findViewById(R.id.b8);
        b9=findViewById(R.id.b9);
        b10=findViewById(R.id.b10);

        s1=findViewById(R.id.spinner);
        s2=findViewById(R.id.spinner1);

        e14=findViewById(R.id.e14);
        e15=findViewById(R.id.e15);
        e16=findViewById(R.id.e16);
        e17=findViewById(R.id.e17);
        e18=findViewById(R.id.e18);
        e19=findViewById(R.id.e19);

        //adding string adapter to spinner
        ArrayAdapter<String> a=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,gender);
        s1.setAdapter(a);

        ArrayAdapter<String> a1=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,answer);
        s2.setAdapter(a1);

        c=Calendar.getInstance();
        myear=c.get(Calendar.YEAR);
        mMonth=c.get(Calendar.MONTH);
        mDay=c.get(Calendar.DAY_OF_MONTH);
        mMinute=c.get(Calendar.MINUTE);
        Mhour=c.get(Calendar.HOUR);

        //outgoing date picker
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              DatePickerDialog d=new DatePickerDialog(permission_activity.this, new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                      b7.setText(i+"/"+i1+"/"+i2);
                  }
              },myear,mMonth,mDay+1);
              d.show();
            }
        });

        //outgoing time
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog t=new TimePickerDialog(permission_activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        b8.setText(i+":"+i1);
                    }
                },Mhour,mMinute,true);
                t.show();
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog t=new TimePickerDialog(permission_activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        b9.setText(i+":"+i1);
                    }
                },Mhour,mMinute,true);
                t.show();
            }
        });
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                g=gender[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ans=answer[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ans=answer[0];
            }
        });

        //submit
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e14.getText().toString().isEmpty()){
                    e14.setError("Compulsory field");
                    return;
                }
                if(e15.getText().toString().isEmpty()){
                    e15.setError("Compulsory field");
                    return;
                } if(e16.getText().toString().isEmpty()){
                    e16.setError("Compulsory field");
                    return;
                } if(e17.getText().toString().isEmpty()){
                    e17.setError("Compulsory field");
                    return;
                } if(e18.getText().toString().isEmpty()){
                    e18.setError("Compulsory field");
                    return;
                } if(e19.getText().toString().isEmpty()){
                    e19.setError("Compulsory field");
                    return;
                }
                name=e14.getText().toString();
                phone=e15.getText().toString();
                age=e16.getText().toString();
                visiting_addr=e17.getText().toString();
                purpose=e18.getText().toString();
                email=e19.getText().toString();
                out_date=b7.getText().toString();
                out_time=b8.getText().toString();
                ret_time=b9.getText().toString();
                SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date d=new Date();
                String ts=format.format(d);
                String t=ts+e15.getText().toString();
                DatabaseReference x=FirebaseDatabase.getInstance().getReference("Permission").child(t);
                permission_modelClass p=new permission_modelClass(name,phone,age,g,ans,visiting_addr,purpose,out_date,out_time,ret_time,email);
                x.setValue(p);
                Toast.makeText(getApplicationContext(),"Permission is submitted",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(permission_activity.this,home.class));
            }
        });
    }
}
