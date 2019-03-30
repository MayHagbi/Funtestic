package com.example.funtestic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {

    // only to test1!!!!!!!!
    private String parent ;
    private List<String> childs = new ArrayList<String>();

   //!!!! !!!!!!!!!!!!!!!!!!!!!!

    private Spinner chooseChild;
    private TextView parentName;
    private TextView report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //for test!!!!!!
        parent="סמי";
        childs.add("יוני");
        childs.add("שלומי");
        childs.add("יואל");
        childs.add("ראובן");
        childs.add("גלעד");
        childs.add("מאי");
        //!!!! !!!!!!!!!!!!!!!!!!!!!!

        chooseChild = (Spinner) findViewById(R.id.reportChooseChildSpinner);
        parentName= (TextView) findViewById(R.id.reportNameTextView);
        report=(TextView) findViewById(R.id.reportTextView);


        //Setting the name of the Parent.
        parentName.setText(" שלום "+parent);

        //filing the spinner with names
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,childs);
        chooseChild.setAdapter(adapter);
        //------------------------------

        //put listener on the childs-------------------------------------
        chooseChild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //What to do when its pressd

                //put child name on the report
                report.setText(childs.get(position));//here can chang txt in the report
                //-----------------------------
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //-----------------------------------------------------------------


    }


}
