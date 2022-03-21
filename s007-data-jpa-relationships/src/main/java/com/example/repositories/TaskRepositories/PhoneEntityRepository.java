package com.example.repositories.TaskRepositories;

import com.example.entities.TaskEntities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PhoneEntityRepository extends JpaRepository<PhoneEntity, Long>, JpaSpecificationExecutor<PhoneEntity> {
}
