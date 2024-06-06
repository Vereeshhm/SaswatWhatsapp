package com.example.SaswatWhatsapp.Service;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.example.SaswatWhatsapp.Utils.Loandto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoanService {

	ResponseEntity<Loandto> getloanrecordsByMobileNumber(String mobile_no, HttpServletRequest request, HttpServletResponse response)throws IOException;

}
