package gr.athtech.ToyStory.controller;

import gr.athtech.ToyStory.ToyStoryApplication;
import gr.athtech.ToyStory.model.Item;
import gr.athtech.ToyStory.service.InitiationService;
import gr.athtech.ToyStory.service.ItemService;
import gr.athtech.ToyStory.service.SessionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    SessionService sessionService;
    InitiationService initiationService;
    ItemService itemService;


    public MainController(SessionService sessionService, InitiationService initiationService, ItemService itemService) {
        this.sessionService = sessionService;
        this.initiationService = initiationService;
        this.itemService = itemService;
    }

    @GetMapping("/login")
    public String login() {
        boolean loggedIn;

        if(ToyStoryApplication.isFirstTime()) {
            initiationService.initiateDatabase();
            ToyStoryApplication.setFirstTime(false);
        }

        loggedIn = sessionService.isUserLoggedIn(SecurityContextHolder.getContext().getAuthentication());
        if (loggedIn) {
            /* The user is logged in */
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/")
    public String main(Model model) {
        List<Item> allItems = itemService.findAllItems();
        model.addAttribute("items", allItems);
        return "main";
    }

    @PostMapping("/item/add")
    public String addItem(@RequestParam String description) {
        itemService.addItem(description);
        return "redirect:/";
    }

    @PostMapping("/item/delete")
    public String deleteItem(@RequestParam long id) {
        itemService.deleteItem(id);
        return "redirect:/";
    }

    @GetMapping("/item/find")
    public String test() {
        return "test";
    }
}
