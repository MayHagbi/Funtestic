package com.funtestic.activities.child;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.funtestic.R;
import com.funtestic.activities.mainMenu.SelectQuizCategory;
import com.funtestic.adapters.ChildListAdapter;
import com.funtestic.models.Child;
import com.funtestic.models.DataBase;
import com.funtestic.models.User;

import java.io.Serializable;
import java.util.ArrayList;


public class SelectChildActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,View.OnClickListener {

    private SharedPreferences sp;
    private User parent ;
    private String token ;
    private String phone;
    private ArrayList<Child> childs = new ArrayList<Child>();

    private static final String TAG = "Main";
    static final int REQUEST_ADDCHILD = 0;
    private ChildListAdapter adapter;
    private Button btnAddChild,btnSeleChild;

    private ListView childListView;
    private SearchView childSearchView;
    private Child selectedChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_child_layout);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        phone= sp.getString("phone","DEFAULT");
        token= sp.getString("token", "DEFAULT");
        parent = DataBase.getInstance().getUserByPhone(phone,token);
        Log.d("TTTTT",parent.toString());

        childs=DataBase.getInstance().getUserChildren(phone,token);
        Log.d("TTTTT",childs.toString());



        childListView = (ListView) findViewById(R.id.listView);
        childSearchView = (SearchView) findViewById(R.id.searchView);
        btnAddChild = (Button) findViewById(R.id.btnAddChild);
        btnSeleChild = (Button) findViewById(R.id.btnSelectChild);

        btnAddChild.setOnClickListener(this);
        btnSeleChild.setOnClickListener(this);
        childListView.setTextFilterEnabled(true);
        childListView.setAdapter(adapter);

        adapter = new ChildListAdapter(this, R.layout.adapter_view_child_list_layout, childs);


        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedChild = adapter.getItem(position);
                view.setSelected(true);
                Log.d(TAG,selectedChild.getName());
            }
        });

        //addChild();
        setupSearchView();
    }


    @Override
    public boolean onQueryTextChange(String newText)
    {
        if (TextUtils.isEmpty(newText)) {
            childListView.clearTextFilter();
        } else {
            childListView.setFilterText(newText);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        childs=DataBase.getInstance().getUserChildren(phone,token);
        adapter = new ChildListAdapter(this, R.layout.adapter_view_child_list_layout, childs);
        childListView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADDCHILD) {
            if (resultCode == RESULT_OK) {
                childs=DataBase.getInstance().getUserChildren(phone,token);
            }
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()) {
            case R.id.btnAddChild:
                intent = new Intent(SelectChildActivity.this, AddChildActivity.class);
                startActivityForResult(intent, REQUEST_ADDCHILD);
                break;

            case R.id.btnSelectChild:
                if (selectedChild!=null) {
                    intent = new Intent(SelectChildActivity.this, SelectQuizCategory.class);
                    intent.putExtra("child_id",selectedChild.getId());
                    startActivityForResult(intent, REQUEST_ADDCHILD);
                    break;
                }
                else
                    Toast.makeText(this,R.string.no_selected_child, Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void setupSearchView()
    {
        childSearchView.setIconifiedByDefault(false);
        childSearchView.setOnQueryTextListener(this);
        childSearchView.setSubmitButtonEnabled(true);
        childSearchView.setQueryHint("Search Here");
    }

}


