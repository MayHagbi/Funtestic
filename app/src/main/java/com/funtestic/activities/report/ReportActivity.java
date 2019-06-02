package com.funtestic.activities.report;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class ReportActivity extends AppCompatActivity {


    private User parent ;
    private String token ;

    private SharedPreferences sp;
    private String phone;

    private ArrayList<Child> childs = new ArrayList<Child>();
    private DataBase db = DataBase.getInstance();
    private ArrayList<String> childlist = new ArrayList<String>();

    private Spinner chooseChild;
    private TextView parentName;
    private TextView report;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        phone= sp.getString("phone","DEFAULT");
        token= sp.getString("token", "DEFAULT");
        parent = db.getUserByPhone(phone,token);
        childs=db.getUserChildren(phone,token);

        for(int i=0; i<childs.size() ; i++){
            childlist.add(childs.get(i).getName());
        }



        chooseChild = (Spinner) findViewById(R.id.reportChooseChildSpinner);
        parentName= (TextView) findViewById(R.id.reportNameTextView);
        report=(TextView) findViewById(R.id.reportTextView);


        //Setting the name of the Parent.
        parentName.setText(R.string.hello+parent.getFirst_name() +parent.getLast_name());

        //filing the spinner with names
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,childlist);
        chooseChild.setAdapter(adapter);
        //------------------------------

        //put listener on the childs-------------------------------------
        chooseChild.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //What to do when its pressd

                //put child name on the report

                if(db.sendReportToEmail(childs.get(position).getId(),token))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "The report was successfully sent to your email";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    finish();
                    startActivity(new Intent(getApplicationContext() , MainMenuActivity.class));
                }
                else{
                    report.setText("שגיאה לא ניתן לשלוח דוח");//here can chang txt in the report
                }
                //-----------------------------
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //-----------------------------------------------------------------


    }


}
