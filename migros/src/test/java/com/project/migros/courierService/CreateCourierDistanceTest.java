package com.project.migros.courierService;

import com.project.migros.base.dto.Courier;
import com.project.migros.base.mapper.DozerMapperUtility;
import com.project.migros.entity.CourierEntity;
import com.project.migros.entity.StoreEntity;
import com.project.migros.repository.CourierRepository;
import com.project.migros.repository.StoreRepository;
import com.project.migros.service.impl.CourierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CreateCourierDistanceTest {

  @Mock
  private CourierRepository courierRepository;
  @Mock
  private StoreRepository storeRepository;
  @Mock
  private DozerMapperUtility dozerMapperUtility;
  @Spy
  @InjectMocks
  private CourierServiceImpl courierService;

  private CourierEntity courierEntity;
  private Courier courier;
  private StoreEntity storeEntity;
  private List<StoreEntity> storeEntityList = new ArrayList<>();

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

    courier = new Courier();
    courier.setCourierId(1L);
    courier.setArrivalTime(new Date());
    courier.setIdentityNumber(1L);
    courier.setName("Can");
    courier.setTown("Ataşehir");
    courier.setLatitude(40.9923307);
    courier.setLongitude(29.1244229);
    courier.setDistance(29.0);
    courier.setLastStore("Ataşehir");

    storeEntity = new StoreEntity();
    storeEntity.setStrId(1L);
    storeEntity.setName("Ataşehir MMM Migros");
    storeEntity.setTown("Ataşehir");
    storeEntity.setLat(40.9923307);
    storeEntity.setLng(29.1244229);
    storeEntityList.add(storeEntity);

  }

  @Test
  @DisplayName("Create Courier Distance Service Test")
  void consentTest() {
    Mockito.doReturn(courierEntity).when(courierRepository).findByIdNum(anyLong());
    Mockito.doReturn(storeEntityList).when(storeRepository).findAll();
    Mockito.doReturn(courierEntity).when(courierRepository).save(courierEntity);
    Mockito.when(dozerMapperUtility.map(courier, CourierEntity.class, "CourierEntity_Courier")).thenReturn(courierEntity);
    Mockito.when(dozerMapperUtility.map(courierEntity, Courier.class, "CourierEntity_Courier")).thenReturn(courier);

    Courier result = courierService.createCourierDistance(courier);

    assertAll("Create Courier Distance",
        () -> assertNotNull(result, "Response can not be null"),
        () -> assertEquals(courier.getIdentityNumber(), result.getIdentityNumber()));

    Mockito.verify(courierRepository, Mockito.times(1)).findByIdNum(anyLong());
    Mockito.verify(storeRepository, Mockito.times(1)).findAll();
    Mockito.verify(courierRepository, Mockito.times(1)).save(courierEntity);
    Mockito.verify(dozerMapperUtility, Mockito.times(1)).map(courierEntity, Courier.class, "CourierEntity_Courier");
  }
}
