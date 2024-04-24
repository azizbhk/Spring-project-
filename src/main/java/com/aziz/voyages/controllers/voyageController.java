package com.aziz.voyages.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.aziz.voyages.typevoyages.Categorie;
import com.aziz.voyages.typevoyages.voyage;
import com.aziz.voyages.service.voyageService;

//import jakarta.validation.Valid;

@Controller
public class voyageController {
	@Autowired
	voyageService voyageService;
	
	
		   @RequestMapping("/Listevoyages")
			public String listevoyages(ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
										@RequestParam (name="size", defaultValue = "2") int size)
			{
			Page<voyage> voys = voyageService.getAllvoyagesParPage(page, size);
				modelMap.addAttribute("voyages", voys);
		         modelMap.addAttribute("pages", new int[voys.getTotalPages()]);	
				modelMap.addAttribute("currentPage", page);			
				return "listevoyages";	
			}


		   @RequestMapping("/showCreate")
			public String showCreate(ModelMap modelMap)
			{
				modelMap.addAttribute("voyage", new voyage());
				List<voyage> cats = voyageService.getAllvoyages();
				modelMap.addAttribute("mode", "new");
				modelMap.addAttribute("categories", cats);
				return "formvoyage";
			}
		   
		   
			


		   @RequestMapping("/savevoyage")
			public String savevoyage(@Valid voyage voyage, BindingResult bindingResult,
					@RequestParam (name="page",defaultValue = "0") int page,
					@RequestParam (name="size", defaultValue = "2") int size)
			{
				int currentPage;
				boolean isNew = false;
			   if (bindingResult.hasErrors()) return "formvoyage";				  
				
			   if(voyage.getIdvoyage()==null) //ajout
				    isNew=true;
			  			   
			  	voyageService.savevoyage(voyage);
			  	if (isNew) //ajout
			  	{
			  		Page<voyage> voys = voyageService.getAllvoyagesParPage(page, size);
			  		currentPage = voys.getTotalPages()-1;
			  	}
			  	else //modif
			  		currentPage=page;
			  	
			  	
				//return "formvoyage";
				return ("redirect:/Listevoyages?page="+currentPage+"&size="+size);
			}


	  @RequestMapping("/supprimervoyage")
		public String supprimervoyage(@RequestParam("id") Long id,
				ModelMap modelMap,
				@RequestParam (name="page",defaultValue = "0") int page,
				@RequestParam (name="size", defaultValue = "2") int size)
		{

			voyageService.deletevoyageById(id);
			Page<voyage> voys = voyageService.getAllvoyagesParPage(page, size);
			modelMap.addAttribute("voyages", voys);		
			modelMap.addAttribute("pages", new int[voys.getTotalPages()]);	
			modelMap.addAttribute("currentPage", page);	
			modelMap.addAttribute("size", size);	
			return "listevoyages";	
		}


	@RequestMapping("/modifiervoyage")
	public String editervoyage(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		voyage p = voyageService.getvoyage(id);
		List<voyage> cats = voyageService.getAllvoyages();
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("voyage", p);
		modelMap.addAttribute("categories", cats);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("size", size);
		
		return "formvoyage";
	}
	  
	

	@RequestMapping("/updatevoyage")
	public String updatevoyage(@ModelAttribute("voyage") voyage voyage, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		voyage.setDateCreation(dateCreation);

		voyageService.updatevoyage(voyage);
		List<voyage> voys = voyageService.getAllvoyages();
		modelMap.addAttribute("voyages", voys);
		return "listevoyages";
	}
}