package com.mountainlodge.booking.repository;

import com.mountainlodge.booking.entity.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LodgeRepository extends JpaRepository<Lodge, Long> {
    List<Lodge> findByManagerId(Long managerId);
}
