package com.example.lap7_advance_custom_listtravel_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private EditText edtNewAddress;
    private Button btnNewAddress;

    //private List<Address> listAddress;

    private AddressDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // find id
        edtNewAddress = findViewById(R.id.edtNewAddress);
        btnNewAddress = findViewById(R.id.btnNewAddress);

        // init obj
        db = new AddressDatabaseHandler(this);

        Bundle bundle = getIntent().getExtras();
        String addressName = bundle.getString("addressName");

        // set edt
        edtNewAddress.setText(addressName);

        // update
        btnNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Address address = new Address(edtNewAddress.toString());
                //String addressNewName = edtNewAddress.getText().toString().trim();
                //db.updateAddress(address);

                Toast.makeText(MainActivity2.this, "Update successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }
}