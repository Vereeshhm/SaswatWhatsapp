package com.example.SaswatWhatsapp.Utils;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="loan_details")
public class Loandto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String mobile_no;
	
	
	private String saswat_loan_number;
	
	
	private String loan_amt;
	
	private String emi_amt;
	
	private String emi_date;
	
	private String total_no_installment;
	
	private String overdue_amt;
	
	private String overdue_interest;

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getSaswat_loan_number() {
		return saswat_loan_number;
	}

	public void setSaswat_loan_number(String saswat_loan_number) {
		this.saswat_loan_number = saswat_loan_number;
	}

	public String getLoan_amt() {
		return loan_amt;
	}

	public void setLoan_amt(String loan_amt) {
		this.loan_amt = loan_amt;
	}

	public String getEmi_amt() {
		return emi_amt;
	}

	public void setEmi_amt(String emi_amt) {
		this.emi_amt = emi_amt;
	}

	public String getEmi_date() {
		return emi_date;
	}

	public void setEmi_date(String emi_date) {
		this.emi_date = emi_date;
	}

	public String getTotal_no_installment() {
		return total_no_installment;
	}

	public void setTotal_no_installment(String total_no_installment) {
		this.total_no_installment = total_no_installment;
	}

	public String getOverdue_amt() {
		return overdue_amt;
	}

	public void setOverdue_amt(String overdue_amt) {
		this.overdue_amt = overdue_amt;
	}

	public String getOverdue_interest() {
		return overdue_interest;
	}

	public void setOverdue_interest(String overdue_interest) {
		this.overdue_interest = overdue_interest;
	}

	
	
	
}
