//React and ReactNative Import
import {View, Image, StyleSheet, FlatList} from "react-native";
import React from 'react';
import Ionicons from "react-native-vector-icons/Ionicons";
import { useSelector } from "react-redux"

//Styles import
import communs from "../style/communs";

//Slices import
import { sdbSelector } from "../reducers/btnSlice";
import { librairieSelector } from '../reducers/librairieSlice'

//Services import
import PlaySong from "../services/playSong";
import color from './reusable/color'


const BtnSdb = ({navigation, btn}) => {

    let clr = color.find((item) => item.id === btn.idColor)
    const lib = useSelector(librairieSelector)
    const sound = lib.find((item)=> item.id === btn.idSound)

    const goToSample = () => {
        navigation.navigate('Sample', {sound:btn})
    }
    return (

        <PlaySong song={sound} goToSample={goToSample} limitPlay={{start : btn.startAt, finish : btn.finishAt}}>
            <View  elevation={5} style={[styles.btnBoard, { borderColor: `rgba(${clr.R},${clr.G},${clr.B},0.7)`, shadowColor: `rgba(${clr.R},${clr.G},${clr.B},0.7)` }]} />
        </PlaySong>

    )
}

const SoundBoard = ({navigation}) => {
    const btnSdb = useSelector(sdbSelector)

    return(
        <View style={communs.container}>
            <View>
                <View style={styles.djPlatine}>
                    <Image source={require("../assets/dj.png")} style={styles.imgDjPlatine} />
                    <Image source={require("../assets/dj.png")} style={styles.imgDjPlatine} />
                </View>
                <View style={communs.play}>
                    <Ionicons name="triangle-outline" style={communs.triangle}/>
                    <View style={communs.linePlayer}>
                        <View style={communs.square}></View>
                        <View style={communs.line}></View>
                    </View>
                </View>
            </View>
            <View style={styles.sonsBtn}>
                <FlatList
                    
                    renderItem={({item}) => <BtnSdb btn={item} navigation={navigation}/>}
                    keyExtractor={(item) => item.idBtn}
                    data={btnSdb}
                    contentContainerStyle={{alignItems:"center"}}
                    numColumns="3"
                />
            </View>
        </View>
    )

}

const styles = StyleSheet.create({
    djPlatine: {
        flexDirection:"row",
        alignItems: "center",
        justifyContent: "space-evenly",
        marginTop: 40,
        
    },
    imgDjPlatine: {
        width: 120,
        height: 120,

    },
    sonsBtn: {

        flexDirection:"row",
    },
    btnBoard: {
        borderRadius:5,
        width: 80,
        height: 80,
        margin: 10,
        backgroundColor: "#252323",
        borderWidth:1,
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.8,
        shadowRadius: 12,

    },

})

export default SoundBoard