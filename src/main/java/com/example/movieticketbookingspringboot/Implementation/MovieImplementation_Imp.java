package com.example.movieticketbookingspringboot.Implementation;

import com.example.movieticketbookingspringboot.Model.MovieData_aADSsa;
import com.example.movieticketbookingspringboot.Repository.MovieRepository_Jpa;
import com.example.movieticketbookingspringboot.Service.MovieService_Serv;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Component
public class MovieImplementation_Imp implements MovieService_Serv {
    private final MovieRepository_Jpa movieRepositoryJpa;
    private final String apiKey = "913cb5e6f6cf3b3fba98e32fa5566fb8";
    private final String tmdbApiUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=";

    @Autowired
    public MovieImplementation_Imp(MovieRepository_Jpa movieRepositoryJpa) {
        this.movieRepositoryJpa = movieRepositoryJpa;
    }

    @Override
    public List<MovieData_aADSsa> findRecommendedMovies() {
        // Fetch data from TMDb API
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = tmdbApiUrl + apiKey;
        ResponseEntity<MovieData_aADSsa[]> response = restTemplate.getForEntity(apiUrl, MovieData_aADSsa[].class);
        MovieData_aADSsa[] movies = response.getBody();

        // Save fetched data to the database
        if (movies != null) {
            movieRepositoryJpa.saveAll(Arrays.asList(movies));
        }

        return movieRepositoryJpa.findAll();
    }

    @Override
    public MovieData_aADSsa getByID(Long id) {
        // Fetch data from TMDb API using OkHttpClient
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(tmdbApiUrl + id + "?api_key=" + apiKey)
                .get()
                .addHeader("accept", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {
                MovieData_aADSsa movieData = new Gson().fromJson(response.body().string(), MovieData_aADSsa.class);
                // Close the response body manually
                response.body().close();
                return movieData;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
