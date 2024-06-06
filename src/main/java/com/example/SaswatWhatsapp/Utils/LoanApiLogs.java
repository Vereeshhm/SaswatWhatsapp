package com.example.SaswatWhatsapp.Utils;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name="loan_api_logs")
public class LoanApiLogs {

	
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "loan_api_logs_seq")

	@SequenceGenerator(name = "loan_api_logs_seq", sequenceName = "loan_api_logs_seq", allocationSize = 1)
	private Long id;

	private String url;

	@Type(io.hypersistence.utils.hibernate.type.json.JsonBinaryType.class)
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "request_body", columnDefinition = "jsonb")
	private String requestBody;
	
	@Type(io.hypersistence.utils.hibernate.type.json.JsonBinaryType.class)
	@JdbcTypeCode(SqlTypes.JSON)
	@Column(name = "response_body", columnDefinition = "jsonb")
	private String responseBody;

	
	private LocalDateTime timestamp = LocalDateTime.now();


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getRequestBody() {
		return requestBody;
	}


	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}


	public String getResponseBody() {
		return responseBody;
	}


	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
