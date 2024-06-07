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

import com.example.SaswatWhatsapp.Repository.ApiLogRepository;
import com.example.SaswatWhatsapp.Repository.Insurance1Repo;
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

	private JdbcTemplate jdbcTemplate;

	
	public InsuranceServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<InsuranceDTO> getinsuranceByMobileNumber(String mobile_no, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<InsuranceDTO> resultsJPA = this.insuranceRepo.findByMobile_no(mobile_no);

		String requestUrl = request.getRequestURL().toString();
		Gson gson = new Gson();

		// Capture request body
		String requestBodyJson = gson.toJson(mobile_no);
		int statusCodeValue = response.getStatus();

		// Create and save ApiLog
		ApiLog apiLog = new ApiLog();
		apiLog.setUrl(requestUrl);
		apiLog.setRequestBody(requestBodyJson);

		ResponseEntity<InsuranceDTO> responseEntity;

		if (resultsJPA.isEmpty()) {
			String query = "SELECT mobile_no, customer_id , saswat_insurance_number_1,saswat_insurance_number_2,saswat_insurance_number_3,saswat_insurance_number_4,saswat_insurance_number_5,insurance_exists,total,language_selected FROM insurancetb1 WHERE mobile_no = ?";

			List<InsuranceDTO> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new insurancedtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				InsuranceNewCustom custom=new InsuranceNewCustom();
				custom.setMobile_no(mobile_no);
				customRepository.save(custom);
				responseEntity = ResponseEntity.notFound().build();
			} else 
			{
				responseEntity = processResults(resultsJdbcTemplate.get(0));
			}
		} else {
			responseEntity = processResults(resultsJPA.get(0));
		}

		
		apiLog.setResponseBody(gson.toJson(responseEntity.getBody()));

		if (statusCodeValue == 200) {
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

	private static class insurancedtoRowMapper implements RowMapper<InsuranceDTO> {
		@Override
		public InsuranceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			InsuranceDTO dto = new InsuranceDTO();
			
			dto.setMobile_no(rs.getString("mobile_no"));
			dto.setCustomer_id(rs.getString("customer_id"));
			dto.setSaswat_insurance_number_1(rs.getString("saswat_insurance_number_1"));
			dto.setSaswat_insurance_number_2(rs.getString("saswat_insurance_number_2"));
			dto.setSaswat_insurance_number_3(rs.getString("saswat_insurance_number_3"));
			dto.setSaswat_insurance_number_4(rs.getString("saswat_insurance_number_4"));
			dto.setSaswat_insurance_number_5(rs.getString("saswat_insurance_number_5"));
			dto.setInsurance_exists(rs.getString("insurance_exists"));
			dto.setTotal(rs.getString("total"));
			dto.setLanguage_selected(rs.getString("language_selected"));
			return dto;
		}
	}

}
