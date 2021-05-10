import {Picker, ScrollView, Text, TextInput, View} from "react-native";
import React, {useState, useEffect, useRef} from 'react';
import {form, styles} from "../../style";
import { RadioButton } from 'react-native-paper';

const ListItem = ({changeMovie,film}) => {

    const handleMovie = (film) => {
        changeMovie(film)
    }

    return(
        <View style={styles.unitFilm} key={film.id} onStartShouldSetResponder={() => handleMovie(film)}>
            <Text style={styles.titreFilm}>{film.titre}</Text>
            <View style={styles.ctnBtm}>
                <Text style={styles.date}>{film.date}</Text>
                <Text style={styles.note}>{film.note}</Text>
            </View>
        </View>
    )
}

export default ListItem