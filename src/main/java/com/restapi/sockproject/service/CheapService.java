package com.restapi.sockproject.service;

import com.restapi.sockproject.model.Cheap;
import com.restapi.sockproject.repository.CheapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheapService {
    private CheapRepository cheapRepository;

    @Autowired
    public void setCheapRepository(CheapRepository cheapRepository) {
        this.cheapRepository = cheapRepository;
    }

    public List<Cheap> getAll(){
        List<Cheap> list = new ArrayList<>();
        cheapRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    public Cheap saveCheap(Cheap cheap) {
        Cheap cheapToSave;
        try {
            cheapToSave = cheapRepository.save(cheap);
            return cheapToSave;
        } catch (Exception e) {
        }
        return new Cheap();
    }

    public Page<Cheap> getPageable(Pageable pageable) {
        return cheapRepository.findAll(pageable);
    }


    public List<Cheap> findByRoute(String route, Pageable pageable){
        return cheapRepository.findByRouteLike("%"+route+"%", pageable);
    }
}
