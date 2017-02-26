/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ntkduy1604.findarestaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find the View that shows the numbers category & Set a click listener on that View
        TextView numbers = (TextView) findViewById(R.id.meal_type_01);
        numbers.setOnClickListener(new View.OnClickListener(){
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                String message = "Open the list of " + getString(R.string.meal_name_01);
                Toast.makeText(view.getContext(),message, Toast.LENGTH_SHORT).show();
                Intent numbersIntent = new Intent(MainActivity.this, MealType01.class);
                startActivity(numbersIntent);
            }
        });

    }
}
