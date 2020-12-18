package com.project.migros.service.impl;

import com.project.migros.base.dto.Store;
import com.project.migros.base.mapper.DozerMapperUtility;
import com.project.migros.entity.StoreEntity;
import com.project.migros.repository.StoreRepository;
import com.project.migros.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements IStoreService {

  @Autowired
  private DozerMapperUtility dozerMapperUtility;

  @Autowired
  private StoreRepository storeRepository;

  @Override
  public Store createStore(Store store) {
    StoreEntity storeEntity = dozerMapperUtility.map(store, StoreEntity.class, "StoreEntity_Store");
    storeRepository.save(storeEntity);

    return store;
  }
}
