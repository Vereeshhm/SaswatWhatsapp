package com.example.SaswatWhatsapp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.SaswatWhatsapp.Utils.Loandto;
@Repository
public interface LoanRepository extends JpaRepository<Loandto, Integer>{


	@Query("SELECT s FROM Loandto s WHERE s.mobile_no = :mobileNo")
    List<Loandto> findByMobile_no(String mobileNo);
	
	
}
