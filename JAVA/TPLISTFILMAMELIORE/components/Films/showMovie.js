import {Button, Image, Text, View, Linking} from "react-native";
import React from 'react';
import {form, styles, showMovie} from "../../style";

 const ShowFilm = ({movie}) => {
    console.log(movie)
    const {titre, id, com, note, plot, date, img} = movie ?? ""
    return (

        <View style={showMovie.mainCtn}>
            {
                movie !== undefined &&
                <View style={showMovie.unitFilm}>
                    <Text style={showMovie.title}>{titre}</Text>
                    <View style={showMovie.noteAndDate}><Text style={showMovie.date}>{date}</Text><Text style={showMovie.date}>{note}/10</Text></View>
                    <Image style={showMovie.img} source={img}/>
                    <View>
                        <Text style={showMovie.title}>Résumé :</Text>
                        <Text>{plot}</Text>
                        <Text style={showMovie.title}>Commentaire :</Text>
                        <Text>{com}</Text>
                        <Text style={showMovie.title}>Lien IMDB :</Text>
                        <Text  onPress={() => Linking.openURL(`https://www.imdb.com/video/${id}`)}>Clique-moi !</Text>
                    </View>
                </View>
            }

            {
                movie === undefined && <Text> Choisissez un film dans votre liste</Text>
            }

        </View>
    )
}

export  default ShowFilm