package com.example.SaswatWhatsapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.SaswatWhatsapp.Utils.ApiLog;


@Repository

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {

}
