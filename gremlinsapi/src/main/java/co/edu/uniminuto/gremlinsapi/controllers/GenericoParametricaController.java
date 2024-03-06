package co.edu.uniminuto.gremlinsapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.gremlinsapi.business.GenericoParametricaBusinessImp;
import co.edu.uniminuto.gremlinsapi.dtos.TableDto;




@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/parameters")
public class GenericoParametricaController {
	
	@Autowired
	GenericoParametricaBusinessImp ImpgenericoParametres;
	
	@GetMapping("/getAll")
	public List<TableDto> getAllEmployees() {
		List<TableDto> listOfPostDTO = null;
		


		try {
			listOfPostDTO = ImpgenericoParametres.findAllParametricasSystem();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return (listOfPostDTO);
	}
	

}
