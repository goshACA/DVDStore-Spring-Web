package com.mycompany.dvdstore.web.controller;

import com.mycompany.dvdstore.core.entity.Movie;
import com.mycompany.dvdstore.core.service.MovieServiceInterface;
import com.mycompany.dvdstore.form.MovieForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/movie")
public class MovieController {

    public MovieServiceInterface getMovieService() {
        return movieService;
    }

    @Autowired
    public void setMovieService(MovieServiceInterface movieService) {
        this.movieService = movieService;
    }

    private MovieServiceInterface movieService;
/*
    @GetMapping("/{id}")
    public String displayMovieCard(@PathVariable("id") String id, Model model){
        model.addAttribute(movieService.getMovieById(Long.parseLong(id)));
        return "movie-details";
    }*/

    @PostMapping("/add")
    //@PostMapping
    public String addMovie(@Valid @ModelAttribute MovieForm movieForm, BindingResult result){
        if(result.hasErrors())
            return "add-movie-form";
        Movie movie = new Movie();
        movie.setId(movieForm.getId());
        movie.setTitle(movieForm.getTitle());
        movie.setGenre(movieForm.getGenre());
        movie.setDescription(movieForm.getDescription());
        System.out.println("Movie is added " + movie.getTitle());
        movieService.registerMovie(movie);
        return "movie-added";
    }
}
