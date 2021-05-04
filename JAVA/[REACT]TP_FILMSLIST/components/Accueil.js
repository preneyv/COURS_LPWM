import {Button, ScrollView, Text, View} from "react-native";
import React from 'react';
import {styles} from "../style";

export default class Acceuil extends React.Component{
    constructor(props) {
        super(props);
        this.handleAddFilmBtn = this.handleAddFilmBtn.bind(this)
    }

    handleAddFilmBtn() {
        this.props.handleSwitchView(1)
    }


    render() {
        return (
            <View >
                <View style={styles.addBtn}><Button  onPress={this.handleAddFilmBtn} color="#0020C2" title="Ajouter"/></View>
                <ScrollView style={styles.container}>
                    {
                        this.props.listFilm.map((el, key) => {
                            return (
                                <View style={styles.unitFilm} key={key}>
                                    <Text style={styles.titreFilm}>{el.titre}</Text>
                                    <View style={styles.ctnBtm}>
                                        <Text style={styles.date}>{el.date}</Text>
                                        <Text style={styles.note}>{el.note}</Text>
                                    </View>
                                </View>
                            )
                        })
                    }
                </ScrollView>
            </View>
        );
    }
}