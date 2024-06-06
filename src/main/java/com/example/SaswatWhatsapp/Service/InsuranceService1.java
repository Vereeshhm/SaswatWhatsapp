package com.example.SaswatWhatsapp.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SaswatWhatsapp.Utils.Insurance1Dto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Service
public interface InsuranceService1 {

		ResponseEntity<Insurance1Dto> getinsurance1ByMobileNumber(String mobile_no, HttpServletRequest request,
				HttpServletResponse response) throws IOException;

}
