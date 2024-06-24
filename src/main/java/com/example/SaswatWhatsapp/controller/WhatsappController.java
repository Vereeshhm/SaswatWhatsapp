
package com.example.SaswatWhatsapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SaswatWhatsapp.Service.InsuranceService;
import com.example.SaswatWhatsapp.Service.InsuranceService1;
import com.example.SaswatWhatsapp.Service.LoanService;
import com.example.SaswatWhatsapp.Service.ProspectNewService;
import com.example.SaswatWhatsapp.Utils.Insurance1Dto;
import com.example.SaswatWhatsapp.Utils.InsuranceDTO;
import com.example.SaswatWhatsapp.Utils.Loandto;
import com.example.SaswatWhatsapp.Utils.ProspectNew;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/saswat")
public class WhatsappController {

	@Autowired
	InsuranceService insuranceService;

	@Autowired
	InsuranceService1 insuranceService1;
	
	@Autowired
	LoanService loanService;
	
	@Autowired
	ProspectNewService prospectnewService;

	@GetMapping("emi/insurance/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<InsuranceDTO>> getinsuranceByMobileNumber(
			@PathVariable("mobile_no") String mobile_no, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return ResponseEntity.ok(insuranceService.getinsuranceByMobileNumber(mobile_no, request, response));
	}

	@GetMapping("emi/insurance1/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<Insurance1Dto>> getinsurance1ByMobileNumber(
			@PathVariable("mobile_no") String mobileNo, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return ResponseEntity.ok(insuranceService1.getinsurance1ByMobileNumber(mobileNo, request, response));
	}

	@GetMapping("emi/loan/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<Loandto>> getloanrecordsByMobileNumber(@PathVariable String mobile_no, HttpServletRequest request, HttpServletResponse response)throws IOException
	{
		return ResponseEntity.ok(loanService.getloanrecordsByMobileNumber(mobile_no,request,response));
	}
	
	@PostMapping("loan/newuser/mobile/{mobile}")
	public ResponseEntity<ResponseEntity<ProspectNew>> updateUserRecordsByMobileNumber(@PathVariable String mobile){
		return ResponseEntity.ok(prospectnewService.updateUserRecordsByMobileNumber(mobile));
	}
}
