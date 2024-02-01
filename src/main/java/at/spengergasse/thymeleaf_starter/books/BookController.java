package at.spengergasse.thymeleaf_starter.books;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public String allBooks(Model model){
        model.addAttribute("books", repository.findAll());
        return "/books/books";
    }

    @GetMapping("/bookForm")
    public String bookForm(Model model){
        model.addAttribute("book", new Book());
        return "/books/edit_book";
    }

    @PostMapping("/saveBook")
    public String saveBook(Book book){
        repository.save(book);
        return "redirect:/books";
    }


    @GetMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable int id){
        repository.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/book/edit/{id}")
    public String editBook(@PathVariable int id, Model model){
        model.addAttribute("book", repository.findById(id).get());
        return "/books/edit_book";
    }

}
