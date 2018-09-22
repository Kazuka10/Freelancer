package com.example.mkorpal.myapplication.przyjecie;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;
import java.util.List;

/**
 * Class that is used to convert a list into an XML doc string.
 * this class has a private constructor and should not be instantiated.
 */
public class XmlUtility {
    private XmlUtility() {

    }


    /**
     * @param ProductList takes of list of {@link Products} class objects.
     * @return string containing an entire xml document.|| returns null on failure
     */
    static String serializeTable(List<Products> ProductList){

        XmlSerializer xmlSerializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        try {
        xmlSerializer.setOutput(writer);
        xmlSerializer.startDocument("UTF-8",true);
        xmlSerializer.startTag("","Products");
        for(Products product:ProductList ){
            xmlSerializer.startTag("","Product");
            xmlSerializer.startTag("","kod");
            xmlSerializer.text(product._productkod);
            xmlSerializer.endTag("","kod");
            xmlSerializer.startTag("","nazwa");
            xmlSerializer.text(product._productname);
            xmlSerializer.endTag("","nazwa");
            xmlSerializer.startTag("","ilosc");
            xmlSerializer.text(product._productilosc);
            xmlSerializer.endTag("","ilosc");
            xmlSerializer.endTag("","Product");
        }
        xmlSerializer.endTag("","Products");
        xmlSerializer.endDocument();
        return writer.toString();
        }catch (Exception e){
            Log.d("XMLPARSER",e.getMessage());
        }
        return null;
    }


}
