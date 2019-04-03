package com.funtestic.activities.child;

import android.content.Intent;
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
import com.funtestic.adapters.ChildListAdapter;
import com.funtestic.models.Child;
import com.funtestic.models.Person;

import java.util.ArrayList;


public class SelectChildActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,View.OnClickListener {

    private static final String TAG = "Main";
    static final int REQUEST_ADDCHILD = 0;

    private ArrayList<Child> childsList;    //TODO get the list from parent
    private ChildListAdapter adapter;
    private Button btnAddChild,btnSeleChild;

    private ListView childListView;
    private SearchView childSearchView;
    private Child selectedChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_child_layout);

        childListView = (ListView) findViewById(R.id.listView);
        childSearchView = (SearchView) findViewById(R.id.searchView);
        btnAddChild = (Button) findViewById(R.id.btnAddChild);
        btnSeleChild = (Button) findViewById(R.id.btnSelectChild);

        btnAddChild.setOnClickListener(this);
        btnSeleChild.setOnClickListener(this);
        childListView.setTextFilterEnabled(true);
        childListView.setAdapter(adapter);

        childsList = new ArrayList<>();
        adapter = new ChildListAdapter(this, R.layout.adapter_view_child_list_layout, childsList);


        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedChild = adapter.getItem(position);
                view.setSelected(true);
                Log.d(TAG,selectedChild.getName());
            }
        });

////check/////////
        //Intent i = getIntent();
        //Person dene = (Person)i.getSerializableExtra("Person");
        //Log.d(TAG,dene.getName());
/////////////////
        addChild();

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
        ChildListAdapter adapter = new ChildListAdapter(this, R.layout.adapter_view_child_list_layout, childsList);
        childListView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADDCHILD) {
            if (resultCode == RESULT_OK) {
                Child incomingChild = (Child)data.getSerializableExtra("child");
                childsList.add(incomingChild);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnAddChild:
                startActivityForResult(new Intent(SelectChildActivity.this, AddChildActivity.class), REQUEST_ADDCHILD);
                break;

            case R.id.btnSelectChild:
                if (selectedChild!=null) {
                    //TODO send selectedChild object to next activity
                    //finish();
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

    private void addChild(){
        ///////////////////for check/////////////////////
        Child john = null;
        Child ron = null;
        Child mira = null;
        Child dana = null;
        Child mike = null;
        try {
            john = new Child("John", "Male", "5", "1234");
            ron = new Child("Ron", "Male", "8", "2314");
            mira = new Child("Mira", "Female", "3", "3124");
            dana = new Child("Dana", "Female", "2", "4123");
            mike = new Child("Mike", "Male", "8", "9314");

            childsList.add(john);
            childsList.add(ron);
            childsList.add(mira);
            childsList.add(dana);
            childsList.add(mike);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///////////////////////////////////////////////
        //TODO add childs of the appropriate father from database to childsList
    }

}
