package com.example.SaswatWhatsapp.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SaswatWhatsapp.Repository.LanguageRepository;
import com.example.SaswatWhatsapp.Utils.Language;

@Service
public class LanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	public List<Language> getAllLanguages() {
		return languageRepository.findAll();
	}

	public Language addLanguage(String languageName) {
		Language existingLanguage = languageRepository.findByNameIgnoreCase(languageName);
		if (existingLanguage != null) {
			return existingLanguage; // Language already exists
		}
		Language newLanguage = new Language(languageName);
		return languageRepository.save(newLanguage); // Save and return the new language
	}

	public List<Language> addLanguages(List<String> languageNames) {
		Set<String> uniqueLanguageNames = new HashSet<>();
		List<Language> addedLanguages = new ArrayList<>();

		for (String name : languageNames) {
			String lowerCaseName = name.toLowerCase(); // Convert name to lower case

			// Only process each language name once
			if (!uniqueLanguageNames.contains(lowerCaseName)) {
				uniqueLanguageNames.add(lowerCaseName); // Track processed names

				// Check if language already exists in database
				Language existingLanguage = languageRepository.findByNameIgnoreCase(name);
				if (existingLanguage != null) {
					addedLanguages.add(existingLanguage); // Add existing language
				} else {
					Language newLanguage = new Language(name);
					addedLanguages.add(languageRepository.save(newLanguage)); // Save and add new language
				}
			}
		}

		return addedLanguages;
	}

}