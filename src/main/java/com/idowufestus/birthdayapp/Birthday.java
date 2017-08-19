package com.idowufestus.birthdayapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Birthday extends AppCompatActivity {

    EditText ET_NAME, ET_APPELATION;
    public static String name, appelation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_layout);

        instantiateField();

    }

    public static String getAppelation() {
        return appelation;
    }

    public static void setAppelation(String appelation) {
        Birthday.appelation = appelation;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Birthday.name = name;
    }

    private  void instantiateField(){
        ET_NAME = (EditText) findViewById(R.id.name);
        ET_APPELATION = (EditText) findViewById(R.id.appelation);
    }

    public void openDesign(View view){
        name = ET_NAME.getText().toString();
        appelation = ET_APPELATION.getText().toString();

        setAppelation(appelation);
        setName(name);

        ET_APPELATION.setText("");
        ET_NAME.setText("");
        startActivity(new Intent(Birthday.this, Design.class));
    }
}
