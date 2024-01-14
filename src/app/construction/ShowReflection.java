package app.construction;

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

import java.awt.Point;
import java.lang.reflect.Constructor;
// TODO: 1/14/2024 Relection :  The most common way to instantiate objects is by invoking the new
//operator, but you can also use reflection. Reflection provides the ability
//to work with types and type members as objects. Even if you do
//not use reflection frequently, you may be able to follow the logic of a
//working program that relies on reflection, such as the following:

public class ShowReflection {

    public static void main(String[] args) {
        Constructor<?>[] cc = Point.class.getConstructors();

        Constructor<?> cons = null;
        for (Constructor<?> aCc : cc)
            if (aCc.getParameterTypes().length == 2) {
                cons = aCc;
            }

        try {
            Object obj = cons.newInstance(3, 4);

            System.out.println(obj);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}