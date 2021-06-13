package com.example.ibu_androidproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Person implements Parcelable {

    //Current age in years and days
    public int daysOld;
    public int yearsOld;

    //Days left to live
    public int daysLeft;
    public int yearsLeft;

    //Current money
    public double money;
    public double dailyCosts;

    //Items the person has
    public boolean hasGuitar;
    public boolean hasBicycle;
    public boolean hasPaint;

    //Upper bound for cash randomization
    public int job1upper;
    public int job2upper;
    public int job3upper;
    public int job4upper;

    //Keeps track of how many times the job has been upgraded
    int job1_upgrade_count;
    int job2_upgrade_count;
    int job3_upgrade_count;
    int job4_upgrade_count;

    //Properties user has
    public boolean hasTent;
    public boolean hasRoom;
    public boolean hasApartment;
    public boolean hasCondo;
    public boolean hasHouse;

    //Food the user has
    public boolean hasJunkfood;
    public boolean hasHomecooking;
    public boolean hasBalanced;
    public boolean hasOrganic;
    public boolean hasPersonalChef;

    //Where the user traveled to
    public boolean hasDayTrip;
    public boolean hasWeekend;
    public boolean hasEurope;
    public boolean hasBoraBora;
    public boolean hasTheWorld;

    //Users academic achievements
    public boolean hasPassedGed;
    public boolean hasScholarship;
    public boolean hasBachelors;
    public boolean hasMasters;
    public boolean hasPhd;

    //Debts
    public boolean hasBachelorsDebt;
    public boolean hasMastersDebt;
    public boolean hasPhdDebt;

    //How many days the user has paid for debt
    public int daysPayedForBachelorsDebt;
    public int daysPayedForMastersDebt;
    public int daysPayedForPhdDebt;

    //Pass chance for exams
    public int gedPassChance;
    public int scholarshipPassChance;

    //Days required for user to gain bigger pass chance
    public int gedStudyDaysRequired;
    public int scholarshipStudyDaysRequired;

    //Current active job of the person
    public boolean hasFastFoodJob;
    public boolean hasSalesRepJob;
    public boolean hasBusinessAssocJob;
    public boolean hasResearcherJob;

    //Click needed for upgrade
    public int clicksNeededForPromotion;

    //Upgrade for clicks
    public double promotionUpgradeClicks;
    public int nextPromotionDaysRequired;
    public double currentClicks;

    //Fast food job pay
    public int fastFoodJobPay;

    //Sales rep job pay
    public int salesRepJobPay;

    //Business asoc job pay
    public int businessAssocJobPay;

    //Researcher job pay
    public int reasercherJobPay;


    public Person() {
        this.daysOld = 1;
        this.yearsOld = 18;
        this.daysLeft = 3;
        this.yearsLeft = 18;
        this.money = 90000;
        this.hasGuitar = false;
        this.hasBicycle = false;
        this.hasPaint = false;
        this.dailyCosts = 1;
        this.hasTent = false;
        this.hasRoom = false;
        this.hasApartment = false;
        this.hasCondo = false;
        this.hasHouse = false;
        this.hasJunkfood = false;
        this.hasHomecooking = false;
        this.hasBalanced = false;
        this.hasOrganic = false;
        this.hasPersonalChef = false;
        this.hasDayTrip = false;
        this.hasWeekend = false;
        this.hasEurope = false;
        this.hasBoraBora = false;
        this.hasTheWorld = false;
        this.hasPassedGed = false;
        this.hasScholarship = false;
        this.hasBachelors = false;
        this.hasBachelorsDebt = false;
        this.daysPayedForBachelorsDebt = 0;
        this.hasMasters = false;
        this.hasMastersDebt = false;
        this.daysPayedForMastersDebt = 0;
        this.gedPassChance = 0;
        this.gedStudyDaysRequired = 0;
        this.scholarshipPassChance = 0;
        this.scholarshipStudyDaysRequired = 0;
        this.hasPhd = false;
        this.hasPhdDebt = false;
        this.daysPayedForPhdDebt = 0;
        this.hasFastFoodJob = false;
        this.fastFoodJobPay = 40;
        this.promotionUpgradeClicks = 0;
        this.nextPromotionDaysRequired = 1;
        this.hasSalesRepJob = false;
        this.salesRepJobPay = 70;
        this.currentClicks = 0;
        this.clicksNeededForPromotion = 150;
        this.hasBusinessAssocJob = false;
        this.businessAssocJobPay = 120;
        this.job1upper = 5;
        this.job2upper = 10;
        this.job3upper = 15;
        this.job4upper = 20;
        this.job1_upgrade_count = 1;
        this.job2_upgrade_count = 1;
        this.job3_upgrade_count = 1;
        this.job4_upgrade_count = 1;
        this.hasResearcherJob = false;
        this.reasercherJobPay = 200;
    }


    protected Person(Parcel in) {
        daysOld = in.readInt();
        yearsOld = in.readInt();
        daysLeft = in.readInt();
        yearsLeft = in.readInt();
        money = in.readDouble();
        dailyCosts = in.readDouble();
        hasGuitar = in.readByte() != 0;
        hasBicycle = in.readByte() != 0;
        hasPaint = in.readByte() != 0;
        job1upper = in.readInt();
        job2upper = in.readInt();
        job3upper = in.readInt();
        job4upper = in.readInt();
        job1_upgrade_count = in.readInt();
        job2_upgrade_count = in.readInt();
        job3_upgrade_count = in.readInt();
        job4_upgrade_count = in.readInt();
        hasTent = in.readByte() != 0;
        hasRoom = in.readByte() != 0;
        hasApartment = in.readByte() != 0;
        hasCondo = in.readByte() != 0;
        hasHouse = in.readByte() != 0;
        hasJunkfood = in.readByte() != 0;
        hasHomecooking = in.readByte() != 0;
        hasBalanced = in.readByte() != 0;
        hasOrganic = in.readByte() != 0;
        hasPersonalChef = in.readByte() != 0;
        hasDayTrip = in.readByte() != 0;
        hasWeekend = in.readByte() != 0;
        hasEurope = in.readByte() != 0;
        hasBoraBora = in.readByte() != 0;
        hasTheWorld = in.readByte() != 0;
        hasPassedGed = in.readByte() != 0;
        hasScholarship = in.readByte() != 0;
        hasBachelors = in.readByte() != 0;
        hasMasters = in.readByte() != 0;
        hasPhd = in.readByte() != 0;
        hasBachelorsDebt = in.readByte() != 0;
        hasMastersDebt = in.readByte() != 0;
        hasPhdDebt = in.readByte() != 0;
        daysPayedForBachelorsDebt = in.readInt();
        daysPayedForMastersDebt = in.readInt();
        daysPayedForPhdDebt = in.readInt();
        gedPassChance = in.readInt();
        scholarshipPassChance = in.readInt();
        gedStudyDaysRequired = in.readInt();
        scholarshipStudyDaysRequired = in.readInt();
        hasFastFoodJob = in.readByte() != 0;
        hasSalesRepJob = in.readByte() != 0;
        hasBusinessAssocJob = in.readByte() != 0;
        clicksNeededForPromotion = in.readInt();
        promotionUpgradeClicks = in.readDouble();
        nextPromotionDaysRequired = in.readInt();
        currentClicks = in.readDouble();
        fastFoodJobPay = in.readInt();
        salesRepJobPay = in.readInt();
        businessAssocJobPay = in.readInt();
        hasResearcherJob = in.readByte() != 0;
        reasercherJobPay = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public int getDaysOld() {
        return this.daysOld;
    }

    public int getYearsOld() {
        return this.yearsOld;
    }

    public double getMoney() {
        return this.money;
    }

    public int getDaysLeft() {
        return this.daysLeft;
    }

    public int getYearsLeft() {
        return this.yearsLeft;
    }

    public double getDailyCosts() {
        return this.dailyCosts;
    }

    public void setDaysOld(int daysOld) {
        this.daysOld = daysOld;
    }

    public void setYearsOld(int yearsOld) {
        this.yearsOld = yearsOld;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setDailyCosts(double dailyCosts) {
        this.dailyCosts = dailyCosts;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public void setYearsLeft(int yearsLeft) {
        this.yearsLeft = yearsLeft;
    }

    public void setHasGuitar(boolean hasGuitar) {
        this.hasGuitar = hasGuitar;
    }

    public void setHasBicycle(boolean hasBicycle){this.hasBicycle = hasBicycle;}

    public void setHasPaint(boolean hasPaint){this.hasPaint = hasPaint;}

    public void setHasTent(boolean hasTent) { this.hasTent = hasTent;}

    public void setHasRoom(boolean hasRoom) {
        this.hasRoom = hasRoom;
    }

    public void setHasApartment(boolean hasApartment) {
        this.hasApartment = hasApartment;
    }

    public void setHasCondo(boolean hasCondo) {
        this.hasCondo = hasCondo;
    }

    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public void setHasJunkfood(boolean hasJunkfood) {
        this.hasJunkfood = hasJunkfood;
    }

    public void setHasHomecooking(boolean hasHomecooking) {
        this.hasHomecooking = hasHomecooking;
    }

    public void setHasBalanced(boolean hasBalanced) {
        this.hasBalanced = hasBalanced;
    }

    public void setHasOrganic(boolean hasOrganic) {
        this.hasOrganic = hasOrganic;
    }

    public void setHasPersonalChef(boolean hasPersonalChef) { this.hasPersonalChef = hasPersonalChef; }

    public void setHasDayTrip(boolean hasDayTrip) {
        this.hasDayTrip = hasDayTrip;
    }

    public void setHasWeekend(boolean hasWeekend) {
        this.hasWeekend = hasWeekend;
    }

    public void setHasEurope(boolean hasEurope) {
        this.hasEurope = hasEurope;
    }

    public void setHasBoraBora(boolean hasBoraBora) {
        this.hasBoraBora = hasBoraBora;
    }

    public void setHasTheWorld(boolean hasTheWorld) {
        this.hasTheWorld = hasTheWorld;
    }

    public void setHasPassedGed(boolean hasPassedGed) {
        this.hasPassedGed = hasPassedGed;
    }

    public void setHasScholarship(boolean hasScholarship) {
        this.hasScholarship = hasScholarship;
    }

    public void setHasBachelors(boolean hasBachelors) {
        this.hasBachelors = hasBachelors;
    }

    public void setHasBachelorsDebt(boolean hasBachelorsDebt) {
        this.hasBachelorsDebt = hasBachelorsDebt;
    }

    public void setHasMasters(boolean hasMasters) {
        this.hasMasters = hasMasters;
    }

    public void setHasMastersDebt(boolean hasMastersDebt) {
        this.hasMastersDebt = hasMastersDebt;
    }

    public void setHasPhd(boolean hasPhd) {
        this.hasPhd = hasPhd;
    }

    public void setHasPhdDebt(boolean hasPhdDebt) {
        this.hasPhdDebt = hasPhdDebt;
    }

    public void setHasFastFoodJob(boolean hasFastFoodJob) {
        this.hasFastFoodJob = hasFastFoodJob;
    }

    public void setHasSalesRepJob(boolean hasSalesRepJob) {
        this.hasSalesRepJob = hasSalesRepJob;
    }

    public void setHasResearcherJob(boolean hasResearcherJob) {
        this.hasResearcherJob = hasResearcherJob;
    }

    public void setHasBusinessAssocJob(boolean hasBusinessAssocJob) { this.hasBusinessAssocJob = hasBusinessAssocJob; }

    public void setFastFoodJobPay(int fastFoodJobPay) {
        this.fastFoodJobPay = fastFoodJobPay;
    }

    public void setSalesRepJobPay(int salesRepJobPay) {
        this.salesRepJobPay = salesRepJobPay;
    }

    public void setBusinessAssocJobPay(int businessAssocJobPay) {
        this.businessAssocJobPay = businessAssocJobPay;
    }

    public void setCurrentClicks(double currentClicks) {
        this.currentClicks = currentClicks;
    }

    public void setReasercherJobPay(int reasercherJobPay) {
        this.reasercherJobPay = reasercherJobPay;
    }

    public void incrementDaysPayedForBachelors(){
        this.daysPayedForBachelorsDebt++;
    }

    public int getDaysPayedForBachelors(){
        return this.daysPayedForBachelorsDebt;
    }

    public void incrementDaysPayedForMasters(){ this.daysPayedForMastersDebt++;}

    public int getDaysPayedForMasters(){ return this.daysPayedForMastersDebt;}

    public void incrementDaysPayedForPhd(){ this.daysPayedForPhdDebt++; }

    public int getDaysPayedForPhd(){ return this.daysPayedForPhdDebt;}

    public void incrementGedChance(){ this.gedPassChance = this.gedPassChance + 5;}

    public void incrementGedStudyDays(){ this.gedStudyDaysRequired = this.gedStudyDaysRequired + 5;}

    public void incrementScholarshipChance(){ this.scholarshipPassChance = this.scholarshipPassChance + 1;}

    public void incrementScholarshipStudyDays(){ this.scholarshipStudyDaysRequired = this.scholarshipStudyDaysRequired + 2;}

    public void incrementClickUpgrade(){ this.promotionUpgradeClicks = this.promotionUpgradeClicks + 0.25; }

    public void incrementNextPromotionDaysRequired(int nextPromotionDaysRequired) {
        this.nextPromotionDaysRequired = this.nextPromotionDaysRequired + 5;
    }

    public void incrementCurrentClicks(double increment){ this.currentClicks = this.currentClicks + increment; }

    public void incrementClicksNeededForPromotion(int increment){ this.clicksNeededForPromotion = this.clicksNeededForPromotion + increment; }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(daysOld);
        dest.writeInt(yearsOld);
        dest.writeInt(daysLeft);
        dest.writeInt(yearsLeft);
        dest.writeDouble(money);
        dest.writeDouble(dailyCosts);
        dest.writeByte((byte) (hasGuitar ? 1 : 0));
        dest.writeByte((byte) (hasBicycle ? 1 : 0));
        dest.writeByte((byte) (hasPaint ? 1 : 0));
        dest.writeInt(job1upper);
        dest.writeInt(job2upper);
        dest.writeInt(job3upper);
        dest.writeInt(job4upper);
        dest.writeInt(job1_upgrade_count);
        dest.writeInt(job2_upgrade_count);
        dest.writeInt(job3_upgrade_count);
        dest.writeInt(job4_upgrade_count);
        dest.writeByte((byte) (hasTent ? 1 : 0));
        dest.writeByte((byte) (hasRoom ? 1 : 0));
        dest.writeByte((byte) (hasApartment ? 1 : 0));
        dest.writeByte((byte) (hasCondo ? 1 : 0));
        dest.writeByte((byte) (hasHouse ? 1 : 0));
        dest.writeByte((byte) (hasJunkfood ? 1 : 0));
        dest.writeByte((byte) (hasHomecooking ? 1 : 0));
        dest.writeByte((byte) (hasBalanced ? 1 : 0));
        dest.writeByte((byte) (hasOrganic ? 1 : 0));
        dest.writeByte((byte) (hasPersonalChef ? 1 : 0));
        dest.writeByte((byte) (hasDayTrip ? 1 : 0));
        dest.writeByte((byte) (hasWeekend ? 1 : 0));
        dest.writeByte((byte) (hasEurope ? 1 : 0));
        dest.writeByte((byte) (hasBoraBora ? 1 : 0));
        dest.writeByte((byte) (hasTheWorld ? 1 : 0));
        dest.writeByte((byte) (hasPassedGed ? 1 : 0));
        dest.writeByte((byte) (hasScholarship ? 1 : 0));
        dest.writeByte((byte) (hasBachelors ? 1 : 0));
        dest.writeByte((byte) (hasMasters ? 1 : 0));
        dest.writeByte((byte) (hasPhd ? 1 : 0));
        dest.writeByte((byte) (hasBachelorsDebt ? 1 : 0));
        dest.writeByte((byte) (hasMastersDebt ? 1 : 0));
        dest.writeByte((byte) (hasPhdDebt ? 1 : 0));
        dest.writeInt(daysPayedForBachelorsDebt);
        dest.writeInt(daysPayedForMastersDebt);
        dest.writeInt(daysPayedForPhdDebt);
        dest.writeInt(gedPassChance);
        dest.writeInt(scholarshipPassChance);
        dest.writeInt(gedStudyDaysRequired);
        dest.writeInt(scholarshipStudyDaysRequired);
        dest.writeByte((byte) (hasFastFoodJob ? 1 : 0));
        dest.writeByte((byte) (hasSalesRepJob ? 1 : 0));
        dest.writeByte((byte) (hasBusinessAssocJob ? 1 : 0));
        dest.writeInt(clicksNeededForPromotion);
        dest.writeDouble(promotionUpgradeClicks);
        dest.writeInt(nextPromotionDaysRequired);
        dest.writeDouble(currentClicks);
        dest.writeInt(fastFoodJobPay);
        dest.writeInt(salesRepJobPay);
        dest.writeInt(businessAssocJobPay);
        dest.writeByte((byte) (hasResearcherJob ? 1 : 0));
        dest.writeInt(reasercherJobPay);
    }
}
