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

import com.funtestic.R;
import com.funtestic.adapters.ChildListAdapter;
import com.funtestic.models.Child;

import java.util.ArrayList;


public class SelectChildActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String TAG = "Main";
    static final int REQUEST_CODE = 0;

    private ArrayList<Child> childsList;
    private ChildListAdapter adapter;
    private ListView mListView;
    private SearchView mSearchView;
    private Child selectedChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_child_layout);

        mListView = (ListView) findViewById(R.id.listView);
        mSearchView=(SearchView) findViewById(R.id.searchView);

        childsList = new ArrayList<>();
        adapter = new ChildListAdapter(this, R.layout.adapter_view_child_list_layout, childsList);


        Button btnAddChild = findViewById(R.id.btnAddChild);
        btnAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(SelectChildActivity.this, AddChildActivity.class), REQUEST_CODE);
            }
        });

        Button btnSeleChild = findViewById(R.id.btnSelectChild);
        btnSeleChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedChild!=null) {
                    //TODO send selectedChild object to next activity
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedChild = adapter.getItem(position);
                view.setSelected(true);
                Log.d(TAG,selectedChild.getName());
            }
        });


        Child john = new Child("John", "Male", 5, 1234);
        Child ron = new Child("Ron", "Male", 8, 2314);
        Child mira = new Child("Mira", "Female", 3, 3124);
        Child dana = new Child("Dana", "Female", 2, 4123);
        Child mike = new Child("Mike", "Male", 8, 9314);

        childsList.add(john);
        childsList.add(ron);
        childsList.add(mira);
        childsList.add(dana);
        childsList.add(mike);

        //TODO add childs of the appropriate father from database to childsList
        mListView.setAdapter(adapter);

        mListView.setTextFilterEnabled(true);
        setupSearchView();
    }

    private void setupSearchView()
    {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {

        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(newText);
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
        mListView.setAdapter(adapter);
        //Log.d(TAG,"onResume!!!");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                //TextView incomingName = findViewById(R.id.etIncomingName);
                //TextView incomingGender = findViewById(R.id.etIncomingGender);
                //TextView incomingAge = findViewById(R.id.etIncomingAge);


                Child incomingChild = (Child)data.getSerializableExtra("child");
                //incomingName.setText(incomingChild.getName());
                //incomingGender.setText(incomingChild.getGender());
                //incomingAge.setText((Integer.toString(incomingChild.getAge())));

                childsList.add(incomingChild);
                //TODO add incomingChild object to child database
                //Log.d(TAG,"onactivity result!!!" + incomingName);

            }
        }
    }

}
