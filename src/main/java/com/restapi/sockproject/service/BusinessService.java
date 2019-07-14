package com.restapi.sockproject.service;

import com.restapi.sockproject.model.Business;
import com.restapi.sockproject.model.Cheap;
import com.restapi.sockproject.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessService {
    private BusinessRepository businessRepository;

    @Autowired
    public void setBusinessRepository(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public List<Business> getAll(){
        List<Business> list = new ArrayList<>();
        businessRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public Business saveBusiness(Business business) {
        Business businessToSave;
        try {
            businessToSave = businessRepository.save(business);
            return businessToSave;
        } catch (Exception e) {
        }
        return new Business();
    }

    public Page<Business> getPageable(Pageable pageable) {
        return businessRepository.findAll(pageable);
    }

    public List<Business> findByDeparture(String departure, Pageable pageable){
        return businessRepository.findByDepartureLike("%"+departure+"%", pageable);
    }


}
