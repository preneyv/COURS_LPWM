import React from "react";
import { StyleSheet, View , FlatList, Image} from "react-native";




const ListPhotos = ({ listPhotos }) => {

    return (
        <View style={styles.ctn}>
            <FlatList
                renderItem={({ item }) => <Image style={styles.img} source={{uri : item.link}}/> }
                data={listPhotos}
                keyExtractor={(item) => item.id}
                style={styles.list}
                numColumns="3"
            />
        </View>
    );
};

const styles = StyleSheet.create({
    ctn: {
        backgroundColor: "#2C2C2C",
        display: "flex",
        flexDirection: "row",
        flex: 1,
        padding: 15,
        height: "100%",
        maxWidth: "100%",
        alignItems: "center",
        justifyContent:"center"
    },
    list: {
      display: "flex",
      flexDirection: "row",

    },
    img: {
        width:100,
        height: 100,
        display: "flex",
        flexDirection: "row",
        backgroundColor: "black",
        padding: 10,
        borderRadius:5,
        margin: 5

    }

})

export default ListPhotos