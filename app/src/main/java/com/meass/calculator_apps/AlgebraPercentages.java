package com.meass.calculator_apps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kaopiz.kprogresshud.KProgressHUD;

import es.dmoral.toasty.Toasty;

public class AlgebraPercentages extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerTextSize, spinnerTextSize1, spinnerTextSize2;
    EditText Email_Log;
    String valueFromSpinner;
    String valueFromSpinner1;
    String valueFromSpinner2;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    TextView no_of_items, total_amount, spinner4;
    String package_name, package_price, packing;
    EditText spinner1, spinner2;
    Button upgrade;
    KProgressHUD kProgressHUD;
    private ClipboardManager myClipboard;
    private ClipData myClip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algebra_percentages);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Percentages");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.ic_myarrow);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_myarrow);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        getSupportActionBar().setElevation(10.0f);
        ///find
        hintss=findViewById(R.id.hintss);
        koto_point=findViewById(R.id.koto_point);
        people=findViewById(R.id.people);
        result1=findViewById(R.id.result1);
        resulty=findViewById(R.id.resulty);


        spinnerTextSize = findViewById(R.id.spinner3);
        spinnerTextSize.setOnItemSelectedListener(this);

        String[] textSizes = getResources().getStringArray(R.array.algebraper);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.algeb, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize.setAdapter(adapter);

    }

    EditText koto_point,people,result1,resulty;
    TextView hintss;
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
    }

    @Override
    public boolean onNavigateUp() {
        startActivity(new Intent(getApplicationContext(),UnitConverter.class));
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner3) {
            valueFromSpinner = parent.getItemAtPosition(position).toString();
            if (valueFromSpinner.equals("Discount")) {
                hintss.setText("A - B% = X");
                if (TextUtils.isEmpty(koto_point.getText().toString())||TextUtils.isEmpty(people.getText().toString())) {
                    Toasty.info(getApplicationContext(),"A এবং B লিখুন",Toasty.LENGTH_SHORT,true).show();
                    return;
                }
                else {
                    float percentage = Float.parseFloat(people.getText().toString().toString());
                    float dec = percentage / 100;
                    float total = dec * Float.parseFloat(koto_point.getText().toString());
                    float main=Float.parseFloat(koto_point.getText().toString())-total;
                    result1.setText("X = "+main);
                    resulty.setText("Y = "+total);
                    resulty.setVisibility(View.VISIBLE);

                  /*
                    float percentage = Float.parseFloat(people.getText().toString().toString());
                    float dec = percentage / 100;
                    float total = dec * Float.parseFloat(people.getText().toString());
                    float main=Float.parseFloat(koto_point.getText().toString())-total;
                    result1.setText(""+main);
                    resulty.setText(""+total);
                    Toast.makeText(this, "ghgh", Toast.LENGTH_SHORT).show();
                   */
                }

            }
            else  if (valueFromSpinner.equals("Increase")) {
                hintss.setText("A + B% = X");
                float percentage = Float.parseFloat(people.getText().toString().toString());
                float dec = percentage / 100;
                float total = dec * Float.parseFloat(koto_point.getText().toString());
                float main=Float.parseFloat(koto_point.getText().toString())+total;
                result1.setText("X = "+main);
                resulty.setText("Y = "+total);
                resulty.setVisibility(View.VISIBLE);
            }
            else  if (valueFromSpinner.equals("Simple Percentage")) {
                hintss.setText("A * B% = X");
                float percentage = Float.parseFloat(people.getText().toString().toString());
                float dec = percentage / 100;
                float total = dec * Float.parseFloat(koto_point.getText().toString());
                float main=Float.parseFloat(koto_point.getText().toString())+total;
                result1.setText("X = "+total);
                resulty.setText("Y = "+total);
                resulty.setVisibility(View.GONE);
            }
            else  if (valueFromSpinner.equals("Increase/Decrease")) {
                hintss.setText("A → B = X%");
                float percentage = Float.parseFloat(people.getText().toString().toString());
                float dec = percentage * 100;
                float first=dec/(Float.parseFloat(koto_point.getText().toString()));
                float main=first-100;
                result1.setText("X = "+main);
                resulty.setVisibility(View.GONE);
            }
            else  if (valueFromSpinner.equals("Percentage of A from B")) {
                hintss.setText("A ← B = X%");
                float a=Float.parseFloat(koto_point.getText().toString())*100;
                float second=a/Float.parseFloat(people.getText().toString());
                result1.setText("X = "+second+" %");
                resulty.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}