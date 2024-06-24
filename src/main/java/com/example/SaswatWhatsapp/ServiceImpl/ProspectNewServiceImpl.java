package com.example.SaswatWhatsapp.ServiceImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.SaswatWhatsapp.Repository.ProspectNewRepository;
import com.example.SaswatWhatsapp.Service.ProspectNewService;
import com.example.SaswatWhatsapp.Utils.ProspectNew;

@Service
public class ProspectNewServiceImpl implements ProspectNewService {

	@Autowired
	ProspectNewRepository newRepository;

	@Override
	public ResponseEntity<ProspectNew> updateUserRecordsByMobileNumber(String mobile) {

		if (newRepository.existsByMobile(mobile)) {

			String errorMessage = "User with mobile number " + mobile + " already exists";

			return new ResponseEntity(errorMessage, HttpStatus.CONFLICT);

		}

		ProspectNew newdto = new ProspectNew();

		newdto.setMobile(mobile);
		newdto.setStatusof_application("New");
		newdto.setLanguage_selected("English");
		newdto.setDistrict("Pune");
		newdto.setState("Maharashtra");
		newdto.setPin_code("5600782");
		LocalDateTime timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
		newdto.setTimestamp(timestamp);
		newdto.setStatus("Interested");

		newRepository.save(newdto);
		return ResponseEntity.ok(newdto);

	}

}
