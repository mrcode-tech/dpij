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
import java.util.Properties;

import com.oozinoz.firework.Firework;

/**
 * Represents a customer.
 */
public class Customer {
    public static final int BIG_SPENDER_DOLLARS = 1000;

    /**
     * @return true if this customer has registered (or entered) his or her
     *         preference profile. This method is not actually implemented
     */
    public boolean isRegistered() {
        return false;
    }

    /**
     * This method demonstrates that the class can actually find the properties
     * file that lists a strategic promotion. If you set your classpath to
     * include the "oozinoz" directory that you can download from oozinoz.com,
     * this program will find the strategy.dat file that lists a promoted
     * firework. In short it's an example of finding and reading from a
     * properties file.
     */

    //This code is in the com.oozinoz.recommendation package of the Oozinoz
    //code base available from www.oozinoz.com. The getRecommended()
    //method expects that if a promotion is on, it will be named in a
    //strategy.dat file in a config directory. Such a file would look as
    //follows:
    //promote=JSquirrel
    //It there is no such file, the getRecommended() code will use the Rel8
    //engine if the customer is registered. If there is no promotion strategy
    //file and the customer is not registered, the code will use the Like-
    //MyStuff engine if the customer has spent a certain amount of money
    //in the past year. If no better recommendation is possible, the code
    //selects and recommends a firework at random. The method works,
    //and you might feel that this is not the worst code you’ve ever seen.
    //But we can certainly make it better.
    public static void main(String[] args) {
        Firework recommendation = new Customer().getRecommended();
        System.out.println("Customer recommendation: " + recommendation.toString());
    }

    /**
     * @return a firework to recommend to this customer.
     */
    // TODO: 1/21/2024 without STRATEGY Design Pattern - read properties file
    public Firework getRecommended() {
        // if we're promoting a particular firework, return it
        try {
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResourceAsStream("config/strategy.dat"));
            String promotedName = p.getProperty("promote");

            if (promotedName != null) {
                Firework f = Firework.lookup(promotedName);
                if (f != null) return f;
            }
        } catch (Exception ignored) {
            // If resource missing or it failed to load,
            // fall through to the next approach.
        }

        // if registered, compare to other customers
        if (isRegistered()) {
            return (Firework) Rel8.advise(this);
        }

        // TODO: 1/21/2024 STRATEGY Design Pattern - work with calender instance
        // check spending over the last year
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        if (spendingSince(cal.getTime()) > 1000) return (Firework) LikeMyStuff.suggest(this);

        // oh well!
        return Firework.getRandom();
    }

    /**
     * @return the amount of dough this customer has spent with us since the
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