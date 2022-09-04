package com.mycompany.dvdstore.api;

import com.mycompany.dvdstore.core.entity.Movie;
import com.mycompany.dvdstore.core.service.MovieServiceInterface;
import com.mycompany.dvdstore.form.MovieForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieResource {

    public MovieServiceInterface getMovieService() {
        return movieService;
    }

    @Autowired
    public void setMovieService(MovieServiceInterface movieService) {
        this.movieService = movieService;
    }

    private MovieServiceInterface movieService;

    @GetMapping("/{id}")
    public Movie get(@PathVariable("id") String id) {
        return movieService.getMovieById(Long.parseLong(id));
    }

    @PostMapping("")
    //@PostMapping
    public Movie add(@RequestBody Movie newMovie) {
        Movie movie = new Movie();
        movie.setId(newMovie.getId());
        movie.setTitle(newMovie.getTitle());
        movie.setGenre(newMovie.getGenre());
        movie.setDescription(newMovie.getDescription());
        System.out.println("Movie is added " + movie.getTitle());
        movieService.registerMovie(movie);
        return movie;
    }

    @GetMapping("")
    public Iterable<Movie> list() {
        return movieService.getMovieList();
    }
}
