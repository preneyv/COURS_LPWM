import { StatusBar } from 'expo-status-bar';
import React, {useState} from 'react';
import { StyleSheet, Button, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Ionicons from "react-native-vector-icons/Ionicons";
import localib from "./Components/Library/localLib.json"
const Stack = createStackNavigator();

import Main from "./components/Main"
import ViewMusic from './components/ViewMusic'

export default function App() {

    const [listMusic, setListMusic] = useState(localib)
    const [currentMusic, setCurrentMusic] = useState()


    const handleNewMusic = (newMusic) => {
        setCurrentMusic(newMusic)
        setListMusic(prev => [...prev, newMusic])
    }

    const changeCurrentMusic = (music) => {
        console.log(music)
        setCurrentFilm(music)
    }

    return (
        <NavigationContainer>
            <Stack.Navigator initialRouteName="Main">
                <Stack.Screen name="Main">
                    {props => <Main {...props} musicList={listMusic} style={styles.container} /> }
                </Stack.Screen>
                <Stack.Screen name="ViewMusic" >
                    {props => <ViewMusic {...props} addMusic={handleNewMusic} style={styles.container} /> }
                </Stack.Screen>
            </Stack.Navigator>
        </NavigationContainer>
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
});
