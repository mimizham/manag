package com.example.sam.leceladon_managing_10.Inventaire;

import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sam.leceladon_managing_10.Leceladon;
import com.example.sam.leceladon_managing_10.NewActivity;
import com.example.sam.leceladon_managing_10.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class New2Activity extends AppCompatActivity implements  View.OnClickListener{
  // DatePicker dpk; implements AdapterView.OnItemSelectedListener
    EditText lblinv;
    EditText lblquan;
    EditText type;
    EditText etatpro,daterenv;
    TextView txt8,txt9;
    Button btn,bt;
    final String lbinv="";
    ArrayList<String> prod;
   String var;
    final String url = "http://work.le-celadon.ma/Managing_Celadon/Inventaires";
    String etatprod;
    String ty;
    String quan;

    private int mYear, mMonth, mDay, mHour, mMinute;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new2);
        prod = new ArrayList<String>();
      /*  dpk = findViewById(R.id.etrenv);
        dpk.setSpinnersShown(false);
*/
        btn = findViewById(R.id.newInv);
        final TextView txtc = findViewById(R.id.textView5);
        lblinv = findViewById(R.id.txtlib);
        lblquan = findViewById(R.id.txtquan);
        btn=findViewById(R.id.newInv);
     //   type = findViewById(R.id.ettypepro);
     //   etatpro = findViewById(R.id.ettetatpro);
        daterenv = findViewById(R.id.etrenv);
     //   mySpinner = findViewById(R.id.SPlibprod);
        txt8 = findViewById(R.id.textView8);
        txt9 = findViewById(R.id.textView9);
        bt=findViewById(R.id.retour);
        // final String lbinv = lblinv.getText().toString();
        /*final String quan = lblquan.getText().toString();
        final String ty = type.getText().toString();
        final String etatprod = etatpro.getText().toString();
*/    daterenv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(New2Activity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                    var=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            //    daterenv.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(New2Activity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                     var=var+hourOfDay + ":" + minute;
                               txt9.setText(var);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show(); /* */
            }});
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final RequestQueue queue = Volley.newRequestQueue(New2Activity.this);
                final StringRequest insertReq = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(final String newsinv) {

                                System.out.print(newsinv);

                                   try {
                                        //Parsing the fetched Json String to JSON Object
                                        JSONArray j = new JSONArray(newsinv);
                                        System.out.println(j);
                                        JSONObject jsonobject = j.getJSONObject(0);
                                        System.out.println("jsonobject" + jsonobject);
                                        String result = "";
                                        JSONObject Data = new JSONObject();

                                        //Storing the Array of JSON String to our JSON Array
                                        for (int i = 0; i < jsonobject.length(); i++) {
                                            try {
                                                //Getting json object
                                                result = jsonobject.getString("libelle_produit");
                                              //  spinnerData.put("libelle_produit", result);
                                                System.out.println(Data);

                                            }
                                            catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        //Calling method getStudents to get the students from the JSON Array
                                       // getproduitName(spinnerData);
                                    }
                                    catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }/* */
                                }

                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast toast = Toast.makeText(getApplicationContext()
                                , "" + error, Toast.LENGTH_LONG);
                        toast.show();
                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> insert = new HashMap<String, String>();
                        insert.put("libelle_produit", lbinv);
                        insert.put("quantite", quan);
                        insert.put("telphone", ty);
                        insert.put("emailcld", etatprod);
                        return insert;
                    }

                };
                //10000 is the time in milliseconds adn is equal to 10 sec
                insertReq.setRetryPolicy(new DefaultRetryPolicy(
                        3000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                queue.add(insertReq);
            }

        });
    }

    @Override
    public void onClick(View view) {

    }
}
  /*  @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
     //prendre la valeur choisiez par user ou bien saisie
    lblinv.setText(getText(i));
  //  System.out.println(""+lbinv.setText(getText(i)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getproduitName(JSONObject j) {

        //Traversing through all the items in the json array
      for (int i = 0; i < j.length(); i++) {
            try {
                //Getting json object

                prod.add(String.valueOf(j.getString("libelle_produit")));

            } catch (Exception e) {
                e.printStackTrace();
            }} /*   JSONObject json = j.getJSONObject("libelle_produit");
                System.out.println(json);
       mySpinner.setAdapter(new ArrayAdapter<String>(New2Activity.this,android.R.layout.simple_dropdown_item_1line,prod));
        } ;

    //remplir le spinner pour afficher les donnes

}*/



