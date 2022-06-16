package com.kayky.workshopmongodb.resources;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kayky.workshopmongodb.domain.Post;
import com.kayky.workshopmongodb.dto.PostDTO;
import com.kayky.workshopmongodb.resources.util.URL;
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
	
	//http://localhost:8080/posts/titlesearch?text=Acordei%20feliz!
	@RequestMapping(value="/titlesearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
    //http://localhost:8080/posts/fullsearch?text=Acordei%20feliz!&minDate=2018-03-20&maxDate=2019-03-20
	@RequestMapping(value="/fullsearch", method=RequestMethod.GET)
 	public ResponseEntity<List<Post>> fullSearch(
 			@RequestParam(value="text", defaultValue="") String text,
 			@RequestParam(value="minDate", defaultValue="") String minDate,
 			@RequestParam(value="maxDate", defaultValue="") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> list = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}
	


}
