package app.templateMethod;

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

import java.util.Arrays;
import com.oozinoz.firework.Rocket;
import com.oozinoz.utility.Dollars;

// TODO: 1/20/2024 TEMPLATE METHOD Design Pattern - sort
public class ShowComparator {
    
    public static void main(String[] args) {
        Rocket r1 = new Rocket("Sock-it", 0.8, new Dollars(11.95), 320, 25);
        Rocket r2 = new Rocket("Sprocket", 1.5, new Dollars(22.95), 270, 40);
        Rocket r3 = new Rocket("Mach-it", 1.1, new Dollars(22.95), 1000, 70);
        Rocket r4 = new Rocket("Pocket", 0.3, new Dollars(4.95), 150, 20);
        Rocket[] rockets = new Rocket[] { r1, r2, r3, r4 };
        
        System.out.println("Sorted by apogee: ");
        //The sort() methods and the Comparator interface
        //let you supply a specific step to a general sorting algorithm.
        //TEMPLATE METHOD is not restricted to the case in which only the missing step is domain specific. Sometimes, the entire algorithm applies
        //to a specific application domain

        Arrays.sort(rockets, new ApogeeComparator());
        for (Rocket rocket : rockets) {
            System.out.println(rocket);
        }

        System.out.println();
        System.out.println("Sorted by name: ");
        Arrays.sort(rockets, new NameComparator());
        for (Rocket rocket : rockets) {
            System.out.println(rocket);
        }
    }
}