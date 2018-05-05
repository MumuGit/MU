package com.diabin.design_pattern.decorator;

public class PersonCloth extends Person {
    protected Person mPerson;

    public PersonCloth(Person mPerson) {
        this.mPerson = mPerson;
    }

    @Override
    public void dressed() {
        mPerson.dressed();
    }
}
