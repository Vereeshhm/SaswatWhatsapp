
package com.example.SaswatWhatsapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SaswatWhatsapp.Service.InsuranceService;
import com.example.SaswatWhatsapp.Service.InsuranceService1;
import com.example.SaswatWhatsapp.Service.LoanService;
import com.example.SaswatWhatsapp.Service.ProspectNewService;
import com.example.SaswatWhatsapp.ServiceImpl.DistrictService;
import com.example.SaswatWhatsapp.ServiceImpl.LanguageService;
import com.example.SaswatWhatsapp.Utils.District;
import com.example.SaswatWhatsapp.Utils.Insurance1Dto;
import com.example.SaswatWhatsapp.Utils.InsuranceDTO;
import com.example.SaswatWhatsapp.Utils.Language;
import com.example.SaswatWhatsapp.Utils.Loandto;
import com.example.SaswatWhatsapp.Utils.ProspectNew;
import com.example.SaswatWhatsapp.Utils.StateDistrict;
import com.example.SaswatWhatsapp.Utils.Taluk;

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

	@Autowired
	private LanguageService languageService;

	@Autowired
	private DistrictService districtService;

	@GetMapping("emi/insurance/mobile_no/{mobile_no}")
	public ResponseEntity<InsuranceDTO> getinsuranceByMobileNumber(@PathVariable("mobile_no") String mobile_no,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return insuranceService.getinsuranceByMobileNumber(mobile_no, request, response);
	}

	@GetMapping("emi/insurance1/mobile_no/{mobile_no}")
	public ResponseEntity<Insurance1Dto> getinsurance1ByMobileNumber(@PathVariable("mobile_no") String mobileNo,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return insuranceService1.getinsurance1ByMobileNumber(mobileNo, request, response);
	}

	@GetMapping("emi/loan/mobile_no/{mobile_no}")
	public ResponseEntity<Loandto> getloanrecordsByMobileNumber(@PathVariable String mobile_no,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		return loanService.getloanrecordsByMobileNumber(mobile_no, request, response);
	}

	@PostMapping("loan/newuser/mobile/{mobile}")
	public ResponseEntity<ProspectNew> updateUserRecordsByMobileNumber(@PathVariable String mobile) {
		return prospectnewService.updateUserRecordsByMobileNumber(mobile);
	}

	@GetMapping("/user/languages")
	public List<Language> getAllLanguages() {
		return languageService.getAllLanguages();
	}

	@PostMapping("/user/languages")
	public List<Language> addLanguages(@RequestBody List<String> languageNames) {
		return languageService.addLanguages(languageNames);
	}

	@GetMapping("/states")
	public List<StateDistrict> getAllStates() {
		return districtService.getAllStates();
	}

	@GetMapping("/states/districts/{stateId}")
	public ResponseEntity<Set<District>> getDistrictsByStateId(@PathVariable Long stateId) {
		Set<District> districts = districtService.getDistrictsByStateId(stateId);
		return ResponseEntity.ok(districts);
	}

	@GetMapping("/districts/taluks/{districtId}")
	public ResponseEntity<Set<Taluk>> getTaluksByDistrictId(@PathVariable Long districtId) {
		Set<Taluk> taluks = districtService.getTaluksByDistrictId(districtId);
		return ResponseEntity.ok(taluks);
	}

	@PostMapping("/states")
	public String addStatesWithDistrictsAndTaluks(@RequestBody List<StateDistrict> stateDistricts) {
		districtService.addStatesWithDistrictsAndTaluks(stateDistricts);
		return "States, Districts, and Taluks added successfully";
	}

}
