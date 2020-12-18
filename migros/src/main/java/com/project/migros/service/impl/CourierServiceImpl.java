package com.project.migros.service.impl;

import com.project.migros.base.dto.Courier;
import com.project.migros.base.exception.CourierLocationException;
import com.project.migros.base.mapper.DozerMapperUtility;
import com.project.migros.entity.CourierEntity;
import com.project.migros.entity.StoreEntity;
import com.project.migros.repository.CourierRepository;
import com.project.migros.repository.StoreRepository;
import com.project.migros.service.ICourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CourierServiceImpl implements ICourierService {

  @Autowired
  private DozerMapperUtility dozerMapperUtility;

  @Autowired
  private CourierRepository courierRepository;

  @Autowired
  private StoreRepository storeRepository;

  @Override
  @Transactional
  public Courier createCourierDistance(Courier courier) {
    CourierEntity courierEntity = courierRepository.findByIdNum(courier.getIdentityNumber());
    List<StoreEntity> storeEntityList = storeRepository.findAll();

    if (courierEntity != null) {
      courierEntity = this.updateOldCourier(courierEntity, courier, storeEntityList);
    } else {
      courierEntity = this.createNewCourier(courier, storeEntityList);
    }

    return dozerMapperUtility.map(courierEntity, Courier.class, "CourierEntity_Courier");
  }

  private CourierEntity updateOldCourier(CourierEntity courierEntity, Courier courier, List<StoreEntity> storeEntityList) {
    for (StoreEntity storeEntity : storeEntityList) {
      double distance = this.courierLocationValidation(courier, storeEntity);
      //Is the courier in the same town and is within 100 meters from the shop?
      if (distance < 100 && courier.getTown().equalsIgnoreCase(storeEntity.getTown())) {
        //Is this  store the courier went to store in last time? If these are same check the time
        if (storeEntity.getName().equalsIgnoreCase(courierEntity.getLstStr())) {
          Date now = new Date();
          long diffInMillies = Math.abs(now.getTime() - courierEntity.getArrvlTime().getTime());
          long betweenDates = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
          if (betweenDates > 1) {
            courierEntity.setArrvlTime(new Date());
            Double courierOldDistance = courierEntity.getDstnc();
            courierEntity.setDstnc(courierOldDistance + courier.getDistance());
            courierEntity.setLstStr(storeEntity.getName());
            courierEntity.setTown(storeEntity.getTown());

            return courierRepository.save(courierEntity);
          } else {
            throw new CourierLocationException("Courier is same location");
          }
        } else {
          courierEntity.setArrvlTime(new Date());
          Double courierOldDistance = courierEntity.getDstnc();
          courierEntity.setDstnc(courierOldDistance + courier.getDistance());
          courierEntity.setLstStr(storeEntity.getName());
          courierEntity.setTown(storeEntity.getTown());

          return courierRepository.save(courierEntity);
        }
      }
    }

    throw new CourierLocationException("Courier is far away from stores");
  }

  private CourierEntity createNewCourier(Courier courier, List<StoreEntity> storeEntityList) {
    for (StoreEntity storeEntity : storeEntityList) {
      double distance = this.courierLocationValidation(courier, storeEntity);
      if (distance < 100 && courier.getTown().equalsIgnoreCase(storeEntity.getTown())) {
        CourierEntity courierEntity = dozerMapperUtility.map(courier, CourierEntity.class, "CourierEntity_Courier");
        courierEntity.setArrvlTime(new Date());
        courierEntity.setLstStr(storeEntity.getName());

        return courierRepository.save(courierEntity);
      }
    }

    return null;
  }

  private double courierLocationValidation(Courier courier, StoreEntity storeEntity) {
    Double courierLongitude = courier.getLongitude();
    Double storeLongitude = storeEntity.getLng();
    double theta = courierLongitude - storeLongitude;

    Double courierLatitude = courier.getLatitude();
    Double storeLatitude = storeEntity.getLat();
    double distance = Math.sin(Math.toRadians(courierLatitude)) * Math.sin(Math.toRadians(storeLatitude)) + Math.cos(Math.toRadians(courierLatitude)) * Math.cos(Math.toRadians(storeLatitude)) * Math.cos(Math.toRadians(theta));

    distance = Math.acos(distance);
    distance = Math.toDegrees(distance);
    //Kilometer
    distance = distance * 1.609344;
    //Meter
    distance = distance * 1000;

    return distance;
  }

  @Override
  public Double getTotalTravelDistance(Long courierId) {
    CourierEntity courierEntity = courierRepository.findByCourId(courierId);

    return courierEntity.getDstnc();
  }
}
