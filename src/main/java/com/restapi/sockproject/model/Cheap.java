package com.restapi.sockproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Cheap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String route;
    private long departure;
    private long arrival;
}
