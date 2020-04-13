package com.academicbot.sahayaka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;

public class farmers_form extends AppCompatActivity {
Button b6;
EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13;
     String name="",phone="",age="",item1="",quant1="",price1="",item2="",quant2="",price2="",item3="",quant3="",price3="",apmc_code="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmers_form);

        //initializing UI components
        b6=findViewById(R.id.b6);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        e5=findViewById(R.id.e5);
        e6=findViewById(R.id.e6);
        e7=findViewById(R.id.e7);
        e8=findViewById(R.id.e8);
        e9=findViewById(R.id.e9);
        e10=findViewById(R.id.e10);
        e11=findViewById(R.id.e11);
        e12=findViewById(R.id.e12);
        e13=findViewById(R.id.e13);

        final Date date=new Date();
        //text from edit text

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=e1.getText().toString();
                phone=e2.getText().toString();
                age=e3.getText().toString();
                item1=e4.getText().toString();
                quant1=e5.getText().toString();
                price1=e6.getText().toString();
                item2=e7.getText().toString();
                quant2=e8.getText().toString();
                price2=e9.getText().toString();
                item3=e10.getText().toString();
                quant3=e11.getText().toString();
                price3=e12.getText().toString();
                apmc_code=e13.getText().toString();

                if(e1.getText().toString().isEmpty()||e2.getText().toString().isEmpty()
                        ||e3.getText().toString().isEmpty()||e4.getText().toString().isEmpty()
                        ||e5.getText().toString().isEmpty()||e6.getText().toString().isEmpty()||e13.getText().toString().isEmpty()
                        ){
                    Toast.makeText(getApplicationContext(),"Fill all the * marked field(s)",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!e7.getText().toString().isEmpty()){
                    if(e8.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Fill the quantity of 2nd item",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(e9.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Fill the Price per KG of 2nd item",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(!e10.getText().toString().isEmpty()){
                    if(e11.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Fill the quantity of 3nd item",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(e12.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(),"Fill the Price per KG of 3nd item",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date d=new Date();
                String ts=format.format(d);


                DatabaseReference dref=FirebaseDatabase.getInstance().getReference("Farmers_Form").child(ts);

                Farmers_modelClass f=new Farmers_modelClass(name,phone,age,item1,quant1,price1,item2,quant2,price2,item3,quant3,price3,apmc_code);
                dref.setValue(f);

                Toast.makeText(getApplicationContext(),"Successfully submitted",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(farmers_form.this,home.class));


            }
        });
    }
}
