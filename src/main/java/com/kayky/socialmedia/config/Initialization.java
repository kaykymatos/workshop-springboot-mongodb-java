package com.kayky.socialmedia.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.kayky.socialmedia.domain.Post;
import com.kayky.socialmedia.domain.User;
import com.kayky.socialmedia.dto.AuthorDTO;
import com.kayky.socialmedia.dto.CommentDTO;
import com.kayky.socialmedia.repository.PostRepository;
import com.kayky.socialmedia.repository.UserRepository;

@Configuration
public class Initialization implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GTM"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Irei viajar para São Paulo",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("11/06/2018"), "Acordei feliz!", "Bom dia gente!", new AuthorDTO(maria));

		CommentDTO comment1 = new CommentDTO("Boa viagem", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Aproveite", sdf.parse("21/03/2018"), new AuthorDTO(bob));
		CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("21/03/2018"), new AuthorDTO(alex));

		post1.getComment().addAll(Arrays.asList(comment1, comment2));
		post2.getComment().addAll(Arrays.asList(comment3));

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
