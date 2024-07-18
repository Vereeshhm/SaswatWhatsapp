package com.example.SaswatWhatsapp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SaswatWhatsapp.Utils.District;
import com.example.SaswatWhatsapp.Utils.Taluk;


@Repository
public interface TalukRepository extends JpaRepository<Taluk, Long> {
    Optional<Taluk> findByNameIgnoreCaseAndDistrict(String name, District district);
}
