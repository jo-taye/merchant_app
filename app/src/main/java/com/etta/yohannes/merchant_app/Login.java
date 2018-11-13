package com.etta.yohannes.merchant_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {

    private EditText editText_user_name, editText_password, editText_ip;
    private Button button_clear, button_next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init_ui();
    }


    private void init_ui(){

        //IDS
        //clear - button
        //next - button2
        //user_name - editText
        //password - editText2
        //ip - editText3

        editText_user_name = (EditText)findViewById(R.id.editText);
        editText_password = (EditText)findViewById(R.id.editText2);
        editText_ip = (EditText)findViewById(R.id.editText3);

        button_clear = (Button)findViewById(R.id.button);
        button_next = (Button)findViewById(R.id.button2);

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate_form()){

                    String user_name, password, ip;
                    user_name = editText_user_name.getText().toString();
                    password = editText_password.getText().toString();
                    ip = editText_ip.getText().toString();

                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("KEY_USER_NAME", user_name);
                    intent.putExtra("KEY_PASSWORD", password);
                    intent.putExtra("KEY_IP", ip);
                    startActivity(intent);
                }
            }
        });
    }
    private boolean validate_form(){
        String user_name, password, ip;
        user_name = editText_user_name.getText().toString();
        password = editText_password.getText().toString();
        ip = editText_ip.getText().toString();

        if(user_name.equals("") || password.equals("") || ip.equals("")){
            return false;
        }
        return true;
    }
}
