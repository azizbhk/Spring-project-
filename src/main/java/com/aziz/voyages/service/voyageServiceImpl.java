package com.aziz.voyages.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

//import com.aziz.voyages.typevoyages.Categorie;
import com.aziz.voyages.typevoyages.voyage;
//import com.aziz.voyages.repos.CategorieRepository;
//import com.aziz.voyages.repos.voyageRepository;

import com.aziz.voyages.repos.voyageRpository;
@Service
public abstract class voyageServiceImpl implements voyageService {
@Autowired
voyageRpository voyageRpository;
@Override
public voyage savevoyage(voyage v) {
return voyageRpository.save(v);
}
@Override
public voyage updatevoyage(voyage v) {
return voyageRpository.save(v);
}
@Override
public void deletevoyage(voyage v) {
voyageRpository.delete(v);
}
 @Override
public void deletevoyageById(Long id) {
voyageRpository.deleteById(id);
}
@Override
public voyage getvoyage(Long id) {
return voyageRpository.findById(id).get();
}
@Override
public List<voyage> getAllvoyages() {
return voyageRpository.findAll();

}
@Override
public Page<voyage> getAllvoyagesParPage(int page, int size) {
return voyageRpository.findAll(PageRequest.of(page, size));
}
}