package com.project.migros.base.util;

import com.project.migros.entity.StoreEntity;
import com.project.migros.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class StoreDBInitializerUtil {

  private final StoreRepository storeRepository;

  @PostConstruct
  private void initDb() {

    if (storeRepository.count() <= 0) {
      createStores();
    }
  }

  private void createStores() {
    StoreEntity storeEntity1 =StoreEntity.builder()
        .name("Ataşehir MMM Migros")
        .town("Ataşehir")
        .lat(40.9923307)
        .lng(29.1244229)
        .build();

    StoreEntity storeEntity2 =StoreEntity.builder()
        .name("Novada MMM Migros")
        .town("Novada")
        .lat(40.986106)
        .lng(29.1161293)
        .build();

    StoreEntity storeEntity3 =StoreEntity.builder()
        .name("Beylikdüzü 5M Migros")
        .town("Beylikdüzü")
        .lat(41.0066851)
        .lng(28.6552262)
        .build();

    StoreEntity storeEntity4 =StoreEntity.builder()
        .name("Ortaköy MMM Migros")
        .town("Ortaköy")
        .lat(41.055783)
        .lng(29.0210292)
        .build();

    StoreEntity storeEntity5 =StoreEntity.builder()
        .name("Caddebostan MMM Migros")
        .town("Caddebostan")
        .lat(40.9632463)
        .lng(29.0630908)
        .build();

    storeRepository.saveAll(Arrays.asList(storeEntity1, storeEntity2, storeEntity3, storeEntity4, storeEntity5));
  }
}
