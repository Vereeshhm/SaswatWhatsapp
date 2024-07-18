package com.example.SaswatWhatsapp.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SaswatWhatsapp.Repository.ApiLog1Repository;
import com.example.SaswatWhatsapp.Repository.Insurance1Repo;
import com.example.SaswatWhatsapp.Service.InsuranceService1;
import com.example.SaswatWhatsapp.Utils.ApiLogentity;
import com.example.SaswatWhatsapp.Utils.Insurance1Dto;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class InsuranceService1Impl implements InsuranceService1 {

	@Autowired
	Insurance1Repo insurance1Repo;

	@Autowired
	ApiLog1Repository apiLog1Repository;

	public void Insurance1Repo(Insurance1Repo insurance1Repo) {
		this.insurance1Repo = insurance1Repo;
	}

//	private JdbcTemplate jdbcTemplate;
//
//	public InsuranceService1Impl(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	@Override
	public ResponseEntity<Insurance1Dto> getinsurance1ByMobileNumber(String mobile_no, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<Insurance1Dto> resultsJPA = this.insurance1Repo.findByMobile_no(mobile_no);

		String requestUrl = request.getRequestURL().toString();
		Gson gson = new Gson();

		// Capture request body
		String requestBodyJson = gson.toJson(mobile_no);

		// Create and save ApiLog
		ApiLogentity api1Log = new ApiLogentity();
		api1Log.setUrl(requestUrl);
		api1Log.setRequestBody(requestBodyJson);

		ResponseEntity<Insurance1Dto> responseEntity;
		// If no records found in JPA repository, return not found response
		if (resultsJPA.isEmpty()) {
			responseEntity = ResponseEntity.notFound().build();
		} else {
			// Mobile number found in JPA

			Insurance1Dto dto = resultsJPA.get(0);

			responseEntity = ResponseEntity.ok(dto);

		}

		api1Log.setResponseBody(gson.toJson(responseEntity.getBody()));

		// Save ApiLog
		apiLog1Repository.save(api1Log);

		return responseEntity;
	}

}