package com.example.lap7_advance_custom_listtravel_2;

public class Address {

    int _id;
    String _address;

    public Address() {

    }

    public Address(int id, String address) {
        this._id = id;
        this._address = address;
    }

    public Address(String address) {
        this._address = address;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
}
