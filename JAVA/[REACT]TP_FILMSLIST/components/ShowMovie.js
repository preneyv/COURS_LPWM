import {Button, Image, Text, View, Linking} from "react-native";
import React from 'react';
import {form, styles, showMovie} from "../style";

export default class ShowFilm extends React.Component {

    constructor(props) {
        super(props);
        console.log(props)

    }

    handleBack = () => {
        this.props.handleSwitchView(0)
    }

    render() {
        const {titre, id, com, note, plot, date, img} = this.props.movie
        return (
            this.props.movie !== undefined &&
            <View style={showMovie.mainCtn}>

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
                <View style={form.addBtn}><Button   color="#0020C2" title="Accueil" onPress={this.handleBack}/></View>
            </View>

        )
    }

}