import {StyleSheet, Button, Image, Text, View, Linking} from "react-native";
import React from 'react';

const ShowMusic = ({route, navigation, addMusic}) => {
    const {music} = route.params
    return (

        <View style={styles.main}>
            <View style={{flex:4, alignItems:"center", justifyContent:"center"}}>
                <Image style={styles.img} source={{uri : music.img}} />
                <Text style={{fontSize:15, textTransform:"uppercase", fontWeight:"600", color:"#0EB9F9"}}>{music.titre}</Text>
                <Text style={{fontSize:15, textTransform:"uppercase", fontWeight:"600", color:"#0EB9F9"}}>{music.groupe}</Text>
                <Text style={{fontSize:15, textTransform:"uppercase", fontWeight:"600", color:"#0EB9F9"}}>{music.annee}</Text>
                <Text style={{fontSize:15, textTransform:"uppercase", fontWeight:"600", color:"#0EB9F9"}}>{music.genre}</Text>
            </View>
            <Button style={{flex:1}} onPress={() => addMusic(music)} title="Ajouter à la librairie" />
        </View>
    )
}

const styles= StyleSheet.create({
    main: {
        flex:1,
        backgroundColor: "#2C2C2C",
    },
    img: {
        height: "290px",
        width: "80%",
    }
})

export  default ShowMusic