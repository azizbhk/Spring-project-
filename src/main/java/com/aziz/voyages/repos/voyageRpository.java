package com.aziz.voyages.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aziz.voyages.typevoyages.voyage;

public interface voyageRpository extends JpaRepository<voyage, Long> {

}
