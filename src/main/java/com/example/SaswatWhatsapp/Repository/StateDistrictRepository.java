package com.example.SaswatWhatsapp.Repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SaswatWhatsapp.Utils.StateDistrict;
@Repository
public interface StateDistrictRepository extends JpaRepository<StateDistrict, Long> {
    Optional<StateDistrict> findByStateIgnoreCase(String state);
}
