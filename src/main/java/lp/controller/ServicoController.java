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

import lp.model.Servico;
import lp.service.ServicoService;
import lp.util.ApiError;


@RestController
public class ServicoController {

	@Autowired
	private ServicoService service;

	@RequestMapping(value = "/servicos/{descricao}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<Servico>> listAll(@PathVariable("descricao") String descricao) {

		List<Servico> todos = service.searchByFilters(descricao.replaceAll("descricao=", ""));

		if (todos.isEmpty()) {
			return new ResponseEntity<List<Servico>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Servico>>(todos, HttpStatus.OK);
	}

	// curl --header "Content-Type: application/json" --request POST --data
	// '{"description":"xyz"}' http://localhost:8080/tarefas_rest/todo
	// @RequestMapping(value = "/todo", method = RequestMethod.POST)
	@RequestMapping(value = "/servico", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> salvarServico(@RequestBody Servico todo, UriComponentsBuilder ucBuilder) {

		List<String> erros = new ArrayList<String>();
		
		if(todo != null) {
			
			if(todo.getDescricao() == null || todo.getDescricao().trim().isEmpty()) {
				
				erros.add("A descrição é obrigatória.");
			}
			
			if(todo.getValor() == null) {
				
				erros.add("Valor é obrigatório");
			}
		}
		
		if(!erros.isEmpty()) {
			
			 ApiError apiError = 
				      new ApiError(HttpStatus.BAD_REQUEST, "ssss", erros);
				    return new ResponseEntity<Object>(
				      apiError, new HttpHeaders(), apiError.getStatus());
		}else {
			
			service.create(todo);

			System.out.println("Entrou");

			HttpHeaders headers = new HttpHeaders();

			headers.setLocation(ucBuilder.path("/servico/{id}").buildAndExpand(todo.getId()).toUri());
			
			return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
			
		}
		

	}

	// curl --header "Content-Type: application/json" --request DELETE
	// http://localhost:8080/tarefas_rest/todo/2
	@RequestMapping(value = "/servico/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> deletetodo(@PathVariable("id") String id) {

		System.out.println("deletou");

		service.delete(Long.valueOf(id));

		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);

	}

	// curl -X PUT -H "Content-Type: application/json" -d '{"done": "true"}'
	// http://localhost:8080/tarefas_rest/todo/5
	@RequestMapping(value = "/servico/{id}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	public Servico updatetodo(@PathVariable("id") String id, @RequestBody Servico done) {

		Servico todo = service.getById(Long.valueOf(id));

		todo.setDescricao(done.getDescricao());
		todo.setValor(done.getValor());
		
		service.update(todo);

		System.out.println("Update");

		return todo;

	}

}
