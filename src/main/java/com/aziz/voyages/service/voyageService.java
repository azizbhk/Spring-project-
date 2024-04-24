package com.aziz.voyages.service;

import java.util.List;

import org.springframework.data.domain.Page;

//import org.hibernate.query.Page;
//import org.springframework.data.domain.Page;
import com.aziz.voyages.typevoyages.voyage;

public interface voyageService {
voyage savevoyage(voyage v);
voyage uvdatevoyage(voyage v);
void deletevoyage(voyage v);
 void deletevoyageById(Long id);
voyage getvoyage(Long id);
List<voyage> getAllvoyages();
voyage updatevoyage(voyage v);
Page<voyage> getAllvoyagesParPage1(int page, int size);
org.springframework.data.domain.Page<voyage> getAllvoyagesParPage(int page, int size);
}

