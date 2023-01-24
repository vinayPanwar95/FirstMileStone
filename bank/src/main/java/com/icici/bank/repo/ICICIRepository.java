package com.icici.bank.repo;

import com.icici.bank.entity.ICICIEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICICIRepository extends JpaRepository<ICICIEntity, UUID> {
}
