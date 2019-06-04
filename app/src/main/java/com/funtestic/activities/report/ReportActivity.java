package com.funtestic.activities.report;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.activities.MainActivity;
import com.funtestic.activities.mainMenu.MainMenuActivity;
import com.funtestic.models.Child;
import com.funtestic.models.DataBase;
import com.funtestic.models.User;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener {


    private User parent ;
    private String token ;

    private SharedPreferences sp;
    private String phone;

    private ArrayList<Child> childs = new ArrayList<Child>();

    private ArrayList<String> childlist = new ArrayList<String>();

    private Spinner chooseChild;
    private TextView parentName;
    private TextView report;
    private Button sendReportButton;
    private int currentPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        currentPosition = 0;
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        phone= sp.getString("phone","DEFAULT");
        token= sp.getString("token", "DEFAULT");
        parent = DataBase.getInstance().getUserByPhone(phone,token);
        childs=DataBase.getInstance().getUserChildren(phone,token);
        if(childs != null) {
            childlist.add("");
            for (int i = 0; i < childs.size(); i++) {
                childlist.add(childs.get(i).getName());
            }
        }

        chooseChild = (Spinner) findViewById(R.id.reportChooseChildSpinner);
        parentName= (TextView) findViewById(R.id.reportNameTextView);
        report=(TextView) findViewById(R.id.reportTextView);
        sendReportButton= (Button)findViewById(R.id.buttonSendReport);


        //Setting the name of the Parent.
        parentName.setText("Hello " +parent.getFirst_name()+" " +parent.getLast_name());

        //filing the spinner with names
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,childlist);
        chooseChild.setAdapter(adapter);
        //------------------------------

        // add listener on send report button
        sendReportButton.setOnClickListener(this);

        //put listener on the childs-------------------------------------
        chooseChild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //What to do when its pressd
                currentPosition = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //-----------------------------------------------------------------


    }


    @Override
    public void onClick(View v) {
        // send report
        if(v.getId() ==  R.id.buttonSendReport) {
            if(currentPosition == 0){
                Toast toast = Toast.makeText(getApplicationContext(), "No child selected", Toast.LENGTH_LONG);
                toast.show();
            }
            else {
                if (DataBase.getInstance().sendReportToEmail(childs.get(currentPosition - 1).getId(), token)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "The report was successfully sent to your email", Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainMenuActivity.class));

                } else {
                    report.setText("");//here can chang txt in the report
                }
                //-----------------------------
            }
        }
    }
}
