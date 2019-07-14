package com.restapi.sockproject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.sockproject.model.Business;
import com.restapi.sockproject.model.Cheap;
import com.restapi.sockproject.repository.BusinessRepository;
import com.restapi.sockproject.repository.CheapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class JavaSpringBootRestApiApplication implements CommandLineRunner {

    private CheapRepository cheapRepository;
    private BusinessRepository businessRepository;

    @Autowired
    public void cheapRepository(CheapRepository cheapRepository) {
        this.cheapRepository = cheapRepository;
    }

    @Autowired
    public void businessRepository(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringBootRestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Cheap>> typeReference = new TypeReference<List<Cheap>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/cheap.json");
        try {
            List<Cheap> cheaps = mapper.readValue(inputStream,typeReference);
            for (Cheap chp: cheaps) {
                cheapRepository.save(chp);
            }
        } catch (IOException e){
            System.out.println("Unable to save cheap: " + e.getMessage());
        }

        ObjectMapper mapper2 = new ObjectMapper();
        TypeReference<List<Business>> typeReference2 = new TypeReference<List<Business>>(){};
        InputStream inputStream2 = TypeReference.class.getResourceAsStream("/json/business.json");
        try {
            List<Business> cheaps = mapper2.readValue(inputStream2,typeReference2);
            for (Business chp: cheaps) {
                businessRepository.save(chp);
            }
        } catch (IOException e){
            System.out.println("Unable to save business: " + e.getMessage());
        }
    }
}
