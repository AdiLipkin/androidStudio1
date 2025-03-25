package com.example.hw1;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputValue;
    private Spinner conversionSpinner;
    private TextView resultText;
    private Button convertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        conversionSpinner = findViewById(R.id.conversionSpinner);
        resultText = findViewById(R.id.resultText);
        convertButton = findViewById(R.id.convertButton);

        String[] conversionOptions = {
                "Centimeters → Meters",
                "Meters → Kilometers",
                "Celsius → Fahrenheit",
                "Fahrenheit → Celsius",
                "Grams → Kilograms"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, conversionOptions);
        conversionSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(v -> performConversion());
    }

    private void performConversion() {
        String selectedConversion = conversionSpinner.getSelectedItem().toString();
        String inputText = inputValue.getText().toString();

        if (inputText.isEmpty()) {
            resultText.setText("Please enter a value");
            return;
        }

        double input = Double.parseDouble(inputText);
        double result = 0;
        String unit = "";

        switch (selectedConversion) {
            case "Centimeters → Meters":
                result = input / 100;
                unit = " m";
                break;
            case "Meters → Kilometers":
                result = input / 1000;
                unit = " km";
                break;
            case "Celsius → Fahrenheit":
                result = (input * (9.0 / 5.0)) + 32;
                unit = " °F";
                break;
            case "Fahrenheit → Celsius":
                result = (input - 32) * (5.0 / 9.0);
                unit = " °C";
                break;
            case "Grams → Kilograms":
                result = input / 1000;
                unit = " kg";
                break;
        }

        resultText.setText(String.format("%.2f%s", result, unit));
    }
}