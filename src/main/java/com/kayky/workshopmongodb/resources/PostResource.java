package com.kayky.workshopmongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kayky.workshopmongodb.domain.Post;
import com.kayky.workshopmongodb.dto.PostDTO;
import com.kayky.workshopmongodb.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PostDTO>> findAll() {

		List<Post> list = service.findAll();
		List<PostDTO> listDto = list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(new PostDTO(obj));
	}


}
