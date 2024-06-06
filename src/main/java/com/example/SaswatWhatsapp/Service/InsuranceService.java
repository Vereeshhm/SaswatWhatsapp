package com.example.SaswatWhatsapp.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SaswatWhatsapp.Utils.InsuranceDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Service
public interface InsuranceService {

//	ResponseEntity<InsuranceDTO> getinsuranceByMobileNumber(String mobile_no) throws IOException;

	ResponseEntity<InsuranceDTO> getinsuranceByMobileNumber(String mobile_no, HttpServletRequest request,
			HttpServletResponse response) throws IOException;
}
