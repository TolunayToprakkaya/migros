package com.project.migros.courierService;

import com.project.migros.base.dto.Courier;
import com.project.migros.entity.CourierEntity;
import com.project.migros.entity.StoreEntity;
import com.project.migros.repository.CourierRepository;
import com.project.migros.service.impl.CourierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class GetTotalTravelDistanceTest {

  @Mock
  private CourierRepository courierRepository;
  @InjectMocks
  private CourierServiceImpl courierService;

  private CourierEntity courierEntity;

  @BeforeEach
  public void setUp() {
    courierEntity = new CourierEntity();
    courierEntity.setCourId(1L);
    courierEntity.setArrvlTime(new Date());
    courierEntity.setIdNum(1L);
    courierEntity.setName("Can");
    courierEntity.setTown("Ataşehir");
    courierEntity.setLat(40.9923307);
    courierEntity.setLng(29.1244229);
    courierEntity.setDstnc(29.0);
    courierEntity.setLstStr("Ataşehir");
  }

  @Test
  @DisplayName("Get Total Travel Distance Service Test")
  void consentTest() {
    Mockito.doReturn(courierEntity).when(courierRepository).findByCourId(anyLong());

    Double result = courierService.getTotalTravelDistance(anyLong());

    assertAll("Get Total Travel Distance",
        () -> assertNotNull(result, "Response can not be null"));

    Mockito.verify(courierRepository, Mockito.times(1)).findByCourId(anyLong());
  }
}
