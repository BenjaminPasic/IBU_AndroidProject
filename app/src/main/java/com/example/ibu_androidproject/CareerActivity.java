package com.example.ibu_androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.Random;

public class CareerActivity extends AppCompatActivity {

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
    TextView gedPercentageView;
    TextView gedDaysRequiredForStudy;
    TextView scholarshipPercentageView;
    TextView scholarshipDaysRequiredView;
    TextView promotionUpgradeRateView;

    Dialog game_over_dialog;
    Dialog are_you_sure_dialog;

    //Buttons
    Button attempt_ged_button;
    Button study_for_ged;
    Button attempt_scholarship_exam;
    Button study_scholarship_exam;
    Button apply_to_bachelors;
    Button bachelors_student_loan;
    Button apply_to_masters;
    Button masters_student_loan;
    Button apply_to_phd;
    Button pdh_student_loan;
    Button upgrade_promotion_rate;

    //Job buttons
    Button fast_food_job;
    Button sales_rep_job;
    Button business_assoc_job;
    Button researcher_job;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career);

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

        //GED views
        gedPercentageView = findViewById(R.id.ged_exam_subtitle);
        gedDaysRequiredForStudy = findViewById(R.id.ged_day_required);

        //Promotion rate text
        promotionUpgradeRateView = findViewById(R.id.promotion_upgrade_cost);

        //Scholarship views
        scholarshipDaysRequiredView = findViewById(R.id.scholarship_days_required);

        //Scolarship views
        scholarshipPercentageView = findViewById(R.id.scholarship_exam_subtitle);

        //Bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set career as active in bottom nav
        bottomNavigationView.setSelectedItemId(R.id.career);

        //Game over dialog
        game_over_dialog = new Dialog(this);
        are_you_sure_dialog = new Dialog(this);

        //Info text
        informationView = findViewById(R.id.information_text);

        //Assign all variables from the other activity to the current one
        daysOldView.setText("" + person.getDaysOld());
        yearsOldView.setText("" + person.getYearsOld() + ".");
        daysLeftView.setText("" + person.getDaysLeft());
        yearsLeftView.setText("" + person.getYearsLeft() + ".");
        moneyView.setText("$"+ person.getMoney());
        dailyCostsView.setText("$ "+ person.getDailyCosts());

        //Assign buttons
        attempt_ged_button = findViewById(R.id.attempt_ged_button);
        attempt_scholarship_exam = findViewById(R.id.scholarship_exam_button);
        apply_to_bachelors = findViewById(R.id.finish_bachelors_button);
        bachelors_student_loan = findViewById(R.id.get_student_loan_bachelors_button);
        apply_to_masters = findViewById(R.id.finish_masters_button);
        masters_student_loan = findViewById(R.id.get_student_loan_masters_button);
        apply_to_phd = findViewById(R.id.finish_phd_button);
        pdh_student_loan = findViewById(R.id.get_student_loan_phd_button);
        upgrade_promotion_rate = findViewById(R.id.promotion_upgrade_button);

        //Job buttons
        fast_food_job = findViewById(R.id.fast_food_job_button);
        sales_rep_job = findViewById(R.id.sales_rep_job_button);
        business_assoc_job = findViewById(R.id.business_assoc_job_button);
        researcher_job = findViewById(R.id.researcher_job_button);

        //Upgrades
        study_for_ged = findViewById(R.id.study_ged_button);
        study_scholarship_exam = findViewById(R.id.study_scholarship_button);

        //Assign progress bar
        progressBar = findViewById(R.id.pBar);
        progressBar.setProgress((int) person.currentClicks);

        //Days required for the next improvement for passing
        gedDaysRequiredForStudy.setText("" + person.gedStudyDaysRequired + " Days");
        scholarshipDaysRequiredView.setText("" + person.scholarshipStudyDaysRequired + " Days");

        //Assigns percentage display for the user
        gedPercentageView.setText(person.gedPassChance+"% to pass the GED");
        scholarshipPercentageView.setText(person.scholarshipPassChance+"% to get a scholarship");

        //Assign users true promotion rate
        promotionUpgradeRateView.setText(person.nextPromotionDaysRequired+" Days");


        if(person.hasPassedGed == true){
            attempt_ged_button.setEnabled(false);
        }

        attempt_ged_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.getMoney() - 125 < 0){
                    informationView.setText("You don't have enough money to attempt the GED");
                }else if(person.gedPassChance == 0){
                    informationView.setText("You need to study a bit before attempting the GED");
                }else{

                    Random r = new Random();
                    int randomInt = r.nextInt(100) + 1;

                    if(person.gedPassChance >= randomInt){
                        informationView.setText("You passed you GED Exam!");
                        person.setHasPassedGed(true);
                        attempt_ged_button.setEnabled(false);
                        updateMoney(person.getMoney() - 125);
                        updateAge(1);
                    }else if(person.gedPassChance < randomInt){
                        informationView.setText("You failed your GED, better luck next time!");
                        updateMoney(person.getMoney() - 125);
                        updateAge(1);
                    }
                }
            }
        });

        if(person.gedPassChance == 100){
            study_for_ged.setEnabled(false);
        }

        study_for_ged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAge(person.gedStudyDaysRequired);

                person.incrementGedStudyDays();
                informationView.setText("You studied hard for your GED");

                person.incrementGedChance();
                gedPercentageView.setText("+"+person.gedPassChance+"% to pass the GED");

                gedDaysRequiredForStudy.setText(person.gedStudyDaysRequired + " Days");

                if(person.gedPassChance == 100){
                    study_for_ged.setEnabled(false);
                }
            }
        });

        if(person.hasScholarship == true){
            attempt_scholarship_exam.setEnabled(false);
        }

        attempt_scholarship_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.hasPassedGed == false){
                    informationView.setText("You need to pass the GED first!");
                }else if(person.getMoney() - 50 < 0){
                    informationView.setText("You don't have enough money for the scolarship exam");
                }else if(person.scholarshipPassChance == 0){
                    informationView.setText("You need to study before taking the scolarship exam");
                }else{

                    Random r = new Random();
                    int randomInt = r.nextInt(100) + 1;

                    if(person.scholarshipPassChance >= randomInt){
                        informationView.setText("You received a scholarship!");
                        person.setHasScholarship(true);
                        attempt_scholarship_exam.setEnabled(false);
                        updateMoney(person.getMoney() - 50);
                    }else if(person.scholarshipPassChance < randomInt){
                        informationView.setText("You failed your scholarship exam, better luck next time!");
                        updateMoney(person.getMoney() - 50);
                    }
                }
            }
        });

        if(person.scholarshipPassChance == 100){
            study_scholarship_exam.setEnabled(false);
        }

        study_scholarship_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateAge(person.scholarshipStudyDaysRequired);

                person.incrementScholarshipStudyDays();
                informationView.setText("You studied hard for your scholarship exam");

                person.incrementScholarshipChance();
                scholarshipPercentageView.setText("+ "+person.scholarshipPassChance+"% to pass the GED");

                scholarshipDaysRequiredView.setText(person.scholarshipStudyDaysRequired + " Days");

                if(person.scholarshipPassChance == 100){
                    study_scholarship_exam.setEnabled(false);
                }

            }
        });

        if(person.hasBachelors == true){
            apply_to_bachelors.setEnabled(false);
            bachelors_student_loan.setEnabled(false);
        }

        apply_to_bachelors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED before applying to college");
                }else if(person.hasScholarship){
                    person.setHasBachelors(true);
                    apply_to_bachelors.setEnabled(false);
                    bachelors_student_loan.setEnabled(false);
                    updateAge(1460);
                    calculateYearsAndDaysLeft(2920);
                }else if(person.getMoney() < 54896){
                    informationView.setText("You don't have money to pay for university! Maybe get a loan?");
                }else{
                    person.setHasBachelors(true);
                    apply_to_bachelors.setEnabled(false);
                    bachelors_student_loan.setEnabled(false);
                    updateMoney(person.getMoney() - 54896);
                    informationView.setText("You received your bachelors degree! Congratulations!");
                    updateAge(1460);
                    calculateYearsAndDaysLeft(2920);
                }
            }
        });

        bachelors_student_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.hasPassedGed == true){

                    Button yes;
                    Button no;
                    are_you_sure_dialog.setContentView(R.layout.are_you_sure_popup);
                    yes = are_you_sure_dialog.findViewById(R.id.yes_button);
                    no = are_you_sure_dialog.findViewById(R.id.no_button);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            person.setDailyCosts(person.getDailyCosts() + 37);
                            dailyCostsView.setText("$" + person.getDailyCosts());
                            person.setHasBachelors(true);
                            person.setHasBachelorsDebt(true);
                            informationView.setText("You got your bachelors degree!");
                            updateAge(1460);
                            calculateYearsAndDaysLeft(2920);
                            bachelors_student_loan.setEnabled(false);
                            apply_to_bachelors.setEnabled(false);
                            are_you_sure_dialog.dismiss();
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            are_you_sure_dialog.dismiss();
                        }
                    });
                    are_you_sure_dialog.show();

                }else{
                    informationView.setText("You need to pass your GED first.");
                }


            }
        });

        if(person.hasMasters == true){
            masters_student_loan.setEnabled(false);
            apply_to_masters.setEnabled(false);
        }

        apply_to_masters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED first.");
                }else if(person.hasBachelors == false){
                    informationView.setText("You need to get a bachelors degree before the master degree");
                }else if(person.getMoney() < 61126){
                    informationView.setText("You don't have enough money to pay for a masters degree. Maybe get a loan?");
                }else{
                    updateMoney(person.getMoney() - 61126);
                    informationView.setText("You received your masters degree! Congratulations!");
                    apply_to_masters.setEnabled(false);
                    person.setHasMasters(true);
                    masters_student_loan.setEnabled(false);
                    updateAge(740);
                    calculateYearsAndDaysLeft(3240);
                }

            }
        });

        masters_student_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED exam first.");
                }else if(person.hasBachelors == false){
                    informationView.setText("You need to a bachelors degree to get a masters degree!");
                }else{

                    Button yes;
                    Button no;
                    are_you_sure_dialog.setContentView(R.layout.are_you_sure_popup);
                    yes = are_you_sure_dialog.findViewById(R.id.yes_button);
                    no = are_you_sure_dialog.findViewById(R.id.no_button);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            person.setDailyCosts(person.getDailyCosts() + 82);
                            dailyCostsView.setText("$" + person.getDailyCosts());
                            person.setHasMasters(true);
                            person.setHasMastersDebt(true);
                            informationView.setText("You got your masters degree!");
                            updateAge(740);
                            calculateYearsAndDaysLeft(3240);
                            masters_student_loan.setEnabled(false);
                            apply_to_masters.setEnabled(false);
                            are_you_sure_dialog.dismiss();
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            are_you_sure_dialog.dismiss();
                        }
                    });
                    are_you_sure_dialog.show();
                }


            }
        });

        if(person.hasPhd == true){
            apply_to_phd.setEnabled(false);
            pdh_student_loan.setEnabled(false);
        }

        apply_to_phd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED first.");
                }else if(person.hasBachelors == false){
                    informationView.setText("You need to get a bachelors degree before the master degree");
                }else if(person.hasMasters == false){
                    informationView.setText("You need to get a masters degree before getting a PHD");
                }else if(person.getMoney() - 72202 < 0){
                    informationView.setText("You don't have enough to pay for a PHD. Maybe get a loan?");
                }else{
                    updateMoney(person.getMoney() - 72202);
                    informationView.setText("You received your PHD! Congratulations!");
                    apply_to_phd.setEnabled(false);
                    pdh_student_loan.setEnabled(false);
                    person.setHasPhd(true);
                    updateAge(740);
                    calculateYearsAndDaysLeft(1440);
                }

            }
        });

        pdh_student_loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED first.");
                }else if(person.hasBachelors == false){
                    informationView.setText("You need to get a bachelors degree before the master degree");
                }else if(person.hasMasters == false){
                    informationView.setText("You need to get a masters degree before getting a PHD");
                }else{

                    Button yes;
                    Button no;
                    are_you_sure_dialog.setContentView(R.layout.are_you_sure_popup);
                    yes = are_you_sure_dialog.findViewById(R.id.yes_button);
                    no = are_you_sure_dialog.findViewById(R.id.no_button);

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            person.setDailyCosts(person.getDailyCosts() + 97);
                            dailyCostsView.setText("$" + person.getDailyCosts());
                            person.setHasPhd(true);
                            person.setHasPhdDebt(true);
                            informationView.setText("You received your PHD!");
                            updateAge(740);
                            calculateYearsAndDaysLeft(3240);
                            pdh_student_loan.setEnabled(false);
                            apply_to_phd.setEnabled(false);
                            are_you_sure_dialog.dismiss();
                        }
                    });
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            are_you_sure_dialog.dismiss();
                        }
                    });
                    are_you_sure_dialog.show();
                }

                }

            });

        if(person.hasFastFoodJob == true){
            fast_food_job.setText("Work");
        }

        fast_food_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fast_food_job.getText().toString().equalsIgnoreCase("work")){

                    if(person.currentClicks >= person.clicksNeededForPromotion){
                        person.setFastFoodJobPay(person.fastFoodJobPay + 5);
                        person.incrementClicksNeededForPromotion(100);
                        person.setCurrentClicks(0);
                        progressBar.setMax(person.clicksNeededForPromotion);
                        progressBar.setProgress(0);
                    }

                    //Increments how many clicks and adds a bonus if upgrade is active.
                    person.incrementCurrentClicks((int) person.promotionUpgradeClicks + 1);
                    progressBar.setProgress((int) person.currentClicks);
                    updateMoney(person.getMoney() + person.fastFoodJobPay - person.getDailyCosts());
                    updateAge(1);
                    informationView.setText("You finished work and got $" + person.fastFoodJobPay + " today.");
                }else if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED to apply to this job");
                }else{
                    informationView.setText("You got hired!");
                    person.setHasFastFoodJob(true);
                    fast_food_job.setText("Work");
                    progressBar.setMax(person.clicksNeededForPromotion);
                    progressBar.setProgress(0);
                    resetPromotionRates();
                }

            }
        });

        upgrade_promotion_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                person.incrementClickUpgrade();
                person.incrementNextPromotionDaysRequired(person.nextPromotionDaysRequired);
                promotionUpgradeRateView.setText(person.nextPromotionDaysRequired + " Days");
                updateAge(person.nextPromotionDaysRequired);
                informationView.setText("You worked overtime, the boss will notice you more!");
            }
        });

        if(person.hasSalesRepJob == true){
            sales_rep_job.setText("Work");
        }

        sales_rep_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sales_rep_job.getText().toString().equalsIgnoreCase("work")){

                    if(person.currentClicks >= person.clicksNeededForPromotion){
                        person.setSalesRepJobPay(person.salesRepJobPay + 8);
                        person.incrementClicksNeededForPromotion(150);
                        person.setCurrentClicks(0);
                        progressBar.setMax(person.clicksNeededForPromotion);
                        progressBar.setProgress(0);
                    }

                    //Increments how many clicks and adds a bonus if upgrade is active.
                    person.incrementCurrentClicks((int) person.promotionUpgradeClicks + 1);
                    progressBar.setProgress((int) person.currentClicks);
                    updateMoney(person.getMoney() + person.salesRepJobPay - person.getDailyCosts());
                    updateAge(1);
                    informationView.setText("You finished work and got $" + person.salesRepJobPay + " today.");
                }else if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED to apply to this job");
                }else if(person.hasBachelors == false){
                    informationView.setText("You need to get your Bachelors degree for this job.");
                }else{
                    informationView.setText("You got hired!");
                    person.setHasSalesRepJob(true);
                    sales_rep_job.setText("Work");
                    progressBar.setMax(person.clicksNeededForPromotion);
                    progressBar.setProgress(0);
                    resetPromotionRates();
                }

            }

        });

        if(person.hasBusinessAssocJob == true){
            business_assoc_job.setText("Work");
        }

        business_assoc_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(business_assoc_job.getText().toString().equalsIgnoreCase("work")){

                    if(person.currentClicks >= person.clicksNeededForPromotion){
                        person.setBusinessAssocJobPay(person.businessAssocJobPay + 13);
                        person.incrementClicksNeededForPromotion(250);
                        person.setCurrentClicks(0);
                        progressBar.setMax(person.clicksNeededForPromotion);
                        progressBar.setProgress(0);
                    }

                    //Increments how many clicks and adds a bonus if upgrade is active.
                    person.incrementCurrentClicks((int) person.promotionUpgradeClicks + 1);
                    progressBar.setProgress((int) person.currentClicks);
                    updateMoney(person.getMoney() + person.businessAssocJobPay - person.getDailyCosts());
                    updateAge(1);
                    informationView.setText("You finished work and got $" + person.businessAssocJobPay + " today.");
                }else if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED to apply to this job");
                }else if(person.hasBachelors == false){
                    informationView.setText("You need to get your Bachelors degree for this job.");
                }else if(person.hasMasters == false){
                    informationView.setText("You need to get a masters degree for this job.");
                }else{
                    informationView.setText("You got hired!");
                    person.setHasBusinessAssocJob(true);
                    business_assoc_job.setText("Work");
                    progressBar.setMax(person.clicksNeededForPromotion);
                    progressBar.setProgress(0);
                    resetPromotionRates();
                }

            }
        });

        researcher_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(researcher_job.getText().toString().equalsIgnoreCase("work")){

                    if(person.currentClicks >= person.clicksNeededForPromotion){
                        person.setReasercherJobPay(person.businessAssocJobPay + 20);
                        person.incrementClicksNeededForPromotion(300);
                        person.setCurrentClicks(0);
                        progressBar.setMax(person.clicksNeededForPromotion);
                        progressBar.setProgress(0);
                    }

                    //Increments how many clicks and adds a bonus if upgrade is active.
                    person.incrementCurrentClicks((int) person.promotionUpgradeClicks + 1);
                    progressBar.setProgress((int) person.currentClicks);
                    updateMoney(person.getMoney() + person.reasercherJobPay - person.getDailyCosts());
                    updateAge(1);
                    informationView.setText("You finished work and got $" + person.reasercherJobPay + " today.");
                }else if(person.hasPassedGed == false){
                    informationView.setText("You need to pass your GED to apply to this job");
                }else if(person.hasBachelors == false){
                    informationView.setText("You need to get your Bachelors degree for this job.");
                }else if(person.hasMasters == false){
                    informationView.setText("You need to get a masters degree for this job.");
                }else if(person.hasPhd == false){
                    informationView.setText("You got hired!");
                    person.setHasResearcherJob(true);
                    researcher_job.setText("Work");
                    progressBar.setMax(person.clicksNeededForPromotion);
                    progressBar.setProgress(0);
                    resetPromotionRates();
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
                    case R.id.health:
                        i = new Intent(getApplicationContext(), HealthActivity.class);
                        i.putExtra("Person Object", person);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });

    }

    public void resetPromotionRates(){
        person.currentClicks = 0;
        person.setFastFoodJobPay(40);
        person.setSalesRepJobPay(70);
        person.setBusinessAssocJobPay(120);
        person.setReasercherJobPay(200);
    }

    //Updates money
    public void updateMoney(double money){
        person.setMoney(money);
        moneyView.setText("$" + person.getMoney());
    }

    //Logic for updating days and years
    public void updateAge(int days){

        //Count the debt from bachelors
        if(person.hasBachelorsDebt == true){
            int d = person.getDaysPayedForBachelors();
            if(d == 1460){
                person.setHasBachelorsDebt(false);
                informationView.setText("You paid off your bachelors degree!");
                person.setDailyCosts(person.getDailyCosts() - 37);
                dailyCostsView.setText("" + person.getDailyCosts());
            }else{
                person.incrementDaysPayedForBachelors();
            }
        }

        if(person.hasMastersDebt == true){
            int d = person.getDaysPayedForMasters();

            if(d == 740){
                person.setHasMastersDebt(false);
                informationView.setText("You paid off your masters degree!");
                person.setDailyCosts(person.getDailyCosts() - 82);
                dailyCostsView.setText("" + person.getDailyCosts());
            }else{
                person.incrementDaysPayedForMasters();
            }
        }

        if(person.hasPhdDebt == true){
            int d = person.getDaysPayedForPhd();

            if(d == 1460){
                person.setHasPhdDebt(false);
                informationView.setText("You paid off your PHD!");
                person.setDailyCosts(person.getDailyCosts() - 97);
                dailyCostsView.setText("" + person.getDailyCosts());
            }else{
                person.incrementDaysPayedForPhd();
            }

        }

        int daysOld = person.getDaysOld() + days;
        int yearsOld;
        //If the age and years is the same as the expected age, then show a dialog.
        if((person.getDaysOld() >= person.getDaysLeft() && person.getYearsOld() >= person.getYearsLeft()) || person.getMoney() <= -500){
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

                    daysOldView.setText("" + person.getDaysOld());
                    yearsOldView.setText("" + person.getYearsOld() + ".");
                    daysLeftView.setText("" + person.getDaysLeft());
                    yearsLeftView.setText("" + person.getYearsLeft() + ".");
                    moneyView.setText("$"+ person.getMoney());
                    dailyCostsView.setText("$ "+ person.getDailyCosts());

                    game_over_dialog.dismiss();
                }
            });
            game_over_dialog.show();
        }else if(daysOld > 365){
            int tempYears = daysOld/ 365;
            daysOld = daysOld - 365 * tempYears;
            yearsOld = person.getYearsOld() + tempYears;

            daysOldView.setText("" + daysOld);
            yearsOldView.setText("" + yearsOld + ".");

            person.setDaysOld(daysOld);
            person.setYearsOld(yearsOld);
        }else if(daysOld == 365){
            daysOld = 1;
            yearsOld = person.getYearsOld() + 1;

            daysOldView.setText("" +  daysOld);
            yearsOldView.setText("" + yearsOld + ".");

            person.setDaysOld(daysOld);
            person.setYearsOld(yearsOld);
        }else{
            daysOldView.setText("" + person.getDaysOld());
            person.setDaysOld(daysOld);
        }
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