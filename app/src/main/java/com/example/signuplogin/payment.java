package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class payment extends AppCompatActivity {

    TextInputEditText textInputEditTextFullname, textInputEditTextBank, textInputEditTextAmount, textInputEditTextAcc, textInputEditTextDate;
    Button buttonBook;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        buttonBook = findViewById(R.id.buttonBook);
        textInputEditTextFullname = findViewById(R.id.fullname);
        textInputEditTextBank = findViewById(R.id.bank);
        textInputEditTextAmount = findViewById(R.id.amount);
        textInputEditTextAcc = findViewById(R.id.acc);
        textInputEditTextDate = findViewById(R.id.date);
        progressBar = findViewById(R.id.progress);

        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, bank, amount, acc, date;
                fullname = String.valueOf(textInputEditTextFullname.getText());
                bank = String.valueOf(textInputEditTextBank.getText());
                amount = String.valueOf(textInputEditTextAmount.getText());
                acc = String.valueOf(textInputEditTextAcc.getText());
                date = String.valueOf(textInputEditTextDate.getText());

                if (!fullname.equals("") && !bank.equals("") && !amount.equals("") && !acc.equals("") && !date.equals(""))

                    progressBar.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[5];
                        field[0] = "fullname";
                        field[1] = "bank";
                        field[2] = "amount";
                        field[3] = "acc";
                        field[4] = "date";
                        //Creating array for data
                        String[] data = new String[5];
                        data[0] = fullname;
                        data[1] = bank;
                        data[2] = amount;
                        data[3] = acc;
                        data[4] = date;
                        PutData putData = new PutData("http://192.168.153.121/phpfiles/booked.php", "POST", field, data);
                        if (putData.startPut()) {

                            if (putData.onComplete()) {
                                progressBar.setVisibility(View.GONE);
                                String result = putData.getResult();
                                if (result.equals("Booking Success")){
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Booked.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        //End Write and Read data with URL
                    }
                });
            }

        });

    }
}