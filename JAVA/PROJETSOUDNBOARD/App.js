import React from 'react';
import { StyleSheet} from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import {Provider} from "react-redux"
import persistStore from "redux-persist/es/persistStore";
import { PersistGate } from "redux-persist/integration/react";

import Sample from "./components/Edit/Sample";
import SoundBoard from "./components/Soundboard";

import store from './store/store'

const Stack = createStackNavigator()

let persistor = persistStore(store)
persistor.purge()


export default function App() {

  return (
    <Provider store={store}>
      <PersistGate persistor={persistor}>
        <NavigationContainer >
          <Stack.Navigator initialRouteName="SoundBoard"
                          screenOptions={{
                              headerShown: false
                          }}
          >
            <Stack.Screen name="SoundBoard">
              {props => <SoundBoard {...props}   /> }
            </Stack.Screen>
            <Stack.Screen name="Sample" >
              {props => <Sample {...props} /> }
            </Stack.Screen>
          </Stack.Navigator>
        </NavigationContainer>
      </PersistGate>
    </Provider>
  )
}