package com.postgrestest.liveasy_intern.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.postgrestest.liveasy_intern.entities.Load;
import java.util.List;
import java.util.UUID;

public interface LoadDao extends JpaRepository<Load, UUID> {
    List<Load> findByShipperId(UUID shipperId);
}