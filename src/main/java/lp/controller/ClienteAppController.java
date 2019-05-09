package lp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lp.model.ClienteApp;
import lp.service.ClienteAppService;
import lp.util.ApiError;


@RestController
public class ClienteAppController {

	@Autowired
	private ClienteAppService service;

	@RequestMapping(value = "/clientesApp/{descricao}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<ClienteApp>> listAll(@PathVariable("descricao") String descricao) {

		List<ClienteApp> todos = service.searchByFilters(descricao.replaceAll("descricao=", ""));

		if (todos.isEmpty()) {
			return new ResponseEntity<List<ClienteApp>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<ClienteApp>>(todos, HttpStatus.OK);
	}

	// curl --header "Content-Type: application/json" --request POST --data
	// '{"description":"xyz"}' http://localhost:8080/tarefas_rest/todo
	// @RequestMapping(value = "/todo", method = RequestMethod.POST)
	@RequestMapping(value = "/clienteApp", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> salvar(@RequestBody ClienteApp todo, UriComponentsBuilder ucBuilder) {

		List<String> erros = new ArrayList<String>();
		
		if(todo != null) {
			
			if(todo.getNome() == null || todo.getNome().trim().isEmpty()) {
				
				erros.add("A descrição é obrigatória.");
			}
			
		}
		
		if(!erros.isEmpty()) {
			
			 ApiError apiError = 
				      new ApiError(HttpStatus.BAD_REQUEST, "Erros", erros);
				    return new ResponseEntity<Object>(
				      apiError, new HttpHeaders(), apiError.getStatus());
		}else {
			
			service.create(todo);

			System.out.println("Entrou");

			HttpHeaders headers = new HttpHeaders();

			headers.setLocation(ucBuilder.path("/clienteApp/{id}").buildAndExpand(todo.getId()).toUri());
			
			return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
			
		}
		

	}



}
