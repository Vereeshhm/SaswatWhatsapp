package com.example.SaswatWhatsapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SaswatWhatsapp.Utils.District;
import com.example.SaswatWhatsapp.Utils.StateDistrict;


@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByNameIgnoreCaseAndStateDistrict(String name, StateDistrict stateDistrict);
}
