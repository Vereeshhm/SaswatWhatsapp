package com.example.SaswatWhatsapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SaswatWhatsapp.Utils.LoanApiLogs;

public interface LoanApiRepository extends JpaRepository<LoanApiLogs, Long>{

}
