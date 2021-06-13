package com.example.ibu_androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.Random;

public class StreetsActivity extends AppCompatActivity {

    //Declare person object
    Person person;
    BottomNavigationView bottomNavigationView;
    FirstTime firstTimeStart;

    //Variables that define texts
    TextView daysOldView;
    TextView yearsOldView;
    TextView daysLeftView;
    TextView yearsLeftView;
    TextView informationView;
    TextView moneyView;
    TextView dailyCostsView;

    //Dialog to show when it's game over.
    Dialog game_over_dialog;
    Dialog game_tutorial;

    //Buttons for different jobs
    Button job1;
    Button job2;
    Button job3;
    Button job4;

    //Button for buying items
    Button buy_item1;
    Button buy_item2;
    Button buy_item3;

    //Button for upgrade items
    Button upgrade_item1;
    Button upgrade_item2;
    Button upgrade_item3;
    Button upgrade_item4;

    //Current money
    double money;

    //Daily costs of living
    double dailyCosts;

    //Current user age and time left to live
    int daysOld;
    int yearsOld;
    int daysLeft;
    int yearsLeft;

    //Lower bounds for money generation
    int job1lower = 1;
    int job2lower = 2;
    int job3lower = 4;
    int job4lower = 6;

    //Variables used to count how many upgrades a certain item has, and another variable to show percentage gain
    //Begging job (job 1)
    int job1_percentage_increase_text = 50;

    //Guitar job (job 2)
    int job2_percentage_increase_text = 25;

    //Courier job (job 3)
    int job3_percentage_increase_text = 25;

    //Graffiti artist job (job 4)
    int job4_percentage_increase_text = 25;

    //TextViews for showing job percentage increases
    TextView job1_upgrade_subtext;
    TextView job2_upgrade_subtext;
    TextView job3_upgrade_subtext;
    TextView job4_upgrade_subtext;

