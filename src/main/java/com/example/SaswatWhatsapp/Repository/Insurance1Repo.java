package com.example.SaswatWhatsapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SaswatWhatsapp.Utils.Insurance1Dto;


@Repository
public interface Insurance1Repo extends JpaRepository<Insurance1Dto, Integer> {

	
	

	@Query("SELECT s FROM Insurance1Dto s WHERE s.mobile_no = :mobileNo")
    List<Insurance1Dto> findByMobile_no(String mobileNo);
}
