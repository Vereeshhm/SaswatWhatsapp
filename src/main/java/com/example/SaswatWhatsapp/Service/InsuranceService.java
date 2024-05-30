package com.example.SaswatWhatsapp.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SaswatWhatsapp.Utils.InsuranceDTO;


@Service
public interface InsuranceService {

	ResponseEntity<InsuranceDTO> getinsuranceByMobileNumber(String mobile_no) throws IOException;
}
