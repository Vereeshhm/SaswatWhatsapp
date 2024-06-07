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

import com.example.SaswatWhatsapp.Repository.LoanApiRepository;
import com.example.SaswatWhatsapp.Repository.LoanRepository;
import com.example.SaswatWhatsapp.Repository.NewCustomRepository;
import com.example.SaswatWhatsapp.Service.LoanService;
import com.example.SaswatWhatsapp.Utils.LoanApiLogs;
import com.example.SaswatWhatsapp.Utils.Loandto;
import com.example.SaswatWhatsapp.Utils.NewCustomentity;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
   LoanRepository loanRepository;

	@Autowired
	private NewCustomRepository customRepository;

	@Autowired
	private LoanApiRepository apiRepository;

	private JdbcTemplate jdbcTemplate;

	
	public LoanServiceImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public ResponseEntity<Loandto> getloanrecordsByMobileNumber(String mobile_no, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<Loandto> resultsJPA = this.loanRepository.findByMobile_no(mobile_no);

		String requestUrl = request.getRequestURL().toString();
		Gson gson = new Gson();

		// Capture request body
		String requestBodyJson = gson.toJson(mobile_no);
		int statusCodeValue = response.getStatus();

		LoanApiLogs apiLogs = new LoanApiLogs();
		apiLogs.setUrl(requestUrl);
		apiLogs.setRequestBody(requestBodyJson);

		ResponseEntity<Loandto> responseEntity;
		// If no records found in JPA repository, fall back to JdbcTemplate
		if (resultsJPA.isEmpty()) {
			String query = "SELECT mobile_no, saswat_loan_number, loan_amt,emi_amt , emi_date,total_no_installment,overdue_amt,overdue_interest FROM loan_details WHERE mobile_no = ?";

			List<Loandto> resultsJdbcTemplate = jdbcTemplate.query(query, ps -> ps.setString(1, mobile_no),
					new LoandtoRowMapper());

			if (resultsJdbcTemplate.isEmpty()) {
				NewCustomentity entity = new NewCustomentity();
				entity.setMobile_no(mobile_no);
				customRepository.save(entity);
				responseEntity = ResponseEntity.notFound().build();
			} else {

				responseEntity = processResults(resultsJdbcTemplate.get(0));

			}
		} else {
			responseEntity = processResults(resultsJPA.get(0));

		}
		apiLogs.setResponseBody(gson.toJson(responseEntity.getBody()));

		apiRepository.save(apiLogs);
		return responseEntity;
	}

	private ResponseEntity<Loandto> processResults(Loandto dto) throws IOException {
		return ResponseEntity.ok(dto);
	}

	private static class LoandtoRowMapper implements RowMapper<Loandto> {
		@Override
		public Loandto mapRow(ResultSet rs, int rowNum) throws SQLException {
			Loandto dto = new Loandto();

			dto.setMobile_no(rs.getString("mobile_no"));
			dto.setSaswat_loan_number(rs.getString("saswat_loan_number"));
			dto.setLoan_amt(rs.getString("loan_amt"));
			dto.setEmi_amt(rs.getString("emi_amt"));
			dto.setEmi_date(rs.getString("emi_date"));
			dto.setTotal_no_installment(rs.getString("total_no_installment"));
			dto.setOverdue_amt(rs.getString("overdue_amt"));
			dto.setOverdue_interest(rs.getString("overdue_interest"));

			return dto;
		}
	}

}
