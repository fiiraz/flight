package com.restapi.sockproject.controller;

import com.restapi.sockproject.model.Business;
import com.restapi.sockproject.model.Cheap;
import com.restapi.sockproject.service.BusinessService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/business/")
@Api(value = "BusinessControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessController {

    private BusinessService businessService;

    @Autowired
    public void setBusinessService(BusinessService businessService) {
        this.businessService = businessService;
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Business>> getAll(){
        List<Business> businessList = businessService.getAll();
        return new ResponseEntity<List<Business>>(businessList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Business saveBusiness(@RequestBody Business businessToSave) {
        return businessService.saveBusiness(businessToSave);
    }

    //http://localhost:8080/api/business/sortPageable?page=1&size=2&sort=route,DESC
    @RequestMapping(value = "sortPageable", method = RequestMethod.GET)
    public Page<Business> sortPageable(Pageable pageable) {

        return businessService.getPageable(pageable);
    }

    //http://localhost:8080/api/business/searchPageable?departure=Istan&page=0&size=1
    @RequestMapping(value = "searchPageable", method = RequestMethod.GET)
    public List<Business> searchPageable(
            @RequestParam("departure") String departure,
            Pageable pageable
    ) {

        return businessService.findByDeparture(departure, pageable);
    }


}
