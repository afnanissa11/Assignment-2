package com.example.assignement2;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText height;
    private EditText weight;
    private TextView result;
    private EditText Name;
    private static final String NAME = "NAME";
    private static final String HEIGHT = "HEIGHT";
    private static final String WEIGHT = "WEIGHT";
    private static final String flag = "FLAG";
    private SharedPreferences profo;
    private SharedPreferences.Editor editor;
    private CheckBox chk;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.edittext1);
        height = (EditText) findViewById(R.id.edittext2);
        weight = (EditText) findViewById(R.id.edittext3);
        result = (TextView) findViewById(R.id.edittext4);
        spinner = findViewById(R.id.genderspinner);
        chk = (CheckBox) findViewById(R.id.chk);
        button = (Button) findViewById(R.id.next1);

        genderr();
        setupSharedpref();
        setupviews();
        checkpref();


    }

    private void checkpref() {
        boolean flags = profo.getBoolean(flag, false);

        if (flags) {
            String NAMES = profo.getString(NAME, "");
            String HIGHTS = profo.getString(HEIGHT, "");
            String WEIGHTS = profo.getString(WEIGHT, "");

            Name.setText(NAMES);
            height.setText(HIGHTS);
            weight.setText(WEIGHTS);

            chk.setChecked(true);


        }
    }


    private void setupviews() {
        Name = findViewById(R.id.edittext1);
        height = findViewById(R.id.edittext2);
        weight = findViewById(R.id.edittext3);
        spinner = findViewById(R.id.genderspinner);
        chk = findViewById(R.id.chk);


    }

    private void setupSharedpref() {
        profo = PreferenceManager.getDefaultSharedPreferences(this);
        editor = profo.edit();

    }


    public void Calculate_BMI(View view) {
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null && !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);

            float bmi = weightValue / (heightValue * heightValue);

            displayBMI(bmi);
        }
    }

    private void displayBMI(float test) {

        String bmitext = "";

        if (Float.compare(test, 15f) <= 0) {
            bmitext = "underweight";

        } else if (Float.compare(test, 18.5f) > 0 && Float.compare(test, 25f) <= 0) {
            bmitext = "normal";


        } else if (Float.compare(test, 25f) > 0 && Float.compare(test, 30f) <= 0) {
            bmitext = "overweight";

        } else if (Float.compare(test, 30f) > 0 && Float.compare(test, 35f) <= 0) {
            bmitext = "obese";

        }

        bmitext = test + "\n\n" + bmitext;
        result.setText(bmitext);
    }


    private void genderr() {
        String[] arraySpinner = new String[]{
                "Male", "Female"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    public void openManinActivity2(View view) {
        Intent intent;
        intent = new Intent(this, MainActivity2.class);
        startActivity(intent);

    }


    public void savebtn(View view) {
        String name = Name.getText().toString();
        String heightl = height.getText().toString();
        String weightl = weight.getText().toString();
        String gender = profo.getString(String.valueOf(spinner), "");

        if (chk.isChecked()) {
            editor.putString(NAME, name);
            editor.putString(HEIGHT, heightl);
            editor.putString(WEIGHT, weightl);
            editor.putString(String.valueOf(spinner), gender);
            editor.putBoolean(flag, true);
            editor.commit();
        }
    }
}




