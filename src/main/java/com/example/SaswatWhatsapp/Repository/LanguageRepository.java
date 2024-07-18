package com.example.SaswatWhatsapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SaswatWhatsapp.Utils.Language;

public interface LanguageRepository extends JpaRepository<Language, Long>{

	 Language findByNameIgnoreCase(String name);
	    boolean existsByNameIgnoreCase(String name);

}
