package com.project.migros.repository;

import com.project.migros.entity.CourierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<CourierEntity, Long> {

  CourierEntity findByIdNum(Long identityNumber);

  CourierEntity findByCourId(Long courierId);
}
