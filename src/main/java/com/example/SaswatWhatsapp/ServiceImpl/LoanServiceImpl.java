package com.example.SaswatWhatsapp.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Override
	public ResponseEntity<Loandto> getloanrecordsByMobileNumber(String mobile_no, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		List<Loandto> resultsJPA = this.loanRepository.findByMobile_no(mobile_no);

		String requestUrl = request.getRequestURL().toString();
		Gson gson = new Gson();

		String requestBodyJson = gson.toJson(mobile_no);

		LoanApiLogs apiLogs = new LoanApiLogs();
		apiLogs.setUrl(requestUrl);
		apiLogs.setRequestBody(requestBodyJson);

		ResponseEntity<Loandto> responseEntity;
		
		if (resultsJPA.isEmpty()) {
			NewCustomentity entity = new NewCustomentity();
			entity.setMobile_no(mobile_no);
			customRepository.save(entity);
			String errorMessage = "User with mobile number " + mobile_no + " does'nt exists";
			apiLogs.setResponseBody(gson.toJson(errorMessage));

			apiRepository.save(apiLogs);

			return new ResponseEntity(errorMessage, HttpStatus.CONFLICT);

		} else {
			// Mobile number found in JPA

			Loandto dto = resultsJPA.get(0);
			responseEntity = ResponseEntity.ok(dto);
		}

		apiLogs.setResponseBody(gson.toJson(responseEntity.getBody()));

		apiRepository.save(apiLogs);
		return responseEntity;
	}

}