import React from "react";

import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { NavigationContainer } from "@react-navigation/native";
import LocalView from './Library/LocalView'
import ItunesView from "./Search/ItunesView";
import Ionicons from "react-native-vector-icons/Ionicons";

const Tabs = createBottomTabNavigator();

const App = ({musicList, navigation}) => {
    return (
            <Tabs.Navigator
                screenOptions={({ route }) => ({
                    tabBarIcon: ({ focused, color, size }) => {
                        let iconName;
                        switch (route.name) {
                            case "Local":
                                iconName = focused ? "folder" : "folder-outline";
                                break;
                            case "Itunes":
                                iconName = focused ? "globe" : "globe-outline";
                                break;
                            default:
                                iconName = "ban";
                                break;
                        }
                        return <Ionicons name={iconName} size={size} color={color} />;
                    },

                })}
                tabBarOptions={{ activeTintColor: "#0EB9F9", inactiveTintColor: "white", tabStyle:{backgroundColor:"black" }}}>
                <Tabs.Screen name="Local">
                    { props => <LocalView {...props} listMusic={musicList} /> }
                </Tabs.Screen>
                <Tabs.Screen name="Itunes">
                    {props => <ItunesView {...props} />}
                </Tabs.Screen>
            </Tabs.Navigator>
    );
};

export default App;

