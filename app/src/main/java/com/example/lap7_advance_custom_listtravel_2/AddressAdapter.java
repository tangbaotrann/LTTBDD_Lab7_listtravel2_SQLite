package com.example.lap7_advance_custom_listtravel_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddressAdapter extends BaseAdapter {

    private Context context;
    private int idLayout;
    private List<Address> listAddress;

    private ImageButton imgBtnEdit;
    private ImageButton imgBtnDelete;

    private ListView listView;

    private AddressDatabaseHandler db;

    public AddressAdapter(Context context, int idLayout, List<Address> listAddress) {
        this.context = context;
        this.idLayout = idLayout;
        this.listAddress = listAddress;
    }

    @Override
    public int getCount() {
        if(listAddress.size() != 0 && !listAddress.isEmpty()) {
            return listAddress.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);
        }

        // init obj
        db = new AddressDatabaseHandler(context);

        // listview
        listView = view.findViewById(R.id.idListview);

        // tv
        TextView tvId = view.findViewById(R.id.tvId);
        TextView tvAddress = view.findViewById(R.id.tvAddress);

        final Address address = listAddress.get(i);

        if(listAddress != null && !listAddress.isEmpty()) {
            // set tv
            tvId.setText(String.valueOf(address.get_id())+".");
            tvAddress.setText(address.get_address());
        }

        // Handle imgBtn Edit
        imgBtnEdit = view.findViewById(R.id.imgBtnEdit);
        imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", address.get_id());
                bundle.putString("addressName", address.get_address());
                intent.putExtras(bundle);

                view.getContext().startActivity(intent);
            }
        });

        imgBtnDelete = view.findViewById(R.id.imgBtnDelete);
        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAddress(listAddress.get(i).get_id());
                Toast.makeText(context, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                listAddress = db.getAllAddress();
                AddressAdapter adapter  = new AddressAdapter(context, R.layout.item_custom_list_view, listAddress);
                listView.setAdapter(adapter);
            }
        });

        return view;
    }
}
