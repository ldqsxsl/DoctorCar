package com.doctorcar.mobile.api;

/**
 * Created by dd on 2017/2/9.
 */

public class Student<T,E> {
    public T girl;
    public E teacher;

    public Student() {
    }

    public T getGirl() {
        return girl;
    }

    public void setGirl(T girl) {
        this.girl = girl;
    }

    public E getTeacher() {
        return teacher;
    }

    public void setTeacher(E teacher) {
        this.teacher = teacher;
    }
}
