package com.oozinoz.recommendation;

/*
 * Copyright (c) 2001, 2005. Steven J. Metsker.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 *
 * Please use this software as you wish with the sole
 * restriction that you may not claim that you wrote it.
 */

import java.lang.String;
import java.util.Calendar;
import java.util.Date;

import com.oozinoz.firework.Firework;

/**
 * Represents a customer. This class is the refactored strategy class. It
 * extends Customer only so it can be passed in to Rel8 and LikeMyStuff. In
 * reality, you would replace the Customer class with this one.
 */
// TODO: 1/21/2024 STRATEGY Design Pattern - using startegy

//The Advisor interface declares that a class that implements the interface
//can accept a customer and recommend a firework. The next step
//in refactoring the Customer class’s getRecommended() code is to create
//classes that each represent one of the recommendation strategies.
//Each class will provide a different implementation of the recommend()
//method that the Advisor interface specifies.
public class Customer2 extends Customer {
    private Advisor advisor;

    private static PromotionAdvisor promotionAdvisor = new PromotionAdvisor();
    private static GroupAdvisor groupAdvisor = new GroupAdvisor();
    private static ItemAdvisor itemAdvisor = new ItemAdvisor();
    private static RandomAdvisor randomAdvisor = new RandomAdvisor();

    public static final int BIG_SPENDER_DOLLARS = 1000;

    /**
     * @return true if this customer has registered (or entered) his or her
     *         preference profile. This method is not actually implemented
     */
    public boolean isRegistered() {
        return false;
    }

    /**
     * This method demonstrates that the object actually finds the properties
     * file that lists a strategic promotion. If you set your classpath to
     * include the "oozinoz" directory that you can download from oozinoz.com,
     * this program will find the strategy.dat file that lists a promoted
     * firework. In short it's an example of finding and reading from a
     * properties file.
     */
    public static void main(String[] args) {
        Firework recommendation = new Customer2().getRecommended();
        System.out.println("Customer2 recommendation: " + recommendation.toString());
    }

    /**
     * @return a firework to recommend to this customer. This method is
     *         refactored to employ the Strategy pattern.
     */

    //The advisor classes translate calls to recommend() into the interfaces
    //that the underlying engines require. For example, the GroupAdvisor
    //class translates calls to recommend() into the advise() interface that
    //the Rel8 engine requires
    public Firework getRecommended() {
        Firework recommend = getAdvisor().recommend(this);
        return recommend;
    }


    //The refactoring of Customer separates the selection of a strategy from
    //the use of the strategy. An advisor attribute of a Customer object
    //holds the current choice of the strategy to apply. The refactored
    //Customer2 class lazy-initializes this attribute with logic that reflects
    //Oozinoz’s advertising policies:
    //this part is for selecting startegy...

    //This selection code may be complex even after refactoring, but
    //you should be able to reduce this code so that it is nearly equivalent
    //to pseudocode that describes strategy selection in the problem
    //domain
    private Advisor getAdvisor() {
        if (advisor == null) {
            if (promotionAdvisor.hasItem())
                advisor = promotionAdvisor;
            else if (isRegistered())
                advisor = groupAdvisor;
            else if (isBigSpender())
                advisor = itemAdvisor;
            else
                advisor = randomAdvisor;
        }
        return advisor;
    }

    /**
     * @return the amount of money this customer has spent with us since the
     *         provided date.
     * @param date Since when?
     */
    public double spendingSince(Date date) {
        return 1000;
    }

    private boolean isBigSpender() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        return (spendingSince(cal.getTime()) > BIG_SPENDER_DOLLARS);
    }
}