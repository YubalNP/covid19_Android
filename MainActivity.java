package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private String url = "https://coronavirus-19-api.herokuapp.com/countries/";
    private static String TAG = MainActivity.class.getSimpleName();
    TextView c1,c2,c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RequestQueue queue = Volley.newRequestQueue(this);
        //
         c1 = findViewById(R.id.textView3);
         c2 = findViewById(R.id.textView4);
         c3 = findViewById(R.id.textView5);
        //
        final Spinner List = findViewById(R.id.listItem);
        Button Submit = findViewById(R.id.submit);
        Button cls = findViewById(R.id.button3);
        cls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1.setText("");
                c2.setText("");
                c3.setText("");
            }
        });
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n_url = url+""+List.getSelectedItem();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, n_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        c1.setText("");
                        c2.setText("");
                        c3.setText("");

                        try{
                            JSONObject js = new JSONObject(response);

                            String c = js.getString("cases");
                            String d = js.getString("deaths");
                            String r = js.getString("recovered");

                            c1.append(c);
                            c2.append(r);
                            c3.append(d);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                queue.add(stringRequest);
            }
        });
//
    }
}