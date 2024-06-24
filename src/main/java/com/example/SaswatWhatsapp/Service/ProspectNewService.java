package com.example.SaswatWhatsapp.Service;

import org.springframework.http.ResponseEntity;

import com.example.SaswatWhatsapp.Utils.ProspectNew;

public interface ProspectNewService {

	ResponseEntity<ProspectNew> updateUserRecordsByMobileNumber(String mobile);

}
