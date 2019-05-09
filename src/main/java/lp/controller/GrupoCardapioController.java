package lp.controller;

import java.util.ArrayList;
import java.util.Date;
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

import lp.model.GrupoCardapio;
import lp.service.GrupoCardapioService;
import lp.util.ApiError;


@RestController
public class GrupoCardapioController {

	@Autowired
	private GrupoCardapioService service;

	@RequestMapping(value = "/grupos/{nome}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<GrupoCardapio>> listAll(@PathVariable("nome") String descricao) {

		List<GrupoCardapio> todos = service.searchByFilters(descricao.replaceAll("nome=", ""));

		if (todos.isEmpty()) {
			return new ResponseEntity<List<GrupoCardapio>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<GrupoCardapio>>(todos, HttpStatus.OK);
	}

	// curl --header "Content-Type: application/json" --request POST --data
	// '{"description":"xyz"}' http://localhost:8080/tarefas_rest/todo
	// @RequestMapping(value = "/todo", method = RequestMethod.POST)
	@RequestMapping(value = "/grupo", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<Object> salvar(@RequestBody GrupoCardapio todo, UriComponentsBuilder ucBuilder) {

		List<String> erros = new ArrayList<String>();
		
		if(todo != null) {
			
			if(todo.getNome() == null || todo.getNome().trim().isEmpty()) {
				
				erros.add("O Nome é obrigatório.");
			}
			
			
		}
		
		if(!erros.isEmpty()) {
			
			 ApiError apiError = 
				      new ApiError(HttpStatus.BAD_REQUEST, "Erros", erros);
				    return new ResponseEntity<Object>(
				      apiError, new HttpHeaders(), apiError.getStatus());
		}else {
			
//			todo.setCreatedDate(new Date());
//			todo.setModified(new Date());
			service.create(todo);

			HttpHeaders headers = new HttpHeaders();

			headers.setLocation(ucBuilder.path("/grupo/{id}").buildAndExpand(todo.getId()).toUri());
			
			return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
			
		}
		

	}

	// curl --header "Content-Type: application/json" --request DELETE
	// http://localhost:8080/tarefas_rest/todo/2
	@RequestMapping(value = "/grupo/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {

		service.delete(Long.valueOf(id));

		HttpHeaders headers = new HttpHeaders();

		return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);

	}

	// curl -X PUT -H "Content-Type: application/json" -d '{"done": "true"}'
	// http://localhost:8080/tarefas_rest/todo/5
	@RequestMapping(value = "/grupo/{id}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	public GrupoCardapio update(@PathVariable("id") String id, @RequestBody GrupoCardapio done) {

		GrupoCardapio todo = service.getById(Long.valueOf(id));

		todo.setNome(done.getNome());
		
		service.update(todo);

		return todo;

	}

}
