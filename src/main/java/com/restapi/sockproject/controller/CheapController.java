package com.restapi.sockproject.controller;

import com.restapi.sockproject.model.Cheap;
import com.restapi.sockproject.service.CheapService;
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
@RequestMapping(path = "/api/cheap/")
@Api(value = "CheapControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheapController {

    private CheapService cheapService;

    @Autowired
    public void setCheapsService(CheapService cheapsService) {
        this.cheapService = cheapsService;
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<Cheap>> getAll(){
        List<Cheap> cheapList = cheapService.getAll();
        return new ResponseEntity<List<Cheap>>(cheapList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cheap saveCheap(@RequestBody Cheap cheapToSave) {
        return cheapService.saveCheap(cheapToSave);
    }

    //http://localhost:8080/api/cheap/sortPageable?page=1&size=2&sort=route,DESC
    @RequestMapping(value = "sortPageable", method = RequestMethod.GET)
    public Page<Cheap> sortPageable(Pageable pageable) {

        return cheapService.getPageable(pageable);
    }

    //http://localhost:8080/api/cheap/searchPageable?route=Istanbul&page=0&size=1
    @RequestMapping(value = "searchPageable", method = RequestMethod.GET)
    public List<Cheap> searchPageable(
            @RequestParam("route") String route,
            Pageable pageable
            ) {

        return cheapService.findByRoute(route, pageable);
    }
}
