package com.example.SaswatWhatsapp.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SaswatWhatsapp.Repository.ApiLogRepository;
import com.example.SaswatWhatsapp.Repository.InsuranceRepo;
import com.example.SaswatWhatsapp.Repository.NewInsuranceCustomRepository;
import com.example.SaswatWhatsapp.Service.InsuranceService;
import com.example.SaswatWhatsapp.Utils.ApiLog;
import com.example.SaswatWhatsapp.Utils.InsuranceDTO;
import com.example.SaswatWhatsapp.Utils.InsuranceNewCustom;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	 InsuranceRepo insuranceRepo;

	@Autowired
	ApiLogRepository apiLogRepository;
	
	@Autowired
	NewInsuranceCustomRepository customRepository;
	
	public void InsuranceRepo(InsuranceRepo insuranceRepo) {
		this.insuranceRepo = insuranceRepo;
	}



	@Override
	public ResponseEntity<InsuranceDTO> getinsuranceByMobileNumber(String mobile_no, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		 List<InsuranceDTO> resultsJPA = this.insuranceRepo.findByMobile_no(mobile_no);

	        String requestUrl = request.getRequestURL().toString();
	        Gson gson = new Gson();

	        // Capture request body
	        String requestBodyJson = gson.toJson(mobile_no);

	        // Create and save ApiLog
	        ApiLog apiLog = new ApiLog();
	        apiLog.setUrl(requestUrl);
	        apiLog.setRequestBody(requestBodyJson);

	        ResponseEntity<InsuranceDTO> responseEntity;

	        // If no records found in JPA repository, return not found response
	        if (resultsJPA.isEmpty()) {
	            InsuranceNewCustom custom = new InsuranceNewCustom();
	            custom.setMobile_no(mobile_no);
	            customRepository.save(custom);
	            
	            String errorMessage = "User with mobile number " + mobile_no + " does'nt exists";
	            apiLog.setResponseBody(gson.toJson(errorMessage));
	            apiLog.setStatusCode("FAILURE");
	            apiLogRepository.save(apiLog);

	            return new ResponseEntity(errorMessage, HttpStatus.CONFLICT);
	          
	                    
	        } else {
	            // Mobile number found in JPA
	            responseEntity = processResults(resultsJPA.get(0));
	        }

	        apiLog.setResponseBody(gson.toJson(responseEntity.getBody()));

	        if (responseEntity.getStatusCode() == HttpStatus.OK) {
	            apiLog.setStatusCode("SUCCESS");
	        } else {
	            apiLog.setStatusCode("FAILURE");
	        }
	        // Save ApiLog
	        apiLogRepository.save(apiLog);

	        return responseEntity;
	    }

	    private ResponseEntity<InsuranceDTO> processResults(InsuranceDTO dto) throws IOException {
	        return ResponseEntity.ok(dto);
	    }
	}