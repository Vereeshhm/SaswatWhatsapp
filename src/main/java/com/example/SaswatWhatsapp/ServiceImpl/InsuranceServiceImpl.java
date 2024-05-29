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

import com.example.SaswatWhatsapp.Repository.InsuranceRepo;
import com.example.SaswatWhatsapp.Service.InsuranceService;
import com.example.SaswatWhatsapp.Utils.InsuranceDTO;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	InsuranceRepo insuranceRepo;

	public void InsuranceRepo(InsuranceRepo insuranceRepo) {
		this.insuranceRepo = insuranceRepo;
	}

	private JdbcTemplate jdbcTemplate;

	public InsuranceServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<InsuranceDTO> getinsuranceByMobileNumber(String mobile_no) throws IOException {

		List<InsuranceDTO> resultsJPA = this.insuranceRepo.findByMobile_no(mobile_no);

		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT mobile_no, customer_id, language_selected,insurance_exists , saswat_insurance_number_1,saswat_insurance_number_2,saswat_insurance_number_3,saswat_insurance_number_4,saswat_insurance_number_5,total FROM insurancetb1 WHERE mobile_no = ?";

			List<InsuranceDTO> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new insurancedtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {

				return processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			return processResults(resultsJPA.get(0));

		}

	}

	private ResponseEntity<InsuranceDTO> processResults(InsuranceDTO dto) throws IOException {

		return ResponseEntity.ok(dto);
	}

	private static class insurancedtoRowMapper implements RowMapper<InsuranceDTO> {
		@Override
		public InsuranceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			InsuranceDTO dto = new InsuranceDTO();
			// Map ResultSet columns to DTO fields
			dto.setCustomer_id(rs.getString("customer_id"));
			dto.setInsurance_exists(rs.getString("insurance_exists"));
			dto.setMobile_no(rs.getString("mobile_no"));
			dto.setLanguage_selected(rs.getString("language_selected"));
			dto.setSaswat_insurance_number_1(rs.getString("saswat_insurance_number_1"));
			dto.setSaswat_insurance_number_2(rs.getString("saswat_insurance_number_2"));
			dto.setSaswat_insurance_number_3(rs.getString("saswat_insurance_number_3"));
			dto.setSaswat_insurance_number_4(rs.getString("saswat_insurance_number_4"));
			dto.setSaswat_insurance_number_5(rs.getString("saswat_insurance_number_5"));
			dto.setTotal(rs.getString("total"));
			return dto;
		}
	}

}
