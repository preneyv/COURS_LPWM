

const api = {
    getFilms : async function() {
        let films = ["Harry Potter", "Batman", "Star Wars", "x-men", "Superman", "Big Fish", "Man of steel", "Revenge of the sith"]
        let listFilm = []

        for (let f of films) {
            let movie = await this.getOneFilm(f);
            listFilm = [...listFilm, movie];
        }

        return listFilm
    },
    getOneFilm : async function(f) {
        let result = await fetch(`http://www.omdbapi.com/?t=${f}&apikey=ea178c90`, {method: "GET"})
            .then(res => res.json())
        let {imdbRating,Released, Title, Poster, imdbID, Plot} = result
        let movie = {id:imdbID, titre: Title, plot: Plot, img:Poster, date: Released, note:imdbRating}
        return movie
    }
}

const testResApi = [
    {
        id: "tt1201607",
        titre: "Harry Potter and the Deathly Hallows: Part 2",
        resume: "Harry, Ron, and Hermione search for Voldemort's remaining Horcruxes in their effort to destroy the Dark Lord as the final battle rages on at Hogwarts.",
        poster:"https://m.media-amazon.com/images/M/MV5BMGVmMWNiMDktYjQ0Mi00MWIxLTk0N2UtN2ZlYTdkN2IzNDNlXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_SX300.jpg",
        date: "15 Jul 2011",
        note: "8.1",
    },
    {
        id: "tt0096895",
        titre: "Batman",
        resume: "The Dark Knight of Gotham City begins his war on crime with his first major enemy being Jack Napier, a criminal who becomes the clownishly homicidal Joker.",
        poster:"https://m.media-amazon.com/images/M/MV5BMTYwNjAyODIyMF5BMl5BanBnXkFtZTYwNDMwMDk2._V1_SX300.jpg",
        date: "23 Jun 1989",
        note: "7.5",
    },
    {
        id: "tt0076759",
        titre: "Star Wars: Episode IV - A New Hope",
        resume: "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
        poster:"https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
        date: "25 May 1977",
        note: "8.6",
    },
    {
        id: "tt0078346",
        titre: "Superman",
        resume: "An alien orphan is sent from his dying planet to Earth, where he grows up to become his adoptive home's first and greatest superhero.",
        poster:"https://m.media-amazon.com/images/M/MV5BMzA0YWMwMTUtMTVhNC00NjRkLWE2ZTgtOWEzNjJhYzNiMTlkXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg",
        date: "15 Dec 1978",
        note: "7.3",
    }
]

export { api, testResApi }