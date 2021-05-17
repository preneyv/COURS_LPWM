import {View,Text, StyleSheet, Image} from "react-native";
import React from "react";


const ContactItem = ({contact}) => {
    return (
        <View style={styles.ctn}>
            <Text style={styles.ctc}>{contact?.name}</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    ctn: {
        display: "flex",
        flexDirection: "row",
        backgroundColor: "black",

        padding: 10,
        borderRadius:5,
        marginTop: 5,
        marginBottom: 5,
        width: "100%",
    },
    ctc: {
        color: "#0EB9F9",
        fontSize: 15,
    }
})
export default ContactItem