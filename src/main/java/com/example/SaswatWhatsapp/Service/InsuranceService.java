package com.example.SaswatWhatsapp.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.example.SaswatWhatsapp.Utils.InsuranceDTO;



public interface InsuranceService {

	ResponseEntity<InsuranceDTO> getinsuranceByMobileNumber(String mobile_no) throws IOException;
}
