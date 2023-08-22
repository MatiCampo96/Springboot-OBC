package com.example.obrestdatajpa;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.obrestdatajpa.config.OpenApi;
import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository =	context.getBean(BookRepository.class);

		//CRUD 
		//Crear un libro
		Book book1 = new Book(null, "Harry Poter", "JK Rowllin", 1000, 29.30, LocalDate.of(1996, 12, 1), true);
		Book book2 = new Book(null, "Anillos Poter", "JK Rowlladsin", 1000, 59.30, LocalDate.of(1999, 11, 12), true);



		//Almacenar un libro
		System.out.println("Num de libros en base de datos: " + repository.findAll().size());
		repository.save(book1);
		repository.save(book2);

		//Recuperar datos de los libros
		System.out.println("Num de libros en base de datos: " + repository.findAll().size());

		//Borrar libro
		//repository.deleteById(1L);
		System.out.println("Num de libros en base de datos: " + repository.findAll().size());


	}

}
