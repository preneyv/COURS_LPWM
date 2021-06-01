import {View, Text, StyleSheet, FlatList, TouchableOpacity} from "react-native";
import React, { useEffect, useState } from 'react';
import MultiSlider from '@ptomasroos/react-native-multi-slider'
import { createMaterialTopTabNavigator  } from "@react-navigation/material-top-tabs";
import Ionicons from "react-native-vector-icons/Ionicons";
import {useDispatch, useSelector} from "react-redux"
import {editColor, editSound, editStartAt, editFinishAt} from '../../reducers/btnSlice'
import {librairieSelector} from '../../reducers/librairieSlice'

import communs from "../../style/communs";

import Micro from "./Micro";
import Locallib from "./Locallib";
import Freesound from "./Freesound";
import color from "../reusable/color"
import getInfosSong from "../reusable/songInfo"


const Tabs = createMaterialTopTabNavigator ();



const Sample = ({navigation, route}) => {
    const sound = route.params.sound
    const [idColor, setIdColor] = useState(sound.idColor)
    const [idSon, setIdSon] = useState(sound.idSound)
    const [dureeSon, setDureeSon] = useState(0)
    const [endAt, setEndAt] = useState(sound.finishAt)
    const [startAt, setStartAt] = useState(sound.startAt)

    const {R, G, B} = color.find((item) => item.id === idColor)
    const song = useSelector(librairieSelector).find((item) => item.id === idSon)
    
    const dispatch = useDispatch()

    useEffect(()=> {
        dispatch(editStartAt({idBtn:sound.idBtn, newStartAt:startAt}))
    }, [startAt])

    useEffect(()=> {
        dispatch(editFinishAt({idBtn:sound.idBtn, newFinishAt:endAt}))
    }, [endAt])

    useEffect(() => {
        dispatch(editColor({idBtn:sound.idBtn, idClr:idColor}))
    }, [idColor])

    useEffect(() => {
        getInfosSong(song).then(res => {
            setDureeSon(res/1000)
        })
        dispatch(editSound({idBtn:sound.idBtn, idNewSound:idSon}))
    }, [idSon])

    const handleValuesChange = (v) => {
        if(v[0] !== startAt) setStartAt(v[0])
        if(v[1] !== endAt) setEndAt(v[1])
    }

    return(
        <View style={communs.container}>
            <View style={styles.header}>
                <View>
                   <TouchableOpacity onPress={() => navigation.navigate("SoundBoard")}>
                        <Ionicons name="arrow-back-outline" size={23} color="white" />
                   </TouchableOpacity>
                    <View style={{flexDirection:"row", alignItems:"center", justifyContent:"space-between"}}>
                        <View>
                            <Text style={[styles.title, {color: `rgba(${R},${G},${B},0.7)`, textShadowColor: `rgba(${R},${G},${B},0.7)`}]}>N°{parseInt(sound?.idBtn)+1}</Text>
                            <Text style={{color:"white", fontSize:15}}>{song.name}</Text>
                        </View>
                        <View>
                            <Text style={{color:"white", fontSize:12}}>Durée totale : {dureeSon} s</Text>
                            <Text style={{color:"white", fontSize:12}}>Début à : {startAt * dureeSon /100} s</Text>
                            <Text style={{color:"white", fontSize:12}}>Fin à : {endAt * dureeSon /100} s</Text>
                        </View>
                    </View>
                </View>
                <View style={communs.play}>
                    <MultiSlider step={1}  min={0} max={100} enableLabel={true} values={[startAt , endAt]}  onValuesChange={(v) => handleValuesChange(v)}/>
                </View>
            </View>
            <View style={styles.colorBtn}>
                <FlatList 
                    renderItem={({item}) => {
                        const opacity = item.id === idColor ? "1" : "0.2"
                        return (
                            <TouchableOpacity onPress={()=> setIdColor(item.id)}>
                                <View  elevation={5} style={[styles.btnBoard, { borderRadius:50, backgroundColor: `rgba(${item.R},${item.G},${item.B},${opacity})`, borderColor: `rgba(${item.R},${item.G},${item.B},${opacity})`, shadowColor: `rgba(${item.R},${item.G},${item.B},${opacity})` }]} /> 
                            </TouchableOpacity>
                        )
                    }}
                    keyExtractor={(item) => item.id}
                    data={color}
                    contentContainerStyle={{alignItems:"center"}}
                    numColumns="6"
                />
            </View>
            <View style={styles.tabsEdit}>
                <Tabs.Navigator
                    screenOptions={({ route }) => ({
                        tabBarIcon: ({ focused, color, size }) => {
                            let iconName;
                            switch (route.name) {
                                case "Micro":
                                    iconName = focused ? "mic" : "mic-outline";
                                    break;
                                case "Locallib":
                                    iconName = focused ? "library" : "library-outline" ;
                                    break;
                                case "Freesound":
                                    iconName =  focused ? "earth" : "earth-outline";
                                    break;
                                default:
                                    iconName = "code-slash";
                                    break;
                            }
                            return <Ionicons name={iconName} size={23} color={`rgb(${R},${G},${B})`} style={{textShadowColor:`rgba(${R},${G},${B},0.7)`, textShadowRadius: 16.00, elevation:20}} />;
                        },

                    })}
                    tabBarOptions={{ tabBarPosition: "bottom",showIcon: true, showLabel: false, activeTintColor: `rgb(${R},${G},${B})`, inactiveTintColor: `rgb(${R},${G},${B})`, tabStyle:{backgroundColor:"black", fontSize: 15 }}}>
                    <Tabs.Screen name="Locallib">
                        {props => <Locallib {...props} selectedSong={idSon} selectedColor={{R,G,B}} handleChangeSon={(idNewSound)=>setIdSon(idNewSound)}/>}
                    </Tabs.Screen>
                    <Tabs.Screen name="Micro" >
                        { props => <Micro {...props} selectedColor={{R,G,B}} /> }
                    </Tabs.Screen>
                    <Tabs.Screen name="Freesound">
                        {props => <Freesound {...props}  selectedColor={{R,G,B}} />}
                    </Tabs.Screen>
                </Tabs.Navigator>
            </View>
        </View>
    )

}

const styles = StyleSheet.create({
  title: {
      fontSize: 60,
      textShadowOffset: {
          width: 0,
          height: 0,
      },
      textShadowRadius: 11.30,
  },
    header: {
      
    },
    tabsEdit: {
      flex:1,
        width:"100%",
        backgroundColor: 'black',
        paddingBottom: 10,
    },
    btnBoard: {
        width: 30,
        height: 30,
        margin: 10,
        borderWidth:1,
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.8,
        shadowRadius: 12,
    },
    colorBtn: {
    
        flexDirection:"row",
        paddingTop: 10,
        paddingBottom: 10,
        alignItems:"center",
        justifyContent:"space-between",
    
    }

})
export default Sample