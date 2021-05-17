
import React, {useState} from "react";
import {Button, Image, StyleSheet, View, Text} from "react-native"
import MapView from 'react-native-maps'

export default function MapPanel({getContactAndImage}) {

    const [contact, setContact] = useState({name:""})
    const [img, setImg] = useState({link:""})

    const startGame = () => {
        let {img, contact} = getContactAndImage()
        setContact(contact)
        setImg(img)
    }
    return (
        <View style={styles.ctn}>

            <MapView
                style={{flex: 1}}
                provider={MapView.PROVIDER_GOOGLE}
                initialRegion={{
                    latitude: 48.8534,
                    longitude: 2.3488,
                    latitudeDelta: 0.0922 * 1.2,
                    longitudeDelta: 0.0421 * 1.2
                }}
            >
                {img.link !== "" &&
                <MapView.Marker
                    coordinate={{
                        latitude: 48.8534,
                        longitude: 2.3488
                    }}
                >
                    <View style={styles.crp}>
                        <Image style={styles.img} source={{uri: img.link}}/>
                        <Text>{contact.name}</Text>
                    </View>
                </MapView.Marker>
                }
            </MapView>

            <Button title="Play" onPress={startGame} />
        </View>

    );
}


const styles = StyleSheet.create({
    ctn: {
        backgroundColor: "#2C2C2C",
        display: "flex",
        flex: 1,
        padding: 15,
        height: "100%",
        maxWidth: "100%",
    },
    crp: {
      backgroundColor: "red",
      padding:10,
    },
    img: {
        width:50,
        height: 50,

    }

});