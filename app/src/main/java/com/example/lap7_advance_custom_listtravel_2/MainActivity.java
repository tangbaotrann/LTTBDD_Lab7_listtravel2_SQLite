package com.example.lap7_advance_custom_listtravel_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtAddress;
    private Button btnSave;

    private AddressAdapter adapter;
    private List<Address> listAddress;
    private ListView listView;

    private AddressDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find id
        edtAddress = findViewById(R.id.edtAddress);
        btnSave = findViewById(R.id.btnSave);
        listView = findViewById(R.id.idListview);

        // init obj
        db = new AddressDatabaseHandler(this);

        // load --> UI
        loadDataToListView();

        // save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addressName = edtAddress.getText().toString().trim();

                db.saveAddress(new Address(addressName));
                loadDataToListView();

                Toast.makeText(MainActivity.this, "Lưu thành công.", Toast.LENGTH_SHORT).show();
                edtAddress.setText("");
            }
        });

    }

    // load --> UI
    public void loadDataToListView() {
        listAddress = db.getAllAddress();
        adapter = new AddressAdapter(MainActivity.this, R.layout.item_custom_list_view, listAddress);
        listView.setAdapter(adapter);
    }

}