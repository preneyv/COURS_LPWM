
import React, {useState, useEffect} from 'react';
import {  View } from 'react-native';
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import Ionicons from "react-native-vector-icons/Ionicons";

import AddFilm from "./formView";
import ShowFilm from "./showMovie";
import ListFilm from "./listFilm";
import ImdbSearch from './searchImdb'

import {api} from "../Reusable/api";

const Tab = createBottomTabNavigator()

const add = async() => {
    let film = await api.getFilms()
    return film
}

const Films = () => {

    const [listFilm, setListFilm] = useState([])
    const [currentFilm, setCurrentFilm] = useState()


    useEffect(() => {
        add().then((res) => setListFilm(res) )
    }, [])

    const handleFilteringList = (list) => {
        console.log(list)
        setListFilm(list)
    }
    const handleNewFilm = (newFilm) => {
        setCurrentFilm(newFilm)
        setListFilm(prev => [...prev, newFilm])
    }

    const changeCurrentFilm = (movie) => {
        console.log(movie)
        setCurrentFilm(movie)
    }

    return (
        <Tab.Navigator
            screenOptions={({ route }) => ({
                tabBarIcon: ({ focused, color, size }) => {
                    let iconName;
                    switch (route.name) {
                        case "ListFilm":
                            iconName = focused ? "list" : "list-outline";
                            break;
                        case "ShowFilm":
                            iconName = focused ? "film" : "film-outline";
                            break;
                        case "AddFilm":
                            iconName = focused ? "add-circle" : "add-circle-outline";
                            break;
                        case "AddFilm":
                            iconName = focused ? "add-circle" : "add-circle-outline";
                            break;
                        default:
                            iconName = "ban";
                            break;
                    }
                    return <Ionicons name={iconName} size={size} color={color} />;
                },

            })}
            tabBarOptions={{ activeTintColor: "#0EB9F9", inactiveTintColor: "grey"}}
        >
            <Tab.Screen name="ListFilm" >
                {props => <ListFilm {...props} list={listFilm} handleChangeMovie={changeCurrentFilm} handleFilterList={handleFilteringList}/>}
            </Tab.Screen>
            <Tab.Screen name="ShowFilm" >
                {props => <ShowFilm {...props} movie={currentFilm} style={{flex:1}}/>}
            </Tab.Screen>
            <Tab.Screen name="AddFilm">
                {props => <AddFilm {...props} handleActionAddFilm={handleNewFilm} />}
            </Tab.Screen>
            <Tab.Screen name="SearchImdb">
                {props => <ImdbSearch {...props} />}
            </Tab.Screen>
        </Tab.Navigator>
    )
}

export default Films