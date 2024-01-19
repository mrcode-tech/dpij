package com.oozinoz.credit.ca;

import com.oozinoz.credit.CreditCheckFactory;

public class TestAbstractFactory {
    public static void main(String[] args) {

        //Abstract FACTORY Design Pattern - method createCreditCheck2 is none-static
        CreditCheckFactory instanceCreditCheckFactory = new CheckFactoryCanada();
        System.out.println(instanceCreditCheckFactory.createCreditCheck2().creditLimit(12));

        //FACTORY METHOD Design Pattern - method createCreditCheck is static
        System.out.println(CreditCheckFactory.createCreditCheck().creditLimit(12));

        // Accessing static method through instance - This is allowed but not recommended
        System.out.println(instanceCreditCheckFactory.createCreditCheck().creditLimit(12));
    }
}
