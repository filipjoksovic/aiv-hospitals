package com.hospital.hospital.patterns.observer;

public abstract class Observer<T> {

    public Observer(Subject<T> subject) {
        this.subject = subject;
    }

    protected Subject<T> subject;

    public abstract void update();
}
