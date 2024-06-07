package com.example.SaswatWhatsapp.ServiceImpl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	private  JdbcTemplate jdbcTemplate;

	public InsuranceService1Impl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<Insurance1Dto> getinsurance1ByMobileNumber(String mobile_no, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<Insurance1Dto> resultsJPA = this.insurance1Repo.findByMobile_no(mobile_no);

		String requestUrl = request.getRequestURL().toString();
		Gson gson = new Gson();

		// Capture request body
		String requestBodyJson = gson.toJson(mobile_no);
		int statusCodeValue = response.getStatus();

		// Create and save ApiLog
		ApiLogentity api1Log = new ApiLogentity();
		api1Log.setUrl(requestUrl);
		api1Log.setRequestBody(requestBodyJson);

		ResponseEntity<Insurance1Dto> responseEntity;
		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT mobile_no, customer_id, policy_no_of_insurance,saswat_loan_number,loan_from, insurance_from,insured_amount,no_of_cattle,insured_date,insurance_validity_date,insurance_type,insurance_status_active  FROM insurancetb2 WHERE mobile_no = ?";

			List<Insurance1Dto> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new Insurance1DtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				responseEntity =ResponseEntity.notFound().build();
			} else {

				responseEntity = processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			responseEntity = processResults(resultsJPA.get(0));

		}
		api1Log.setResponseBody(gson.toJson(responseEntity.getBody()));

		if (statusCodeValue == 200) {
			api1Log.setStatus("SUCCESS");
		} else {
			api1Log.setStatus("FAILURE");
		}
		// Save ApiLog
		apiLog1Repository.save(api1Log);

		return responseEntity;
	}

	private ResponseEntity<Insurance1Dto> processResults(Insurance1Dto dto) throws IOException {

		return ResponseEntity.ok(dto);
	}

	private static class Insurance1DtoRowMapper implements RowMapper<Insurance1Dto> {
		@Override
		public Insurance1Dto mapRow(ResultSet rs, int rowNum) throws SQLException {
			Insurance1Dto dto = new Insurance1Dto();
			// Map ResultSet columns to DTO fields
			dto.setMobile_no(rs.getString("mobile_no"));
			dto.setCustomer_id(rs.getString("customer_id"));
			dto.setPolicy_no_of_insurance(rs.getString("policy_no_of_insurance"));
			dto.setSaswat_loan_number(rs.getString("saswat_loan_number"));
			dto.setLoan_from(rs.getString("loan_from"));
			dto.setInsurance_from(rs.getString("insurance_from"));
            dto.setInsured_amount(rs.getString("insured_amount"));	
			dto.setNo_of_cattle(rs.getString("no_of_cattle"));
			dto.setInsured_date(rs.getString("insured_date"));
			dto.setInsurance_validity_date(rs.getString("insurance_validity_date"));
			dto.setInsurance_type(rs.getString("insurance_type"));
			dto.setInsurance_status_active(rs.getString("insurance_status_active"));
	
			
			
			return dto;
		}
	}

}
