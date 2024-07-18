package com.example.SaswatWhatsapp.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SaswatWhatsapp.Exception.ResourceNotFoundException;
import com.example.SaswatWhatsapp.Repository.DistrictRepository;
import com.example.SaswatWhatsapp.Repository.StateDistrictRepository;
import com.example.SaswatWhatsapp.Repository.TalukRepository;
import com.example.SaswatWhatsapp.Utils.District;
import com.example.SaswatWhatsapp.Utils.StateDistrict;
import com.example.SaswatWhatsapp.Utils.Taluk;

@Service
public class DistrictService {

    @Autowired
    private StateDistrictRepository stateDistrictRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private TalukRepository talukRepository;

    public void addStatesWithDistrictsAndTaluks(List<StateDistrict> stateDistricts) {
        for (StateDistrict stateDistrict : stateDistricts) {
            Optional<StateDistrict> existingStateDistrict = stateDistrictRepository
                    .findByStateIgnoreCase(stateDistrict.getState());
            if (existingStateDistrict.isPresent()) {
                stateDistrict = existingStateDistrict.get();
            } else {
                stateDistrict = stateDistrictRepository.save(stateDistrict);
            }

            for (District district : stateDistrict.getDistricts()) {
                district.setStateDistrict(stateDistrict); 
                Optional<District> existingDistrict = districtRepository
                        .findByNameIgnoreCaseAndStateDistrict(district.getName(), stateDistrict);
                if (existingDistrict.isPresent()) {
                    district = existingDistrict.get();
                } else {
                    district = districtRepository.save(district);
                }

                for (Taluk taluk : district.getTaluks()) {
                    taluk.setDistrict(district); 
                    Optional<Taluk> existingTaluk = talukRepository.findByNameIgnoreCaseAndDistrict(taluk.getName(),
                            district);
                    if (!existingTaluk.isPresent()) {
                        talukRepository.save(taluk);
                    }
                }
            }
        }
    }

    public List<StateDistrict> getAllStates() {
        return stateDistrictRepository.findAll();
    }

    public Set<District> getDistrictsByStateId(Long stateId) {
        Optional<StateDistrict> stateDistrict = stateDistrictRepository.findById(stateId);

        if (stateDistrict.isPresent()) {
            return stateDistrict.get().getDistricts();
        } else {
            throw new ResourceNotFoundException("State not found with ID: " + stateId);
        }
    }

    public Set<Taluk> getTaluksByDistrictId(Long districtId) {
        Optional<District> district = districtRepository.findById(districtId);
        if (district.isPresent()) {
            return district.get().getTaluks();
        } else {
            throw new ResourceNotFoundException("District not found with ID: " + districtId);
        }
    }
}
