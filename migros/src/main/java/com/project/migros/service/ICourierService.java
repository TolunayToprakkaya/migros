package com.project.migros.service;

import com.project.migros.base.dto.Courier;

public interface ICourierService {

  Courier createCourierDistance(Courier courier);

  Double getTotalTravelDistance(Long courierId);
}
