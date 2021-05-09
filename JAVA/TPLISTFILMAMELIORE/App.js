import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Button, View } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import Ionicons from "react-native-vector-icons/Ionicons";
const Stack = createStackNavigator();

import Films from "./components/Films/films";
import Connect from "./components/connect";
import Setting from "./components/settings";

export default function App() {
  return (
      <NavigationContainer>
        <Stack.Navigator initialRouteName="Log">
          <Stack.Screen name="Log" component={Connect} style={styles.container}/>
          <Stack.Screen name="Films" 
          component={Films} 
          style={styles.container}
          options={({ navigation }) => ({
            
            title: 'Mes Films',
            headerLeft: () => {
              
            },
            headerRight: () => (
              <View style={{display:"flex", flexDirection:"row"}}>
                <Ionicons name="settings-outline" color="#0EB9F9" style={{fontSize:"35px", marginRight:"5px"}} onPress={() => navigation.navigate("Settings")} />
                <Ionicons name="log-out-outline" color="#0EB9F9" style={{fontSize:"35px", marginRight:"5px"}} onPress={() => navigation.navigate("Log")} /> 
              </View>
            ),
          })}/>
          <Stack.Screen name="Settings" component={Setting} />
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
