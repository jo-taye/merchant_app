package com.etta.yohannes.merchant_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    //TODO: 1st PAGE:- REGISTRATION PAGE TO ONLY ENTER DRIVER PHONE NUMBER THEN WE MAKE THAT STATIC MERCHANT ID
    //TODO: 2nd PAGE:- APP ASKS CARD NUMBER, PIN, EXPIRY DATE, AMOUNT, THEN WE HOT THIS LINK. APP SHOULD INCREMENT SourceTransID=10218

    public final String URL = "https://api.myamole.com/Payment/FettanPay.svc/api/FettanPAY";

    private String user_name, password, ip;

    private EditText editText_cardNumber, editText_expiration_date, editText_pin, editText_payment_action, editText_amount,
    editText_fettan_merchant_id, editText_order_description, editText_source_trans_id;

    private Button button_clear, button_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        user_name = getIntent().getStringExtra("KEY_USER_NAME");
        password = getIntent().getStringExtra("KEY_PASSWORD");
        ip = getIntent().getStringExtra("KEY_IP");





    }


    private void init_ui(){

        editText_cardNumber = (EditText)findViewById(R.id.editText4);
        editText_expiration_date = (EditText)findViewById(R.id.editText5);
        editText_pin = (EditText)findViewById(R.id.editText6);
        editText_payment_action = (EditText)findViewById(R.id.editText7);
        editText_amount = (EditText)findViewById(R.id.editText9);
        editText_fettan_merchant_id = (EditText)findViewById(R.id.editText10);
        editText_order_description = (EditText)findViewById(R.id.editText11);
        editText_source_trans_id = (EditText)findViewById(R.id.editText12);


        button_submit = (Button)findViewById(R.id.button3);
        button_clear = (Button)findViewById(R.id.button4);


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate_form()){
                    HttpGetRequest httpGetRequest = new HttpGetRequest();
                    httpGetRequest.execute(URL);
                }
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }
    private void clear(){
        editText_cardNumber.setText("");
        editText_expiration_date.setText("");
        editText_pin.setText("");
        editText_payment_action.setText("");
        editText_amount.setText("");
        editText_fettan_merchant_id.setText("");
        editText_order_description.setText("");
        editText_source_trans_id.setText("");
    }


    private boolean check_if_empty_exists(String ... args){
        for(String i : args){
            if(i.equals("")){
                return true;
            }

        }
        return false;
    }



    private boolean validate_form(){
        String card_number, expiration_date, pin, payment_action, amount, fettan_merchant_id, order_description, source_trans_id;

        card_number = editText_cardNumber.getText().toString();
        expiration_date = editText_expiration_date.getText().toString();
        pin = editText_pin.getText().toString();
        payment_action = editText_payment_action.getText().toString();
        amount = editText_amount.getText().toString();
        fettan_merchant_id = editText_fettan_merchant_id.getText().toString();
        order_description = editText_order_description.getText().toString();
        source_trans_id = editText_source_trans_id.getText().toString();

        if(check_if_empty_exists(card_number, expiration_date, pin, payment_action, amount, fettan_merchant_id, order_description, source_trans_id)){
            return false;
        }

        return true;
    }

    public class HttpGetRequest extends AsyncTask<String, Void, String> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;
        @Override
        protected String doInBackground(String... params){
            String stringUrl = params[0];
            String result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection =(HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();
            }
            catch(IOException e){
                e.printStackTrace();
                result = null;
            }
            return result;
        }
        protected void onPostExecute(String result){
            super.onPostExecute(result);
        }
    }



}
