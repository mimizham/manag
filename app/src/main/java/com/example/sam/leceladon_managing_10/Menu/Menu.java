package com.example.sam.leceladon_managing_10.Menu;
import com.example.sam.leceladon_managing_10.Inventaire.*;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.sam.leceladon_managing_10.Inventaire.New2Activity;
import com.example.sam.leceladon_managing_10.MainActivity;
import com.example.sam.leceladon_managing_10.R;

public class Menu extends AppCompatActivity {

        RelativeLayout r ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        r= findViewById(R.id.relativeLInventory);
        r.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent1 = new Intent(Menu.this, New2Activity.class);
                // intent.putExtra("response", response);
                startActivity(intent1);

                System.out.print("in");

            }
        });
    }



}
