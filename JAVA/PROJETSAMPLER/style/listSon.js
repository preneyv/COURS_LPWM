import {StyleSheet} from "react-native";



const listSon = StyleSheet.create({

    container: {
        backgroundColor: "black",
        flex:1,
        flexDirection: "column",
        //alignItems: "center",
        justifyContent: "center",
        borderTopColor: "#202020",
        borderWidth:2,
    },
    btns: {
        flexDirection:"row",
        flex:1,
        alignItems:"center",
        justifyContent:"space-between",
        
    },
    songItem: {
        flexDirection: "row",
        width: "100%",
        justifyContent: "space-between",
        alignItems: "center",
        borderWidth: 1,
        borderStyle: "dashed",
        borderBottomColor: "#202020",
        borderRadius:1,
        paddingLeft: 10,
        paddingTop: 10,
        paddingBottom: 10,
    },
    panelNameSong: {
        flex:3,
    }
})


export default listSon