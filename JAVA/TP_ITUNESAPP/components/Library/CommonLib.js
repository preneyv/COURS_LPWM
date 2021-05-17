import React from "react";
import { StyleSheet, Text, View, Button , FlatList, TouchableOpacity} from "react-native";

import MusicItem from "./MusicItem";


const CommonLib = ({ list, navigation }) => {

    const handleClick = (item) => {

       if (navigation) {
           navigation.navigate("ViewMusic", {music:item})
       }
    }
    return (
        <View style={styles.ctn}>
            <FlatList
                renderItem={({ item }) =><TouchableOpacity onPress={() => handleClick(item)}><MusicItem music={item}/></TouchableOpacity> }
                data={list}
                style={styles.list}
            />
        </View>
    );
};

const styles = StyleSheet.create({
    ctn: {
        backgroundColor: "#2C2C2C",
        display: "flex",
        flex: 1,
        padding: "15px",
        height: "100%",
        maxWidth: "100%",
    },
    list: {
        display: "flex",
        flexDirection: "column",
        gap: 5
    }

});

export default CommonLib;
