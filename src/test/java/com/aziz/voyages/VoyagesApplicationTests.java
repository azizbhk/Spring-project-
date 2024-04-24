package com.aziz.voyages;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aziz.voyages.repos.voyageRpository;
import com.aziz.voyages.typevoyages.voyage;

@SpringBootTest
class VoyagesApplicationTests {

		@Autowired
		private voyageRpository voyageRepository;
		
		@Test
		public void testCreatevoyage() {
		voyage voy = new voyage("Turkey",4000.2,new Date());
		voyageRepository.save(voy);
		}
		@Test
		public void testFindvoyage()
		{
		voyage v = voyageRepository.findById(1L).get();
		System.out.println(v);
		}
		@Test
		public void testupdatevoyage()
		{
		voyage v = voyageRepository.findById(1L).get();
		v.setPrixvoyage(3000.0);
		voyageRepository.save(v);
		System.out.println(v);
		}
		@Test
		public void testDeletevoyage()
		{
		  voyageRepository.deleteById(1L);;
		}
		@Test
		public void testListerTousvoyages()
		{
		List<voyage> voys = voyageRepository.findAll();
		for (voyage v : voys)
		{
		System.out.println(v);
		}
		}


		
		}



