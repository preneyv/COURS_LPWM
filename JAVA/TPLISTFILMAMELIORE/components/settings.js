import React, {useState, useEffect} from 'react';
import { StyleSheet, Switch, View, Text } from 'react-native';
import {styles} from "../style";

const Setting = ({switchDarkMode}) => {

    const [isEnabled, setIsEnabled] = useState(false);
    const toggleSwitch = () => setIsEnabled(previousState => !previousState);


    return (
        <View style={[styles.container, isEnabled ? styles.darkMode : styles.lightMode]}>
            <Text>Passer en Dark Mod</Text>
            <Switch
                trackColor={{ false: "#767577", true: "#81b0ff" }}
                thumbColor={isEnabled ? "#f5dd4b" : "#f4f3f4"}
                ios_backgroundColor="#3e3e3e"
                onValueChange={toggleSwitch}
                value={isEnabled}
            />
        </View>
    )
}

export default Setting