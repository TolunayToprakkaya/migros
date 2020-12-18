package com.project.migros.base.dto;

import java.util.Date;

public class Courier {

  private Long courierId;
  private Date arrivalTime;
  private Long identityNumber;
  private String name;
  private String town;
  private Double latitude;
  private Double longitude;
  private Double distance;
  private String lastStore;

  public Long getCourierId() {
    return courierId;
  }

  public void setCourierId(Long courierId) {
    this.courierId = courierId;
  }

  public Date getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(Date arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public Long getIdentityNumber() {
    return identityNumber;
  }

  public void setIdentityNumber(Long identityNumber) {
    this.identityNumber = identityNumber;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTown() {
    return town;
  }

  public void setTown(String town) {
    this.town = town;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public String getLastStore() {
    return lastStore;
  }

  public void setLastStore(String lastStore) {
    this.lastStore = lastStore;
  }
}
