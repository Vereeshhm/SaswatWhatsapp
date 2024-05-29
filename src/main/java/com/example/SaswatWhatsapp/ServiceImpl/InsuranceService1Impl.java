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

import com.example.SaswatWhatsapp.Repository.Insurance1Repo;
import com.example.SaswatWhatsapp.Service.InsuranceService1;
import com.example.SaswatWhatsapp.Utils.Insurance1Dto;


@Service
public class InsuranceService1Impl implements InsuranceService1 {

	
	
	@Autowired
	Insurance1Repo insurance1Repo;

	public void Insurance1Repo(Insurance1Repo insurance1Repo) {
		this.insurance1Repo = insurance1Repo;
	}

	private JdbcTemplate jdbcTemplate;

	public InsuranceService1Impl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public ResponseEntity<Insurance1Dto> getinsurance1ByMobileNumber(String mobile_no) throws IOException {
		
		
		
		
		List<Insurance1Dto> resultsJPA = this.insurance1Repo.findByMobile_no(mobile_no);

		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT mobile_no, customer_id, policy_no_of_insurance,insurance_from , insurance_status_active,insurance_type,insured_amount,insurance_validity_date,saswat_loan_number,no_of_cattle,insured_date,loan_from FROM insurancetb2 WHERE mobile_no = ?";

			List<Insurance1Dto> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new insurance2dtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {

				return processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			return processResults(resultsJPA.get(0));

		}
	}
	
	private ResponseEntity<Insurance1Dto> processResults(Insurance1Dto dto) throws IOException {

		return ResponseEntity.ok(dto);
	}

	private static class insurance2dtoRowMapper implements RowMapper<Insurance1Dto> {
		@Override
		public Insurance1Dto mapRow(ResultSet rs, int rowNum) throws SQLException {
			Insurance1Dto dto = new Insurance1Dto();
			// Map ResultSet columns to DTO fields
			dto.setCustomer_id(rs.getString("customer_id"));
			dto.setInsurance_from(rs.getString("insurance_from"));
			dto.setMobile_no(rs.getString("mobile_no"));
			dto.setInsurance_status_active(rs.getString("insurance_status_active"));
			dto.setInsurance_type(rs.getString("insurance_type"));
			dto.setInsurance_validity_date(rs.getString("insurance_validity_date"));
			dto.setInsured_amount(rs.getString("insured_amount"));
			dto.setLoan_from(rs.getString("loan_from"));
			dto.setSaswat_loan_number(rs.getString("saswat_loan_number"));
			dto.setPolicy_no_of_insurance(rs.getString("policy_no_of_insurance"));
			dto.setNo_of_cattle(rs.getString("no_of_cattle"));
			dto.setInsured_date(rs.getString("insured_date"));
			return dto;
		}
	}

	
	
}
