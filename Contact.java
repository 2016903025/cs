package com.addressbook.control;

/**
 * @author quan
 * date 2020-05-30
 */
public class Contact {
    int id;
    String name;
    String sex;
    String job;
    String phoneNumber;
    String email;
    String group;

    Contact(int id, String name, String sex, String job, String phoneNumber, String email, String group) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.job = job;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.group = group;
    }
}