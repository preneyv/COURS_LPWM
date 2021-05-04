import {StyleSheet} from "react-native";

const styles = StyleSheet.create({
    container: {
        backgroundColor: "rgba(255,255,255,0.9)",
        padding: "5px",
        flexDirection:"column",
        gap: "10px",
        flex: 1,
        height: "90%"
    },
    unitFilm: {
        marginTop: 5,
        marginBottom: 5,
        backgroundColor: '#fff',
        flex: 1,
        padding: "8px",
        borderRadius: "5px",
        minWidth: "85%",
        alignItems: "flex-start",
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.23,
        shadowRadius: 2.62,
        elevation: 4,
        gap: "3px"
    },
    titreFilm: {
        fontWeight:'600',
        flex: 1
    },
    ctnBtm: {
        flexDirection: "row",
        justifyContent: "space-between",
        flex:1,
        width: "100%"
    },
    date: {
        fontSize: "11px"
    },
    note: {
        fontSize: "11px"
    },
    addBtn: {
        padding: "5px",
        alignSelf: "end"
    }
});

const form = StyleSheet.create({
    main: {
        width: "80%",

    },
    ctn: {
        backgroundColor: "rgba(255,255,255,0.9)",
        borderRadius: "5px",
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.23,
        shadowRadius: 2.62,
        elevation: 4,
        padding: "5px",
        gap: "15px"

    },
    header: {
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        gap: "10px",
        padding: "10px",
        fontFamily: "Roboto Condensed",

    },
    headerText: {
        letterSpacing: "1px",
        textTransform: "uppercase",
        fontWeight: "800"
    },
    bg: {
        flex: 1,
        resizeMode: "cover",
        justifyContent: "center",
        alignItems: "center"
    },
    icon: {
        width: 40,
        height: 40
    },
    label: {
        letterSpacing: "1px",
        textTransform: "uppercase",
        fontWeight: "600"
    },
    input: {
        borderWidth: 1
    },
    addBtn: {
        padding: "5px",
        alignSelf: "center"
    }
})

const showMovie = StyleSheet.create({
    mainCtn: {
        width: "min-content",
    },
    unitFilm: {
        marginTop: 5,
        marginBottom: 5,
        backgroundColor: '#fff',
        flex: 1,
        padding: "8px",
        borderRadius: "5px",
        minWidth: "85%",
        alignItems: "flex-start",
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.23,
        shadowRadius: 2.62,
        elevation: 4,
        gap: "3px",
    },
    img: {
        width: 320,
        height: 415,
        margin:"auto",
    },
    title: {
        letterSpacing: "1px",
        textTransform: "uppercase",
        fontWeight: "800"
    },
    date: {
        fontSize: "11px"
    },
    noteAndDate: {
        flex:1,
        flexDirection: 'row',
        justifyContent: "space-between",
        width: "100%",
    }
})

export {form, styles, showMovie}