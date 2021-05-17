import React from "react";
import { StyleSheet, Text, View, Button , FlatList, TouchableOpacity} from "react-native";
import ContactItem from "./ContactItem";


const ListContacts = ({ listContact }) => {

    console.log(listContact)

    return (
        <View style={styles.ctn}>
            <FlatList
                renderItem={
                    ( {item} ) =>  {
                        if (item !== "undefined")  {
                            return <ContactItem contact={item} />
                        }
                    }
                }
                data={listContact}
                keyExtractor={(item) => item?.id}
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
        padding: 15,
        height: "100%",
        maxWidth: "100%",
    },
    list: {
        display: "flex",
        flexDirection: "column",
    }

});

export default ListContacts