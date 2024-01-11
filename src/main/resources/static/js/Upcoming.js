// Function to fetch carousel data from API
async function fetchUpcomingMovies() {
    const apiKey = '913cb5e6f6cf3b3fba98e32fa5566fb8';
    const tmdbUpcomingUrl = `https://api.themoviedb.org/3/movie/now_playing?api_key=913cb5e6f6cf3b3fba98e32fa5566fb8&region=IN`;
    try {
        // Fetch data from TMDB API
        const response = await fetch(tmdbUpcomingUrl);
        const data = await response.json();

        // Process the data and update the UI
        displayUpcomingMovies(data.results.slice(0, 6));  // Assuming results is an array containing movie information
    } catch (error) {
        console.error('Error fetching upcoming movies:', error);
    }
}
// Function to display upcoming movies on the UI
// Function to display upcoming movies on the UI
function displayUpcomingMovies(movies) {
    const container = document.getElementById('upcomingMoviesContainer');

    if (!container) {
        console.error('Container not found.');
        return;
    }

    movies.forEach(movie => {
        const col = document.createElement('div');
        col.className = 'col'; // Each movie will be in a separate column
        movie_id = movie.id;
        const movieElement = document.createElement('div');

        movieElement.innerHTML = `
<style>


        .card img {
            max-height: 24rem;
            object-fit: contain;
        }
</style>
<div class="card h-100 w-75">

                    <img src="https://image.tmdb.org/t/p/w500/${movie.poster_path}" alt="${movie.title} Poster" class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title"><a href="#" th:text="${movie.title}">${movie.title}</a></h5>
                        <h6 class="card-text text-title">${movie.release_date}</h6>
                        <h6 class="card-text text-muted">${movie.popularity}</h6>
                        <div class="mt-3">
                           <div class="col-md-3 p-0 col-3">
\t\t\t   <div class="upcome_2i_lastir pt-3">
    \t\t\t     <span><a class="col_red rounded" href="/Client/View_DAvadadw/${movie.id}"><i class="fa fa-shopping-cart"></i></a></span>
\t\t\t   </div>
\t\t\t  </div>
                        </div>
                    </div>
                    </div>
                `;

        col.appendChild(movieElement);
        container.appendChild(col);
    });
}

// Trigger the fetch when the page is loaded
document.addEventListener('DOMContentLoaded', fetchUpcomingMovies);
// Function to update the carousel with dynamic data
