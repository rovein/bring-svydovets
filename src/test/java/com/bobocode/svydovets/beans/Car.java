package com.bobocode.svydovets.beans;

import com.bobocode.svydovets.annotation.annotations.Component;

@Component("car_bean")
public class Car {

  public Car() {
  }

  String name = "BMW";

  public String getName() {
    return name;
  }
}