    //Used to check if the person class has already been intialized once.
    boolean firstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streets);

        //This is used to check if the class has already been initialized, so that it
        //doesn't create a new instance of person each time a new activity is started
        if(firstTimeStart.firstTime){
            person = new Person();
            firstTimeStart.firstTime = false;



        }else if(firstTimeStart.firstTime == false){
            Intent intent = getIntent();
            person = intent.getParcelableExtra("Person Object");
        }



        //Find navigation view and set it to the current activity it is in
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.streets);

        //Money the user has
        money = person.getMoney();

        //Age and days left of the user
        daysOld = person.getDaysOld();
        yearsOld = person.getYearsOld();
        daysLeft = person.getDaysLeft();
        yearsLeft = person.getYearsLeft();

        //Dialog
        game_over_dialog = new Dialog(this);
        game_tutorial = new Dialog(this);

        //Find the appbar texts
        daysOldView = findViewById(R.id.current_age_number_days);
        yearsOldView = findViewById(R.id.current_age_number_years);
        daysLeftView = findViewById(R.id.estimated_lifetime_number_days);
        yearsLeftView = findViewById(R.id.estimated_lifetime_number_years);
        moneyView = findViewById(R.id.money_number);
        dailyCostsView = findViewById(R.id.daily_costs_number);

        //Used to provide informative text for each action
        informationView = findViewById(R.id.information_text);

        //Set initial values
        daysOldView.setText("" + person.getDaysOld());
        yearsOldView.setText("" + person.getYearsOld() + ".");
        daysLeftView.setText("" + person.getDaysLeft());
        yearsLeftView.setText("" + person.getYearsLeft() + ".");
        moneyView.setText("$"+ person.getMoney());
        dailyCostsView.setText("$ "+ person.getDailyCosts());

        //TextViews for showing job percentage increases
        job1_upgrade_subtext = findViewById(R.id.item_upgrade_subtext_1);
        job2_upgrade_subtext = findViewById(R.id.item_upgrade_subtext_2);
        job3_upgrade_subtext = findViewById(R.id.item_upgrade_subtext_3);
        job4_upgrade_subtext = findViewById(R.id.item_upgrade_subtext_4);

        //Buttons for jobs
        job1 = findViewById(R.id.job_button_1);
        job2 = findViewById(R.id.job_button_2);
        job3 = findViewById(R.id.job_button_3);
        job4 = findViewById(R.id.job_button_4);

        //Buttons for buying items
        buy_item1 = findViewById(R.id.item_purchase_button_1);
        buy_item2 = findViewById(R.id.item_purchase_button_2);
        buy_item3 = findViewById(R.id.item_purchase_button_3);

        //Buttons for upgrading items
        upgrade_item1 = findViewById(R.id.upgrade_purchase_button_1);
        upgrade_item2 = findViewById(R.id.upgrade_purchase_button_2);
        upgrade_item3 = findViewById(R.id.upgrade_purchase_button_3);
        upgrade_item4 = findViewById(R.id.upgrade_purchase_button_4);

        job1_upgrade_subtext.setText("+" + person.job1_upgrade_count * job1_percentage_increase_text + "% from begging");
        job2_upgrade_subtext.setText("+" + person.job2_upgrade_count * job2_percentage_increase_text + "% from playing guitar");
        job3_upgrade_subtext.setText("+" + person.job3_upgrade_count * job3_percentage_increase_text + "% from courier job");
        job4_upgrade_subtext.setText("+" + person.job4_upgrade_count * job4_percentage_increase_text + "% from graffiti artist job");

        Log.i("nba2k21", "onCreate: " + person.job1_upgrade_count + "AND" + job1_percentage_increase_text);
        //Defining functions for each begging job
        //Begging job
        job1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dailyProfit = Math.round(randomizeMoneyGain(job1lower,person.job1upper) * 100) / 100;
                money = person.getMoney() + dailyProfit - person.getDailyCosts();
                updateMoney(money);
                updateAge(1);
                informationView.setText("You earned $" + dailyProfit);
            }
        });

        //Playing guitar job
        job2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.hasGuitar == false){
                    informationView.setText("You need to buy a guitar first");
                }

                if(person.hasGuitar == true){
                    double dailyProfit = Math.round(randomizeMoneyGain(job2lower,person.job2upper) * 100) / 100;
                    money = person.getMoney() + dailyProfit - person.getDailyCosts();
                    updateMoney(money);
                    updateAge(1);
                    informationView.setText("You earned $" + dailyProfit);
                }

            }
        });

        //Courier job
        job3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.hasBicycle == false){
                    informationView.setText("You need to purchase a bicycle first.");
                }

                if(person.hasBicycle == true){
                    double dailyProfit = Math.round(randomizeMoneyGain(job3lower,person.job3upper) * 100) / 100;
                    money = person.getMoney() + dailyProfit - person.getDailyCosts();
                    updateMoney(money);
                    updateAge(1);
                    informationView.setText("You earned $" + dailyProfit);
                }
            }
        });

        //Graffiti artist job
        job4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.hasPaint == false){
                    informationView.setText("You need to purchase paint first.");
                }

                if(person.hasPaint == true){
                    double dailyProfit = Math.round(randomizeMoneyGain(job4lower,person.job4upper) * 100) / 100;
                    money = person.getMoney() + dailyProfit - person.getDailyCosts();
                    updateMoney(money);
                    updateAge(1);
                    informationView.setText("You earned $" + dailyProfit);
                }
            }
        });

        if(person.hasGuitar == true){
            buy_item1.setEnabled(false);
        }

        //Buttons for buying items
        //Purchase guitar button
        buy_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 150 < 0){
                    informationView.setText("You don't have enough money to buy the guitar");
                }else{
                    person.setHasGuitar(true);
                    buy_item1.setEnabled(false);
                    money = person.getMoney() - 150;
                    updateMoney(money);
                    informationView.setText("You purchased a guitar!");
                }
            }
        });

        if(person.hasBicycle == true){
            buy_item2.setEnabled(false);
        }

        //Purchase bicycle button
        // $1/day maintanecne
        buy_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 500 < 0){
                    informationView.setText("You don't have enough money to purchase a bycicle");
                }else{
                    person.setHasBicycle(true);
                    buy_item2.setEnabled(false);
                    money = person.getMoney() - 500;
                    updateMoney(money);
                    dailyCosts = person.getDailyCosts() + 1;
                    person.setDailyCosts(dailyCosts);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    informationView.setText("You purchased a bicycle!");
                }
            }
        });

        if(person.hasPaint == true){
            buy_item3.setEnabled(false);
        }

        //Purchase paint button
        buy_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getMoney() - 750 < 0){
                    informationView.setText("You don't have enough money to purchase paint");
                }else{
                    person.setHasPaint(true);
                    buy_item3.setEnabled(false);
                    money = person.getMoney() - 750;
                    updateMoney(money);
                    dailyCosts = person.getDailyCosts() + 2;
                    person.setDailyCosts(dailyCosts);
                    dailyCostsView.setText("$" + person.getDailyCosts());
                    informationView.setText("You purchased paint!");
                }
            }
        });

        if(person.job1_upgrade_count == 6){
            upgrade_item1.setEnabled(false);
        }

        //Buttons for upgrading items
        //Purchase pet button (Upgrades begging job)
        upgrade_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.getMoney() - 250 < 0){
                    informationView.setText("You don't have enough money");
                }else if(person.job1_upgrade_count == 6){
                    upgrade_item1.setEnabled(false);
                    job1_upgrade_subtext.setText("Maximum upgrade");
                }else{
                    //Increase how many times the user purchased the upgrade
                    person.job1_upgrade_count++;

                    //Increase the upper bound of maximum profit for that job
                    person.job1upper = (int) Math.floor(person.job1upper * 1.5);
                    job1_upgrade_subtext.setText("+"+job1_percentage_increase_text * person.job1_upgrade_count +"% from begging");
                    informationView.setText("You purchased a pet!");

                    //Update money
                    money = person.getMoney() - 300;
                    updateMoney(money);
                }

            }
        });

        if(person.job2_upgrade_count == 6){
            upgrade_item2.setEnabled(false);
        }

        //Upgrades guitar job income
        upgrade_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.hasGuitar == false){
                    informationView.setText("You need to buy a guitar first");
                }else if(person.getMoney() - 300 < 0){
                    informationView.setText("You don't have enough money");
                }else if(person.job2_upgrade_count == 6){
                    upgrade_item2.setEnabled(false);
                    job2_upgrade_subtext.setText("Maximum upgrade");
                }else{
                    //Increase how many times the user purchased the upgrade
                    person.job2_upgrade_count++;
                    //Increase the number for the textview to show how much % gain there is

                    //Increase the upper bound of maximum profit for that job
                    person.job2upper = (int) Math.floor(person.job2upper * 1.25);
                    job2_upgrade_subtext.setText("+"+job2_percentage_increase_text * person.job2_upgrade_count +"% from playing guitar");
                    informationView.setText("You learned a new song and improved your skills!");

                    //Update money
                    money = person.getMoney() - 300;
                    updateMoney(money);
                }

            }
        });

        if(person.job3_upgrade_count == 6){
            upgrade_item3.setEnabled(false);
        }

        //Upgrades courier job income
        upgrade_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.hasBicycle == false){
                    informationView.setText("You need to buy a bicycle first.");
                }else if(person.getMoney() - 500 < 0){
                    informationView.setText("You don't have enough money.");
                }else if(person.job3_upgrade_count == 5){
                    upgrade_item3.setEnabled(false);
                    job3_upgrade_subtext.setText("Maximum upgrade");
                }else{
                    //Increase how many times the user purchased the upgrade
                    person.job3_upgrade_count++;
                    //Increase the number for the textview to show how much % gain there is

                    //Increase the upper bound of maximum profit for that job
                    person.job3upper = (int) Math.floor(person.job3upper * 1.25);
                    job3_upgrade_subtext.setText("+"+job3_percentage_increase_text * person.job3_upgrade_count +"% from doing courier work");
                    informationView.setText("You greased up your bike!");
                    money = person.getMoney() - 350;
                    updateMoney(money);
                }
            }
        });

        if(person.job4_upgrade_count == 6){
            upgrade_item4.setEnabled(false);
        }

        upgrade_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.hasPaint == false){
                    informationView.setText("You need to buy some paint first.");
                }else if(person.getMoney() - 750 < 0){
                    informationView.setText("You don't have enough money.");
                }else if(person.job4_upgrade_count == 5){
                    upgrade_item4.setEnabled(false);
                    job4_upgrade_subtext.setText("Maximum upgrade");
                }else{

                    //Increase how many times the user purchased the upgrade
                    person.job4_upgrade_count++;
                    //Increase the number for the textview to show how much % gain there is
                    //Increase the upper bound of maximum profit for that job
                    person.job4upper = (int) Math.floor(person.job4upper * 1.25);;

                    job4_upgrade_subtext.setText("+"+job4_percentage_increase_text * person.job4_upgrade_count +"% from graffitis");

                    informationView.setText("You learned some new art!");
                    money = person.getMoney() - 500;
                    updateMoney(money);
                }
            }
        });



        //Bottom navigation listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = null;
                switch (item.getItemId()){
                    case R.id.health:
                        i = new Intent(getApplicationContext(), HealthActivity.class);
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

    //Update the money that the user has
    public void updateMoney(double money){
        person.setMoney(money);
        moneyView.setText("$" + person.getMoney());
    }

    //Logic for updating days and years
    public void updateAge(int days){
        daysOld = daysOld + days;
        //If the age and years is the same as the expected age, then show a dialog.
        if((person.getDaysOld() == person.getDaysLeft() && person.getYearsOld() == person.getYearsLeft()) || person.getMoney() <= -500){
            TextView text_close;
            Button try_again_button;
            game_over_dialog.setContentView(R.layout.gameover_popup);
            text_close = game_over_dialog.findViewById(R.id.close_popup);
            try_again_button = game_over_dialog.findViewById(R.id.try_again_button);

            text_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game_over_dialog.dismiss();
                }
            });

            try_again_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   person = new Person();
                   game_over_dialog.dismiss();

                   FirstTime.firstTime = true;

                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

            game_over_dialog.show();

        }else if(daysOld > 365){
            daysOld = daysOld - 365;
            yearsOld = yearsOld + 1;

            daysOldView.setText("" + daysOld);
            yearsOldView.setText("" + yearsOld + ".");

            person.setDaysOld(daysOld);
            person.setYearsOld(yearsOld);
            updateMoney(money);
        }else if(daysOld == 365){
            daysOld = 1;
            yearsOld = yearsOld + 1;

            daysOldView.setText("" +  daysOld);
            yearsOldView.setText("" + yearsOld + ".");

            person.setDaysOld(daysOld);
            person.setYearsOld(yearsOld);
            updateMoney(money);
        }else{
            daysOldView.setText("" + daysOld);
            person.setDaysOld(daysOld);
            person.setYearsOld(yearsOld);
            updateMoney(money);
        }
    }

    //Randomize daily profit
    public double randomizeMoneyGain(double min, double max){
        double random = min + Math.random() * (max - min);
        return random;
    }




}