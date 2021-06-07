//React and ReactNative Import
import {View, Text, StyleSheet, TouchableOpacity, TextInput, Button} from "react-native";
import React, {useState, useEffect} from 'react';
import { useDispatch } from "react-redux";
import { Audio } from 'expo-av';
import Toast from 'react-native-toast-message';
import Ionicons from "react-native-vector-icons/Ionicons"
import InsetShadow from "react-native-inset-shadow"

//Services import
import {getAudioPermission} from '../../services/requestPermissions'

//Slices import
import {add} from '../../reducers/librairieSlice'

//Components Import
import PlaySong from "../../services/playSong";


const startRecording = async () => {
    const recording = new Audio.Recording()
    await recording.prepareToRecordAsync(Audio.RECORDING_OPTIONS_PRESET_HIGH_QUALITY, Audio.RECORDING_OPTION_ANDROID_OUTPUT_FORMAT_MPEG_4)
    await recording.startAsync()
    return recording
}

const Micro = ({selectedColor}) => {
    const dispatch = useDispatch()
    const [recordingState, setRecordingState] = useState("NOT_STARTED")
    const [recording, setRecording] = useState(undefined)
    const [newSongName, setNewSongName] = useState("")

    const handleStartRecording = async () => {
        try {
            const dataRecorded = await startRecording()
            setRecording(dataRecorded)
            setRecordingState("BEGUN")
        }catch(err){ 
            console.error("Erreur lors de l'enregistrement")
        }
    }

    const handleStopRecording = async () => {

        await recording.stopAndUnloadAsync()
        setRecordingState("OVER")
    
    }

    const resetStateRecord = () => {
        setRecordingState("NOT_STARTED")
        setNewSongName("")
        setRecording(undefined)
    }
    const addRecordedSon = () => {
    
        const newSong = {name:newSongName, type:"RECORDED", req:recording.getURI()}
        dispatch(add(newSong))
        Toast.show({
            text1: 'Son ajouté',
          });
        resetStateRecord()
    }

    useEffect(() => {
        getAudioPermission()
    }, [])

    const {R, G, B} = selectedColor
    return (
        <View style={styles.mainCtn}>
            <View style={styles.container}>
                <InsetShadow shadowOpacity={1} shadowRadius={12} shadowColor={`rgb(${R},${G},${B})`} containerStyle={{borderWidth: 10, borderRadius:100, borderColor:`rgba(${R},${G},${B},0.7)`,height:"auto", marginLeft: 0, borderWidth:1}} >
                        { recordingState === "NOT_STARTED" &&
                            <TouchableOpacity onPress={handleStartRecording}>
                                <Ionicons name="ellipse" size={75} color="white" style={{padding:30}}/>
                            </TouchableOpacity>
                        }

                        { recordingState === "BEGUN" &&
                            <TouchableOpacity onPress={ handleStopRecording }>
                                <Ionicons name="ellipse" size={75} color="red" style={{padding:30}}/>
                            </TouchableOpacity>
                        }

                        {
                            (recordingState === "OVER" || recordingState === "VALIDATE") &&
                        <TouchableOpacity onPress={resetStateRecord}>
                            <Ionicons name="refresh-outline" size={75} color="white" style={{padding:30}}/>
                        </TouchableOpacity>    
                        }
                        
                </InsetShadow>
                {
                    (recordingState === "OVER" || recordingState === "VALIDATE") &&
                    <View style={styles.ctnListenAndValid}>
                    <PlaySong song={{req:recording.getURI()}}>
                        <Ionicons name="caret-forward-outline" size={40} color={`rgb(${R},${G},${B})`} style={{textShadowColor:`rgba(${R},${G},${B},0.7)`, textShadowRadius: 16.00, elevation:20}} />
                    </PlaySong>
                    <TouchableOpacity onPress={()=> setRecordingState("VALIDATE")} >
                        <Ionicons name="checkmark-outline" size={40} color={`rgb(${R},${G},${B})`} style={{textShadowColor:`rgba(${R},${G},${B},0.7)`, textShadowRadius: 16.00, elevation:20}} />
                    </TouchableOpacity>
                </View>
                }
            </View>
            { recordingState === "VALIDATE" && <View style={styles.inputBox}><TextInput style={styles.input} value={newSongName} onChangeText={setNewSongName} placeholder="Nom de l'enregistrement"/><Button title="Valider" onPress={addRecordedSon}/></View>}
            { recordingState === "BEGUN" && <Text style={{color: "white"}}>Enregistrement en cours ...</Text>}

        </View>
        
    )
}

const styles = StyleSheet.create({
    mainCtn: {
        backgroundColor: "black",
        flex:1,
        alignItems: "center",
        justifyContent: "center",
        borderTopColor: "#202020",
        borderWidth:2,
    },
    container: {
        flex:1,
        flexDirection: "row",
        alignItems: "center",
        justifyContent: "center",


    },
    startRecord: {
        borderWidth:1,
        borderColor: "rgba(41,238,226,0.7)",
        borderRadius: 50,
    },
    ctnListenAndValid: {
        flexDirection: "column",
        justifyContent:"space-between",
        height:200,
        
    },
    inputBox: {
        width: "100%", 
        marginTop: 10,
    },
    input: {
        width: "100%", 
        backgroundColor:"white", 
        padding:7,
        marginBottom: 2,
    }


})

export default Micro