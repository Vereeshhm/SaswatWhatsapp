package com.example.SaswatWhatsapp.Utils;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="insurancetb1")

public class InsuranceDTO {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mobile_no")
	private String mobile_no;
    
	@Column(name = "customer_id")
	private String customer_id;

	@Column(name = "saswat_insurance_number_1")
	private String saswat_insurance_number_1;

	@Column(name = "saswat_insurance_number_2")
	private String saswat_insurance_number_2;

	@Column(name = "saswat_insurance_number_3")
	private String saswat_insurance_number_3;

	@Column(name = "saswat_insurance_number_4")
	private String saswat_insurance_number_4;

	@Column(name = "saswat_insurance_number_5")
	private String saswat_insurance_number_5;

	@Column(name = "insurance_exists")
	private String insurance_exists;

	@Column(name = "total")
	private String total;

	@Column(name = "language_selected")
	private String language_selected;

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getSaswat_insurance_number_1() {
		return saswat_insurance_number_1;
	}

	public void setSaswat_insurance_number_1(String saswat_insurance_number_1) {
		this.saswat_insurance_number_1 = saswat_insurance_number_1;
	}

	public String getSaswat_insurance_number_2() {
		return saswat_insurance_number_2;
	}

	public void setSaswat_insurance_number_2(String saswat_insurance_number_2) {
		this.saswat_insurance_number_2 = saswat_insurance_number_2;
	}

	public String getSaswat_insurance_number_3() {
		return saswat_insurance_number_3;
	}

	public void setSaswat_insurance_number_3(String saswat_insurance_number_3) {
		this.saswat_insurance_number_3 = saswat_insurance_number_3;
	}

	public String getSaswat_insurance_number_4() {
		return saswat_insurance_number_4;
	}

	public void setSaswat_insurance_number_4(String saswat_insurance_number_4) {
		this.saswat_insurance_number_4 = saswat_insurance_number_4;
	}

	public String getSaswat_insurance_number_5() {
		return saswat_insurance_number_5;
	}

	public void setSaswat_insurance_number_5(String saswat_insurance_number_5) {
		this.saswat_insurance_number_5 = saswat_insurance_number_5;
	}

	public String getInsurance_exists() {
		return insurance_exists;
	}

	public void setInsurance_exists(String insurance_exists) {
		this.insurance_exists = insurance_exists;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getLanguage_selected() {
		return language_selected;
	}

	public void setLanguage_selected(String language_selected) {
		this.language_selected = language_selected;
	}

	@Override
	public String toString() {
		return "InsuranceDTO [mobile_no=" + mobile_no + ", customer_id=" + customer_id + ", saswat_insurance_number_1="
				+ saswat_insurance_number_1 + ", saswat_insurance_number_2=" + saswat_insurance_number_2
				+ ", saswat_insurance_number_3=" + saswat_insurance_number_3 + ", saswat_insurance_number_4="
				+ saswat_insurance_number_4 + ", saswat_insurance_number_5=" + saswat_insurance_number_5
				+ ", insurance_exists=" + insurance_exists + ", total=" + total + ", language_selected="
				+ language_selected + "]";
	}

	
	
}
