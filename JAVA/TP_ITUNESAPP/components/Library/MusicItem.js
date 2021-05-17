import {View,Text, StyleSheet, Image} from "react-native-web";
import React from "react";


const MusicItem = ({music}) => {
    console.log(music)
    return (
        <View style={styles.ctn}>
            {music.img !== "" && <Image style={styles.img} source={{uri : music.img}} />}
            <View style={styles.info}>
                <View style={styles.topInfo}>
                    <Text style={styles.titre}>{music.titre}</Text>
                    <Text style={styles.annee}>{music.annee}</Text>
                </View>
                <View style={styles.topInfo}>
                    <Text style={styles.album}>{music.album}</Text>
                    <Text style={styles.album}>{music.genre}</Text>
                </View>
                <Text style={styles.groupe}>{music.groupe}</Text>
            </View>
        </View>
    )
}

const styles = StyleSheet.create({
    ctn: {
        display: "flex",
        flexDirection: "row",
        backgroundColor: "black",
        color: "white",
        padding: "10px",
        borderRadius:"5px",
        marginTop: 5,
        marginBottom: 5,
        width: "100%",
    },
    info: {
        display: "flex",
        flex: 1,
        marginLeft: 5
    },
    topInfo: {
        flexDirection: "row",
      display: "flex",
      justifyContent: "space-between"
    },
    titre: {
        fontSize: 15,
        fontWeight: 600,
        color: "#0EB9F9",
    },
    album: {
        fontSize: "12px",
        color: "white",
        display: "flex",
        flexWrap: "wrap"
    },
    groupe: {
        fontSize: 12,
        color: "white",
        marginTop: "5px"
    },
    annee: {
        ontSize: 12,
        color: "white",
        display: "flex",
    },
    img: {
        height: "100%",
        width: 50,
    }
})

export default MusicItem