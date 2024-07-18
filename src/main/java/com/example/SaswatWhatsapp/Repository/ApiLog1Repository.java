package com.example.SaswatWhatsapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SaswatWhatsapp.Utils.ApiLogentity;


@Repository
public interface ApiLog1Repository extends JpaRepository<ApiLogentity, Long> {

	
}
