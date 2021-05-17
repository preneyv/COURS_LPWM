import React, {useState, useEffect} from "react";

import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { NavigationContainer } from '@react-navigation/native';

import * as Contacts from 'expo-contacts';

import ListContacts from "./components/Contacts";
import ListPhotos from "./components/Photos";
import MapPanel from "./components/MapView";

import requirement from "./components/Requirements";

import Ionicons from "react-native-vector-icons/Ionicons";

const Tabs = createBottomTabNavigator();


const App = () => {

  const [contacts, setContacts] = useState([])
  const [photos, setPhotos] = useState([])

    useEffect(() => {
        (async () => {
            const data = await requirement.contactRqr()
            setContacts(data)
            const photos = await requirement.pictureRqr()
            setPhotos(photos)
        })();
    }, []);

  const sendContactAndImage = () => {
      return {img:photos[0], contact:contacts[1]}
  }

  return (
      <NavigationContainer>
        <Tabs.Navigator
            screenOptions={({ route }) => ({
              tabBarIcon: ({ focused, color, size }) => {
                let iconName;
                switch (route.name) {
                  case "Contacts":
                    iconName = focused ? "layers" : "layers-outline";
                    break;
                  case "Photos":
                    iconName = focused ? "image" : "image-outline";
                    break;
                  case "Map":
                      iconName = focused ? "map" : "map-outline";
                      break;
                  default:
                    iconName = "code-slash";
                    break;
                }
                return <Ionicons name={iconName} size={size} color={color} />;
              },

            })}
            tabBarOptions={{ activeTintColor: "#0EB9F9", inactiveTintColor: "white", tabStyle:{backgroundColor:"black" }}}>
          <Tabs.Screen name="Contacts">
            { props => <ListContacts {...props} listContact={contacts} /> }
          </Tabs.Screen>
          <Tabs.Screen name="Photos">
            {props => <ListPhotos {...props} listPhotos={photos} />}
          </Tabs.Screen>
            <Tabs.Screen name="Map">
                {props => <MapPanel {...props}  getContactAndImage={sendContactAndImage}/>}
            </Tabs.Screen>
        </Tabs.Navigator>
      </NavigationContainer>
  );
};

export default App;

