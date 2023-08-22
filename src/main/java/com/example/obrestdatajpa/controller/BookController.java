  package com.example.obrestdatajpa.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private BookRepository bookRepository;

    
    //CRUD sobre la entidad book

    //Buscar todos los libros(lista de libros)
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/api/books")
    public List<Book> findAll(){
        return bookRepository.findAll();

    }

    //buscar 1 libro segun id
    @Operation(description = "Obtiene un libro por su clave primaria, id Long")
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@Parameter(description = "Clave primaria tipo Long")@PathVariable Long id){
        Optional<Book> bookOpt = bookRepository.findById(id); 
        if(bookOpt.isPresent())
            return ResponseEntity.ok(bookOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    //Crear libro... Obtener cabeceras?? para que?? por seguridad
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        // Guardar el libro recibido por parametros en la base de datos
        if(book.getId() != null){ //quiere decir que existe el id y por lo tanto no es una creacion
            log.warn("Tryng to create a book with id");
            System.out.println("Tryng to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);

    }

    /**
     * actualizar el libro existente en base de datos
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null) {
            log.warn("Tryng to update a non existent book");
            return ResponseEntity.badRequest().build();

        }
        if(!bookRepository.existsById(book.getId())){
            log.warn("Tryng to update a non existent book");
            return ResponseEntity.notFound().build();
        }

        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result); // El libro devuelto tiene una clave primaria
    }

    @Hidden
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){

        if(!bookRepository.existsById(id)){
            log.warn("Tryng to delete a non existent book");
            return ResponseEntity.noContent().build();
        }

        bookRepository.deleteById(id);

        return ResponseEntity.noContent().build(); 

    }
    @Hidden
    @DeleteMapping("/api/books/")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST request for deleting all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build(); 
    }


    }
    

    


