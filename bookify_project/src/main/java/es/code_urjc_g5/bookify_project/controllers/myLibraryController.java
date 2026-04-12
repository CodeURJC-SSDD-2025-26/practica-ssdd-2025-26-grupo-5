package es.code_urjc_g5.bookify_project.controllers;

import es.code_urjc_g5.bookify_project.models.Book;
import es.code_urjc_g5.bookify_project.repositories.BookRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

import es.code_urjc_g5.bookify_project.models.Collection;
import es.code_urjc_g5.bookify_project.services.UserService;
import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestParam;

import es.code_urjc_g5.bookify_project.models.User;
import es.code_urjc_g5.bookify_project.repositories.CollectionRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class myLibraryController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CollectionRepository collectionRepository;

    @Transactional
    @GetMapping("/myLibrary")
    public String myLibrary(Model model, Authentication authentication,
            @RequestParam(required = false) Long collectionId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Collection> collections = collectionRepository.findByUser(user);
        model.addAttribute("collections", collections);
        System.out.println(">>> Colecciones del usuario: " + collections.size());

        if (collections.isEmpty()) {
            model.addAttribute("books", List.of());
            model.addAttribute("bookCount", 0);
            return "myLibrary";
        }
        Collection activeCollection = collections.stream()
                .filter(c -> c.getId().equals(collectionId))
                .findFirst()
                .orElse(collections.get(0));
        // Mensaje de depuración para verificar la colección activa y sus libros
        System.out.println(">>> Colección activa: " + activeCollection.getCollectionName());
        System.out.println(">>> Libros en la colección: " + activeCollection.getBooks().size());
        activeCollection.getBooks().forEach(b -> System.out.println("    - " + b.getTitle()));

        model.addAttribute("activeCollection", activeCollection.getId());
        model.addAttribute("Books", activeCollection.getBooks());
        model.addAttribute("bookCount", activeCollection.getBooks().size());

        return "myLibrary";
    }

    @PostMapping("/collection/new")
    public String createNewCollection(@RequestParam String collectionName, Authentication authentication,
            @RequestParam(required = false) String redirect) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        String email = authentication.getName();
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Collection newCollection = new Collection();
        newCollection.setCollectionName(collectionName);
        newCollection.setUser(user);
        collectionRepository.save(newCollection);

        return "redirect:" + (redirect != null ? redirect : "/myLibrary");
    }

}
