package com.gs.global_solution.repository;


import com.gs.global_solution.model.CheckinEmocional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckinEmocionalRepository extends JpaRepository<CheckinEmocional, Long>{
    List<CheckinEmocional> findByUserId(Long userId);
}
