
package com.example.SaswatWhatsapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SaswatWhatsapp.Service.InsuranceService;
import com.example.SaswatWhatsapp.Service.InsuranceService1;
import com.example.SaswatWhatsapp.Utils.Insurance1Dto;
import com.example.SaswatWhatsapp.Utils.InsuranceDTO;


@RestController
@RequestMapping("/saswat")
public class WhatsappController {

	
	
	@Autowired
	InsuranceService insuranceService;
	
	@Autowired
	InsuranceService1 insuranceService1;
	
	@GetMapping("emi/insurance/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<InsuranceDTO>> getinsuranceByMobileNumber(@PathVariable String mobile_no)
			throws IOException {
		return ResponseEntity.ok(insuranceService.getinsuranceByMobileNumber(mobile_no));
	}

	@GetMapping("emi/insurance1/mobile_no/{mobile_no}")
	public ResponseEntity<ResponseEntity<Insurance1Dto>> getinsurance1ByMobileNumber(@PathVariable String mobile_no)
			throws IOException {
		return ResponseEntity.ok(insuranceService1.getinsurance1ByMobileNumber(mobile_no));
	}
	
}
