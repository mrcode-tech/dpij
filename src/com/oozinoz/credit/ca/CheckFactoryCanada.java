package com.oozinoz.credit.ca;

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

import com.oozinoz.credit.*;

/**
 * This factory supplies objects that can check credit, billing addresses, and
 * shipping addresses in Canada.
 */

// TODO: 1/19/2024 Abstract Factories Design Pattern
//Now suppose that a requirements analyst tells you that Oozinoz
//wants to start servicing customers in Canada. To do business in Canada,
//you will use a different credit agency and different data sources
//to determine shipping and billing information.

//When a customer calls, the call center application needs a family of
//objects to perform a variety of checks. The family to use depends on
//whether the call is from Canada or from the United States. You can
//apply the ABSTRACT FACTORY pattern to provide for the creation of
//these object families.

//The revised package contains primarily interfaces and an abstract factory class.

//An instance of the abstract CreditCheckFactory class will be either a CheckFactory-
//Canada class or a CheckFactoryUS class

//• Implement create- methods for methods inherited from the abstract CreditCheckFactory class
//• Have the proper interface for the return type of each createmethod
//• Return a CreditCheckOffline object if the agency is down
public class CheckFactoryCanada extends CreditCheckFactory {
    /**
     * @return a BillingCheck object for Canadian customers.
     */
    public BillingCheck createBillingCheck() {
        return new BillingCheckCanada();
    }

    /**
     * @return an ICreditCheck object for Canadian customers.
     */
    public CreditCheck createCreditCheck2() {
        if (isAgencyUp())
            return new CreditCheckCanadaOnline();
        return new CreditCheckOffline();
    }

    /**
     * @return a ShippingCheck object for Canadian customers.
     */
    public ShippingCheck createShippingCheck() {
        return new ShippingCheckCanada();
    }
}