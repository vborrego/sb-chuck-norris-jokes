package com.mooo.bitarus.chucknorris;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    private JokeService jokeService;

    public IndexController(JokeService jokeService){
        this.jokeService = jokeService;        
    }

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("title", "Chuck Norris Jokes");
        model.addAttribute("name", name);
        model.addAttribute("joke", jokeService.getJoke().getValue());
        return "index";
    }

}
