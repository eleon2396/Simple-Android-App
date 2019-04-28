package com.example.eleon.project1cs478;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

/* Second activity that will get the user input from the text field and send it back through the intent to the main activity.
   This class will also check for the validity of the input and set RESULT_OK or RESULT_CANCELLED depending on the input.
   This activity also implements a back press if the user wants to return to the main activity.

*/

public class addressInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_input);

        //call function to get the user input and send it back to the main activity
        getUserInputAndSend();

    }

    //Function to be able to get the address string and return it to the main activity
    public void getUserInputAndSend(){

        //get the address Edit Text that has the string the user entered and set the OnEditActionListener
        final EditText addressText = (EditText) findViewById(R.id.addressEditText);
        addressText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //Check that the end is NOT null and the user entered the Done or Return key on the soft keyboard
                if(event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE ){

                    //get the address string
                    String address = addressText.getText().toString();
                    //Get the intent
                    Intent intent = getIntent();

                    //Error checking to see if the edit text is empty if so then set the result to 'Result Canceled'
                    if(address.matches("")) {
                        setResult(RESULT_CANCELED, intent);
                    }
                    //Else the user entered something valid into the edit text then set the result to 'Result Ok' And add the string to the intent
                    else{
                        intent.putExtra("address", address);
                        setResult(RESULT_OK, intent);
                    }
                    //Once the input is saved and the result code is set then end this activity and return to main activity
                    finish();
                }
                return false;
            }
        });

    }


    //Function for letting the user hit the back button and return to the main activity
    @Override
    public void onBackPressed() {
        EditText addressText = (EditText) findViewById(R.id.addressEditText);
        String address = addressText.getText().toString();
        //Get the intent
        Intent intent = getIntent();

        //Error checking to see if the edit text is empty if so then set the result to 'Result Canceled'
        if(address.matches("")) {
            setResult(RESULT_CANCELED, intent);
        }
        //Else the user entered something valid into the edit text then set the result to 'Result Ok' And add the string to the intent
        else{
            intent.putExtra("address", address);
            setResult(RESULT_OK, intent);
        }
        //Once the input is saved and the result code is set then end this activity and return to main activity
        finish();


        return;
    }
}
