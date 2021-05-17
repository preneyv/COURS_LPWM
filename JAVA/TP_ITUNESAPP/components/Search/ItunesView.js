import React, {useState, useEffect} from "react";
import {View, TextInput, StyleSheet} from "react-native";
import CommonLib from "../Library/CommonLib";


const formatResponse = (item) => {
    return {
        titre: item.trackName,
        groupe: item.artistName,
        img: item.artworkUrl100,
        genre: item.primaryGenreName,
        annee: item.releaseDate.split('-')[0],
        album: item.collectionName,
        id: item.trackId.toString(),
    };
};

const searchItunes = async (query) => {
    let formattedQuery = query.split(" ").join("+");
    let response = await fetch(`https://itunes.apple.com/search?term=${formattedQuery}`);
    const json = await response.json();
    return json.results
        .filter((item) => item.kind === "song")
        .map(formatResponse);
};

const ItunesView = ({navigation}) => {
    const [input, setInput] = useState("");
    const [listResults, setListResults] = useState([]);

    const handleSubmit = () => {
        searchItunes(input).then((result) => {
            setListResults(result);
        });
    };

    useEffect(() => {
        const timeout = setTimeout(handleSubmit, 1000);
        return () => {
            clearTimeout(timeout);
        };
    }, [input]);

    return (
        <View style={styles.ctn}>
            <TextInput
                style={styles.input}
                value={input}
                onChangeText={setInput}
                placeholder="Search iTunes"
            />
            <CommonLib list={listResults} navigation={navigation} />
        </View>
    );
};

const styles = StyleSheet.create({
    ctn: {
        backgroundColor: "#2C2C2C",
        display: "flex",
        flex: 1,
        height: "100%"
    },
    input: {
        backgroundColor: "white",
        margin: 10,
        height: 40
    }
})

export default ItunesView;
