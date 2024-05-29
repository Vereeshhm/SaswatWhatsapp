package com.example.SaswatWhatsapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SaswatWhatsapp.Utils.InsuranceDTO;

@Repository
public interface InsuranceRepo extends JpaRepository<InsuranceDTO, Integer> {
	@Query("SELECT s FROM InsuranceDTO s WHERE s.mobile_no = :mobileNo")
    List<InsuranceDTO> findByMobile_no(String mobileNo);

}
