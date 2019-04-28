package com.example.eleon.project1cs478;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/* Eric Leon eleon23 654889611
   CS 478 Project 1: Simple Google Maps Application
   This application will be able to take in user input for a specific address they would like to look up.
   Once entered the app will display the map location on the Google Maps Application.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //call startMapActivity to start the application
        startMapActivity();

    }

    //function to create the intent to start the second activity for the user's address input
    private void startMapActivity(){
        //get the address button
        Button addressBtn =(Button) findViewById(R.id.addressButton);

        //set the buttons action listener
        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(getApplicationContext(), addressInputActivity.class);
                startActivityForResult(mapIntent, 1);
            }
        });
    }

    //wait for activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //store the address string
        String address = data.getStringExtra("address");

        //check the result code and call the map button that will handle each event
        if(resultCode == RESULT_OK){

            onMapButton(address, true);
        }
        else if(resultCode == RESULT_CANCELED){
            onMapButton(address, false);
        }

    }

    //function to handle the map button and launch the google maps
    public void onMapButton(final String address, boolean check){
        //store the address string
        final String temp = address;

        //if true then launch Google maps with the appropriate address
        if(check){
            //get the map button and create its action listener
            Button mapButton = (Button)findViewById(R.id.mapButton);
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Parse the data using the Uri.Parse
                    Uri data = Uri.parse("geo:37.7749,-122.4192?q=" + Uri.encode(temp));
                    //Create the intent
                    Intent map = new Intent(Intent.ACTION_VIEW);
                    //set the data and package
                    map.setData(data);
                    map.setPackage("com.google.android.apps.maps");

                    //Make sure there is an app that can handle this intent and launch the activity
                    if(map.resolveActivity(getPackageManager()) != null) {
                        startActivity(map);
                    }
                }
            });
        }

        //If false the address is invalid and display Error Toast message
        else{
            //get the map button and set its action listener
            Button mapButton = (Button)findViewById(R.id.mapButton);
            mapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //get the application context
                    Context context = getApplicationContext();
                    //create Toast error message
                    String errorMSG = "ERROR: You did NOT enter a valid address...";
                    //set the duration
                    int duration = Toast.LENGTH_LONG;
                    //create the Toast object and set its parameters
                    Toast toast = Toast.makeText(context, errorMSG, duration);
                    //Display the Toast
                    toast.show();

                }
            });
        }
    }


}
