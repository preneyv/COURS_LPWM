import {StyleSheet} from "react-native";

const communs = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: 'black',
        alignItems: 'center',
        flexDirection: "column",
        justifyContent: "space-between",
        paddingTop: 15,
        paddingLeft: 25,
        paddingRight: 25,
    
    },
    play: {
        marginTop: 30,
        flexDirection:"row",
        alignItems: "center",
    },
    triangle: {
        transform: [{ rotate: "90deg" }],
        fontSize: 25,
        color: "#F67518",
        textShadowColor: "#F67518",
        textShadowOffset: {
          width: 0,
          height: 0,
      },
      textShadowRadius: 15.30,
    },

    linePlayer: {
        width: "85%",
        position: "relative",
    },
    line: {
        height:1,
        top: -1,
        left:4,
        width: "100%",
        backgroundColor:"#F67518",
        position: "absolute",
    },
    square: {
        backgroundColor:"#F67518",
        position: "absolute",
        top: -5,
        left:4,
        width: 10,
        height:5,
        shadowOffset: {
            width: 0,
            height: 0,
        },
        shadowOpacity: 0.48,
        shadowRadius: 16.00,
        elevation: 24,
        shadowColor: "#F67518",
    }
})

export default communs