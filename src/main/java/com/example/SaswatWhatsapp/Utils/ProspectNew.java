package com.example.SaswatWhatsapp.Utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="prospect_new_user")
public class ProspectNew {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	 @Column(name = "mobile", unique = true, nullable = false)
	private String mobile;
	
	
	private String statusof_application;
	
	private String language_selected;
	
	private String pin_code;
	
	
	private String state;
	
	private String district;
	
	private LocalDateTime timestamp = LocalDateTime.now();
	
	private String Status;

	

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatusof_application() {
		return statusof_application;
	}

	public void setStatusof_application(String statusof_application) {
		this.statusof_application = statusof_application;
	}

	public String getLanguage_selected() {
		return language_selected;
	}

	public void setLanguage_selected(String language_selected) {
		this.language_selected = language_selected;
	}

	public String getPin_code() {
		return pin_code;
	}

	public void setPin_code(String pin_code) {
		this.pin_code = pin_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public ProspectNew() {
		this.timestamp=LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
	}
}
