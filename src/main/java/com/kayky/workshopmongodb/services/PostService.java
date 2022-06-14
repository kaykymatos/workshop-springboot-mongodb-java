package com.kayky.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kayky.workshopmongodb.domain.Post;
import com.kayky.workshopmongodb.dto.PostDTO;
import com.kayky.workshopmongodb.repository.PostRepository;
import com.kayky.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public List<Post> findAll() {
		return repository.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
	}

	public Post insert(Post obj) {
		return repository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	public Post fromDTO(PostDTO objDto) {
		return new Post(objDto.getId(), objDto.getDate(), objDto.getTitle(), objDto.getBody(), objDto.getAuthor());
	}
}
