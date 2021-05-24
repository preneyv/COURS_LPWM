import {View, Text, StyleSheet, FlatList, TouchableOpacity} from "react-native";
import React, { useEffect, useState } from 'react';
import { createMaterialTopTabNavigator  } from "@react-navigation/material-top-tabs";
import Ionicons from "react-native-vector-icons/Ionicons";
import {useDispatch, useSelector} from "react-redux"
import {editColor, editSound} from '../../reducers/btnSlice'
import {librairieSelector} from '../../reducers/librairieSlice'

import communs from "../../style/communs";

import Micro from "./Micro";
import Locallib from "./Locallib";
import Freesound from "./Freesound";
import color from "../reusable/color"


const Tabs = createMaterialTopTabNavigator ();

const Sample = ({navigation, route}) => {
    const sound = route.params.sound
    const [idColor, setIdColor] = useState(sound.idColor)
    const [idSon, setIdSon] = useState(sound.idSound)

    const {R, G, B} = color.find((item) => item.id === idColor)
    const {name} = useSelector(librairieSelector).find((item) => item.id === idSon)


    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(editColor({idBtn:sound.idBtn, idClr:idColor}))
    }, [idColor])

    useEffect(() => {
        dispatch(editSound({idBtn:sound.idBtn, idNewSound:idSon}))
    }, [idSon])

    return(
        <View style={communs.container}>
            <View style={styles.header}>
                <View>
                   <TouchableOpacity onPress={() => navigation.navigate("SoundBoard")}>
                        <Ionicons name="arrow-back-outline" size={23} color={`rgb(${R},${G},${B})`} style={{textShadowColor:`rgba(${R},${G},${B},0.7)`, textShadowRadius: 16.00, elevation:20}} />
                   </TouchableOpacity>
                    <View>
                        <Text style={[styles.title, {color: `rgba(${R},${G},${B},0.7)`, textShadowColor: `rgba(${R},${G},${B},0.7)`}]}>N°{parseInt(sound?.idBtn)+1}</Text>
                        <Text style={{color:"white", fontSize:15}}>{name}</Text>
                    </View>
                </View>
                <View style={communs.play}>
                    <Ionicons name="triangle-outline" style={communs.triangle}/>
                    <View style={communs.linePlayer}>
                        <View style={communs.square}></View>
                        <View style={communs.line}></View>
                    </View>
                </View>
            </View>
            <View style={styles.colorBtn}>
                <FlatList 
                    renderItem={({item}) => {
                        const opacity = item.id === idColor ? "1" : "0.5"
                        return (
                            <TouchableOpacity onPress={()=> setIdColor(item.id)}>
                                <View  elevation={5} style={[styles.btnBoard, { backgroundColor: `rgba(${item.R},${item.G},${item.B},${opacity})`, borderColor: `rgba(${item.R},${item.G},${item.B},${opacity})`, shadowColor: `rgba(${item.R},${item.G},${item.B},${opacity})` }]} /> 
                            </TouchableOpacity>
                        )
                    }}
                    keyExtractor={(item) => item.id}
                    data={color}
                    style={{width:"100%"}}
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
        justifyContent:"center",
    
    }

})
export default Sample