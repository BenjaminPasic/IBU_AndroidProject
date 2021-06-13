package com.example.ibu_androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HealthActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Person person;

    //Variables that define texts
    TextView daysOldView;
    TextView yearsOldView;
    TextView daysLeftView;
    TextView yearsLeftView;
    TextView informationView;
    TextView moneyView;
    TextView dailyCostsView;

    //current money
    double money;

    //Buttons for purchasing new homes
    Button purchase_house1;
    Button purchase_house2;
    Button purchase_house3;
    Button purchase_house4;
    Button purchase_house5;

    //Buttons for purchasing foods
    Button purchase_dish1;
    Button purchase_dish2;
    Button purchase_dish3;
    Button purchase_dish4;
    Button purchase_dish5;

    //Purchase vacation
    Button purchase_vacation1;
    Button purchase_vacation2;
    Button purchase_vacation3;
    Button purchase_vacation4;
    Button purchase_vacation5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        //Get the person object instance from the activity we switched from
        Intent intent = getIntent();
        person = intent.getParcelableExtra("Person Object");

        //Find the appbar texts
        daysOldView = findViewById(R.id.current_age_number_days);
        yearsOldView = findViewById(R.id.current_age_number_years);
        daysLeftView = findViewById(R.id.estimated_lifetime_number_days);
        yearsLeftView = findViewById(R.id.estimated_lifetime_number_years);
        moneyView = findViewById(R.id.money_number);
        dailyCostsView = findViewById(R.id.daily_costs_number);

        //Info text
        informationView = findViewById(R.id.information_text);

        //Assign all variables from the other activity to the current one
        daysOldView.setText("" + person.getDaysOld());
        yearsOldView.setText("" + person.getYearsOld() + ".");
        daysLeftView.setText("" + person.getDaysLeft());
        yearsLeftView.setText("" + person.getYearsLeft() + ".");
        moneyView.setText("$"+ person.getMoney());
        dailyCostsView.setText("$ "+ person.getDailyCosts());

        //Assign current days left object to variable
        //daysLeft = person.getDaysLeft();
        //yearsLeft = person.getYearsLeft();

        //Assign buttons for purchasing homes
        purchase_house1 = findViewById(R.id.purchase_first_home);
        purchase_house2 = findViewById(R.id.purchase_second_home);
        purchase_house3 = findViewById(R.id.purchase_third_home);
        purchase_house4 = findViewById(R.id.purchase_fourth_home);
        purchase_house5 = findViewById(R.id.purchase_fifth_home);

        //Assign buttons for purchasing foods
        purchase_dish1 = findViewById(R.id.purchase_first_dish);
        purchase_dish2 = findViewById(R.id.purchase_second_dish);
        purchase_dish3 = findViewById(R.id.purchase_third_dish);
        purchase_dish4 = findViewById(R.id.purchase_fourth_dish);
        purchase_dish5 = findViewById(R.id.purchase_fifth_dish);

        //Assign buttons for purchasing vacations
        purchase_vacation1 = findViewById(R.id.purchase_first_vacation);
        purchase_vacation2 = findViewById(R.id.purchase_second_vacation);
        purchase_vacation3 = findViewById(R.id.purchase_third_vacation);
        purchase_vacation4 = findViewById(R.id.purchase_fourth_vacation);
        purchase_vacation5 = findViewById(R.id.purchase_fifth_vacation);

        //Find bottom naviagation view and set it to the current activity it is in
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.health);

        //Check if the person already bouth the property once restarting the activity.
        if(person.hasTent == true){
            purchase_house1.setEnabled(false);
        }

        purchase_house1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.getMoney() - 75 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(90);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 2);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 75);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasTent(true);
                    //Disable button once clicked.
                    purchase_house1.setEnabled(false);
                }

            }
        });

        if(person.hasRoom == true){
            purchase_house2.setEnabled(false);
        }

        purchase_house2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 150 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(180);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 5);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 150);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasRoom(true);
                    //Disable button once clicked.
                    purchase_house2.setEnabled(false);
                }
            }
        });

        if(person.hasApartment == true){
            purchase_house3.setEnabled(false);
        }

        purchase_house3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 300 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(270);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 10);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 300);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasApartment(true);
                    //Disable button once clicked.
                    purchase_house3.setEnabled(false);
                }
            }
        });

        if(person.hasCondo == true){
            purchase_house4.setEnabled(false);
        }

        purchase_house4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 600 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(365);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 20);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 600);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasCondo(true);
                    //Disable button once clicked.
                    purchase_house4.setEnabled(false);
                }
            }
        });

        if(person.hasHouse == true){
            purchase_house5.setEnabled(false);
        }

        purchase_house5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 1500 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(730);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 50);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 600);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasHouse(true);
                    //Disable button once clicked.
                    purchase_house5.setEnabled(false);
                }
            }
        });

        if(person.hasJunkfood == true){
            purchase_dish1.setEnabled(false);
        }

        purchase_dish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 75 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(90);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 2);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 75);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasJunkfood(true);
                    //Disable button once clicked.
                    purchase_dish1.setEnabled(false);
                }
            }
        });

        if(person.hasHomecooking == true){
            purchase_dish2.setEnabled(false);
        }

        purchase_dish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 150 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(180);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 5);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 150);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasHomecooking(true);
                    //Disable button once clicked.
                    purchase_dish2.setEnabled(false);
                }
            }
        });

        if(person.hasBalanced == true){
            purchase_dish3.setEnabled(false);
        }

        purchase_dish3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 300 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(270);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 10);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 300);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasBalanced(true);
                    //Disable button once clicked.
                    purchase_dish3.setEnabled(false);
                }
            }
        });

        if(person.hasOrganic == true){
            purchase_dish4.setEnabled(false);
        }

        purchase_dish4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 600 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(365);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 20);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 600);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasOrganic(true);
                    //Disable button once clicked.
                    purchase_dish4.setEnabled(false);
                }
            }
        });

        if(person.hasPersonalChef == true){
            purchase_dish5.setEnabled(false);
        }

        purchase_dish5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 1500 < 0){
                    informationView.setText("You don't have enough money to buy this.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(730);
                    //Add daily costs and apply it to textview
                    person.setDailyCosts(person.getDailyCosts() + 50);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    //Update users money
                    person.setMoney(person.getMoney() - 1500);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasPersonalChef(true);
                    //Disable button once clicked.
                    purchase_dish5.setEnabled(false);
                }
            }
        });

        if(person.hasDayTrip == true){
            purchase_vacation1.setEnabled(false);
        }

        purchase_vacation1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 100 < 0){
                    informationView.setText("You don't have enough money to travel here.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(90);
                    //Update users money
                    person.setMoney(person.getMoney() - 100);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasDayTrip(true);
                    //Disable button once clicked.
                    purchase_vacation1.setEnabled(false);
                }
            }
        });

        if(person.hasWeekend == true){
            purchase_vacation2.setEnabled(false);
        }

        purchase_vacation2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 500 < 0){
                    informationView.setText("You don't have enough money to travel here.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(180);
                    //Update users money
                    person.setMoney(person.getMoney() - 500);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasWeekend(true);
                    //Disable button once clicked.
                    purchase_vacation2.setEnabled(false);
                }
            }
        });

        if(person.hasEurope == true){
            purchase_vacation3.setEnabled(false);
        }

        purchase_vacation3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 2500 < 0){
                    informationView.setText("You don't have enough money to travel here.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(270);
                    //Update users money
                    person.setMoney(person.getMoney() - 2500);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasEurope(true);
                    //Disable button once clicked.
                    purchase_vacation3.setEnabled(false);
                }
            }
        });

        if(person.hasBoraBora == true){
            purchase_vacation4.setEnabled(false);
        }

        purchase_vacation4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 7500 < 0){
                    informationView.setText("You don't have enough money to travel here.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(365);
                    //Update users money
                    person.setMoney(person.getMoney() - 7500);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasBoraBora(true);
                    //Disable button once clicked.
                    purchase_vacation4.setEnabled(false);
                }
            }
        });

        if(person.hasTheWorld == true){
            purchase_vacation5.setEnabled(false);
        }

        purchase_vacation5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 50000 < 0){
                    informationView.setText("You don't have enough money to travel here.");
                }else{
                    //Calculate how many days to add
                    calculateYearsAndDaysLeft(730);
                    //Update users money
                    person.setMoney(person.getMoney() - 50000);
                    moneyView.setText("$" + person.getMoney());
                    person.setHasTheWorld(true);
                    //Disable button once clicked.
                    purchase_vacation5.setEnabled(false);
                }
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = null;
                switch (item.getItemId()){
                    case R.id.streets:
                        i = new Intent(getApplicationContext(), StreetsActivity.class);
                        i.putExtra("Person Object", person);
                        startActivity(i);
                        return true;
                    case R.id.career:
                        i = new Intent(getApplicationContext(), CareerActivity.class);
                        i.putExtra("Person Object", person);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });

    }

    public void calculateYearsAndDaysLeft(int days){

        if(days > 365){
            int tempYears = days / 365;
            int tempDays = days - (tempYears * 365);
            person.setYearsLeft(person.getYearsLeft() + tempYears);
            person.setDaysLeft(person.getDaysLeft() + tempDays);
            daysLeftView.setText("" + person.getDaysLeft());
            yearsLeftView.setText("" + person.getYearsLeft() + ".");
        }else if (days == 365){
            person.setYearsLeft(person.getYearsLeft() + 1);
            yearsLeftView.setText("" + person.getYearsLeft() + ".");
        }else{
            int tempDays = person.getDaysLeft();
            int sumDaysLeft = tempDays + days;

            if(sumDaysLeft > 365){
                person.setYearsLeft(person.getYearsLeft() + 1);
                yearsLeftView.setText("" + person.getYearsLeft() + ".");
                int daysLeft = sumDaysLeft - 365;
                person.setDaysLeft(daysLeft);
                daysLeftView.setText("" + person.getDaysLeft());
            }else{
                int daysLeft = person.getDaysLeft() + days;
                person.setDaysLeft(daysLeft);
                daysLeftView.setText("" + person.getDaysLeft());
            }
        }

    }





}