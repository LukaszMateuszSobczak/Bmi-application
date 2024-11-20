package com.lukaszsobczak.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight;
    private EditText editTextHeight;
    private EditText editTextAge;
    private MaterialCardView cardViewMale;
    private MaterialCardView cardViewFemale;
    private boolean isCardMaleClicked = false;
    private boolean isCardFemaleClicked = false;
    private MaterialButton buttonCalculate;
    private TextView textViewResult;
    private TextView textViewResultAdditional;
    private int age;
    private double bmi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getViews();
        cardsClickListener();


        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
                if (isCardMaleClicked) {
                    resultMale();
                } else if (isCardFemaleClicked) {
                    resultFemale();
                }
            }
        });

    }


    private void getViews() {
        editTextWeight = findViewById(R.id.edit_text_weight);
        editTextHeight = findViewById(R.id.edit_text_height);
        editTextAge = findViewById(R.id.edit_text_age);
        cardViewMale = findViewById(R.id.card_view_gender_male);
        cardViewFemale = findViewById(R.id.card_view_gender_female);
        buttonCalculate = findViewById(R.id.button_calculate);
        textViewResult = findViewById(R.id.text_view_result);
        textViewResultAdditional = findViewById(R.id.text_view_result_additional);
    }

    private void cardsClickListener() {
        cardViewMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isCardMaleClicked) {
                    isCardMaleClicked = true;
                    isCardFemaleClicked = false;
                    setColorInCards(isCardMaleClicked, isCardFemaleClicked);
                } else {
                    isCardMaleClicked = false;
                    isCardFemaleClicked = false;
                    setColorInCards(isCardMaleClicked, isCardFemaleClicked);

                }

            }
        });

        cardViewFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isCardFemaleClicked) {
                    isCardFemaleClicked = true;
                    isCardMaleClicked = false;
                    setColorInCards(isCardMaleClicked, isCardFemaleClicked);
                } else {
                    isCardFemaleClicked = false;
                    isCardMaleClicked = false;
                    setColorInCards(isCardMaleClicked, isCardFemaleClicked);

                }

            }
        });
    }

    private int getColorForCard(MaterialCardView cardView) {
        if (cardView == cardViewMale) {
            return ContextCompat.getColor(MainActivity.this, R.color.colorPressedMale);
        } else if (cardView == cardViewFemale) {
            return ContextCompat.getColor(MainActivity.this, R.color.colorPressedFemale);
        } else {
            return ContextCompat.getColor(MainActivity.this, R.color.colorDefault);
        }
    }

    private void setColorInCards(boolean isCardMaleClicked, boolean isCardFemaleClicked) {
        if (isCardMaleClicked && !isCardFemaleClicked) {
            cardViewMale.setStrokeColor(getColorForCard(cardViewMale));
            cardViewFemale.setStrokeColor(getColorForCard(null));
            buttonCalculate.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this, R.color.colorPressedMale));
        } else if (!isCardMaleClicked && isCardFemaleClicked) {
            cardViewFemale.setStrokeColor(getColorForCard(cardViewFemale));
            cardViewMale.setStrokeColor(getColorForCard(null));
            buttonCalculate.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this, R.color.colorPressedFemale));
        } else {
            cardViewFemale.setStrokeColor(getColorForCard(null));
            cardViewMale.setStrokeColor(getColorForCard(null));
            buttonCalculate.setBackgroundTintList(ContextCompat.getColorStateList(MainActivity.this, R.color.buttonColorDefault));
        }
    }

    private void calculateBmi() {
        String stringWeight = editTextWeight.getText().toString();
        String stringHeight = editTextHeight.getText().toString();
        String stringAge = editTextAge.getText().toString();

        if (stringWeight.isEmpty()) {
            Toast.makeText(MainActivity.this, "Input correct weight", Toast.LENGTH_SHORT).show();

        } else if (stringHeight.isEmpty()) {
            Toast.makeText(MainActivity.this, "Input correct height", Toast.LENGTH_SHORT).show();

        } else if (!isCardMaleClicked && !isCardFemaleClicked) {
            Toast.makeText(MainActivity.this, "Choose your gender", Toast.LENGTH_SHORT).show();

        } else if (stringAge.isEmpty()) {
            Toast.makeText(MainActivity.this, "Input correct age", Toast.LENGTH_SHORT).show();

        } else {
            double weight = Double.parseDouble(stringWeight);
            double height = Double.parseDouble(stringHeight);
            age = Integer.parseInt(stringAge);
            bmi = weight / ((height / 100.0) * (height / 100.0));
        }
    }

    private void resultMale() {

        if (age >= 18 && age < 25) {
            conditionalCheck(20, 25, 30, 40);
        } else if (age >= 25 && age < 35) {
            conditionalCheck(21, 26, 31, 41);
        } else if (age >= 35 && age < 45) {
            conditionalCheck(22, 27, 32, 42);
        } else if (age >= 45 && age < 55) {
            conditionalCheck(23, 28, 33, 43);
        } else if (age >= 55 && age < 65) {
            conditionalCheck(24, 29, 34, 44);
        } else if (age >= 65) {
            conditionalCheck(25, 30, 35, 45);
        } else {
            setResultText("BMI:", "Please consult your BMI with doctor.");
        }
    }

    private void resultFemale() {
        if (age >= 18 && age < 25) {
            conditionalCheck(19, 24, 29, 39);
        } else if (age >= 25 && age < 35) {
            conditionalCheck(20, 25, 30, 40);
        } else if (age >= 35 && age < 45) {
            conditionalCheck(21, 26, 31, 41);
        } else if (age >= 45 && age < 55) {
            conditionalCheck(22, 27, 32, 42);
        } else if (age >= 55 && age < 65) {
            conditionalCheck(23, 28, 33, 43);
        } else if (age >= 65) {
            conditionalCheck(24, 29, 34, 44);
        } else {
            setResultText("BMI:", "Please consult your BMI with doctor.");
        }
    }

    private void conditionalCheck(int stage1, int stage2, int stage3, int stage4) {
        if (bmi < stage1) {
            setResultText(getString(R.string.underweight), ("(< " + stage1 + ")"));
        } else if (bmi >= stage1 && bmi < stage2) {
            setResultText(getString(R.string.normal_weight), ("(" + stage1 + " - " + stage2 + ")"));
        } else if (bmi >= stage2 && bmi < stage3) {
            setResultText(getString(R.string.overweight), ("(" + stage2 + " - " + stage3 + ")"));
        } else if (bmi >= stage3 && bmi < stage4) {
            setResultText(getString(R.string.adiposity), ("(" + stage3 + " - " + stage4 + ")"));
        } else if (bmi >= stage4) {
            setResultText(getString(R.string.severe_adiposity), ("(> " + stage4 + ")"));
        }
    }

    private void setResultText(String text, String additional_text) {
        textViewResult.setText(getString(R.string.text_result, text, formatBmiToTwoDecimal()));
        textViewResultAdditional.setText(additional_text);
    }

    private String formatBmiToTwoDecimal() {
        DecimalFormat formatter = new DecimalFormat("0.00");
        return formatter.format(bmi);
    }
}