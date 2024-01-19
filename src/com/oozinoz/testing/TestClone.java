package com.oozinoz.testing;

// TODO: 1/19/2024 shallow copy and deepcopy - clonaeble sample
class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Address {
    String city;

    public Address(String city) {
        this.city = city;
    }
}

//The clone() method in Java, as defined in the Object class, performs a shallow copy.
// This means that it creates a new object, but for reference types (objects),
// it copies the references to the original objects rather than creating new instances of the referenced objects.
// In other words, it copies the references but not the referenced objects themselves.

//If you need a deep copy (i.e., creating new instances of the referenced objects as well), 
// you would need to implement a custom clone() method that explicitly creates new instances of the referenced objects and copies their values. 
// Keep in mind that this might involve recursively cloning nested objects if your object contains other objects

public class TestClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address originalAddress = new Address("City Original");
        Person originalPerson = new Person("John", originalAddress);

        // Clone the originalPerson
        Person clonedPerson = (Person) originalPerson.clone();

        // Modify the clonedPerson's address
        clonedPerson.address.city = "City Modified";

        // Print originalPerson's address
        System.out.println(originalPerson.address.city);  // Prints "City Modified"
    }
}
