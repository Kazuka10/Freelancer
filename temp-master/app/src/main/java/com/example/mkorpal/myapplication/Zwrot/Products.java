package com.example.mkorpal.myapplication.Zwrot;

/**
 * Created by m.korpal on 05.09.2016.
 */
public class Products {
    private int _id;
    public String _productname;
    public String _productkod;
    public String _productilosc;


    public Products(){

    }

    public Products(String productname, String productkod, String productilosc) {
        this._productname = productname;
        this._productkod = productkod;
        this._productilosc = productilosc;

    }
    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public String get_productname() {
        return _productname;
    }
    public void set_productname(String _productname) {
        this._productname = _productname;
    }
    public String get_productkod() {
        return _productkod;
    }
    public void set_productkod(String _productkod) {
        this._productkod = _productkod;
    }
    public String get_productilosc() {
        return _productilosc;
    }
    public void set_productilosc(String _productilosc) {
        this._productilosc = _productilosc;
    }









    /*
    public Products(){

    }
    public Products(String productName) {
        this._productname = productName;

    }




    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;


    }

    public String get_productname() {
        return _productname;
    }


    public void set_productname(String _productname) {
        this._productname = _productname;
    }

*/
}