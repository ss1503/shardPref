package com.example.shardpref;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //vars
    int counter = 0;
    String currInput = "";

    //components
    EditText edt;
    TextView counterTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText) findViewById(R.id.textInputEdt);
        counterTxt = (TextView) findViewById(R.id.counterTxt);

        SharedPreferences info = getSharedPreferences("user_stats", MODE_PRIVATE);
        counter = info.getInt("counterInput", 0);
        currInput = info.getString("strInput", "Empty");

        counterTxt.setText(counter + "");
        edt.setText(currInput + "");
    }

    /**
     * This function increases the counter by one every click
     * @param view
     */
    public void countCLicked(View view)
    {
        counter++;
        counterTxt.setText(counter + "");
    }

    /**
     * This function resets the counter
     * @param view
     */
    public void resetClicked(View view)
    {
        counter = 0;
        counterTxt.setText(counter + "");
    }

    /**
     * This function exits the app and saves the stats of the user
     * @param view
     */
    public void exitClicked(View view)
    {
        currInput = edt.getText().toString();

        SharedPreferences info = getSharedPreferences("user_stats", MODE_PRIVATE);
        SharedPreferences.Editor editor = info.edit();

        //saving the stats
        editor.putString("strInput", currInput);
        editor.putInt("counterInput", counter);
        editor.commit();

        finish();
    }

    /**
     * This function creates option menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This function starts new Activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent si = new Intent(this, MainActivity2.class);
        startActivity(si);
        return super.onOptionsItemSelected(item);
    }
}