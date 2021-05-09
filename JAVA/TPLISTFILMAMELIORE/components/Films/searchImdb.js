import React, {useState, useEffect} from "react";
import {View, TextInput, StyleSheet, ScrollView} from "react-native";
import ShowFilm from './showMovie'
import {api} from '../Reusable/api'



const searchImdb = async (query) => {
    let formattedQuery = query.split(" ").join("+");
    let response = await api.getOneFilm(formattedQuery)
    return response
        
};

const ImdbSearch = () => {
    const [input, setInput] = useState("");
    const [filmResults, setFilmResults] = useState([]);

    const handleSubmit = () => {
        searchImdb(input).then((result) => {
            setFilmResults(result);
        });
    };

    useEffect(() => {
        const timeout = setTimeout(handleSubmit, 1000);
        return () => {
            clearTimeout(timeout);
        };
    }, [input]);

    return (
        <ScrollView style={styles.ctn}>
            <TextInput
                style={styles.input}
                value={input}
                onChangeText={setInput}
                placeholder="Chercher un film dans IMDB"
            />
            <ShowFilm movie={filmResults} />
        </ScrollView>
    );
};

const styles = StyleSheet.create({
    ctn: {
        backgroundColor: "#2C2C2C",
        display: "flex",
        flex: 1,
    },
    input: {
        backgroundColor: "white",
        margin: 10,
        height: 40
    }
})

export default ImdbSearch;
