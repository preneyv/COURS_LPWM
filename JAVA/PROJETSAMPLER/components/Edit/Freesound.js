import {View, Text, StyleSheet, TextInput, FlatList, TouchableOpacity} from "react-native"
import React, { useEffect, useState } from 'react'
import {useDispatch, useSelector} from "react-redux"
import Ionicons from "react-native-vector-icons/Ionicons"
import {add, librairieSelector} from '../../reducers/librairieSlice'
import listSon from '../../style/listSon'
import PlaySong from '../../services/playSong'

import {getListFreesound, getSound} from '../reusable/freesoundAPI'
import downloadSong from '../../services/dowloadSong'


const ItemSong = ({item, color}) => {
    const {R, G, B} = color
    const [songFreesound, setSongFreesound] = useState(item)

    const getUrlSound = async () => {
        let res = await getSound(item.id)
        setSongFreesound({...songFreesound, req:res})
        

    }
    useEffect(()=> {
        getUrlSound()
    },[])

    const dispatch = useDispatch()
    const handleAddSong = async () => {

        let newFile = await downloadSong(songFreesound.req, songFreesound.id)
        console.log(newFile)
        console.log(songFreesound)
        dispatch(add({name:songFreesound.name, type:"DOWNLOAD",req:newFile }))
    }

    return (
        <View style={[listSon.songItem, {borderBottomColor: `rgba(${R},${G},${B},0.7)`}]}>
            <View style={listSon.panelNameSong}><Text style={{color:"white", fontSize:15}}>{item.name}</Text></View>
            <View style={listSon.btns}>
                <PlaySong song={songFreesound}>
                    <Ionicons name="caret-forward-outline" size={25} color={`rgb(${R},${G},${B})`} style={{textShadowColor:`rgba(${R},${G},${B},0.7)`, textShadowRadius: 16.00, elevation:20}} />
                </PlaySong>
                <TouchableOpacity onPress={handleAddSong}><Ionicons name="add-outline"  size={25} color={`rgb(${R},${G},${B})`} style={{textShadowColor:`rgba(${R},${G},${B},0.7)`, textShadowRadius: 16.00, elevation:20}} /></TouchableOpacity>
            </View>
        </View>
    )
}

const Freesound = ({selectedColor}) => {
    const [input, setInput] = useState("")
    const [listFreesound, setListFresound] = useState([])

    const searchOnFreesound = async () => {
        if(input !== "") {
            let data = await getListFreesound(input)
            setListFresound(data)
            
        }
    }

    useEffect(()=> {
        let timer = setTimeout(searchOnFreesound, 1000)
        return () => {clearTimeout(timer)}
    },[input])

    return (
        <View style={listSon.container}>
            <View style={styles.inputBox}><TextInput style={styles.input} value={input} onChangeText={setInput} placeholder="Recherche un son"/></View>
            <FlatList 
                renderItem={({item}) => <ItemSong item={item} color={selectedColor}/> }
                keyExtractor={(item) => item.id}
                data={listFreesound}
                style={{width:"100%"}}
            />
        </View>
    )
}

const styles = StyleSheet.create({
    inputBox: {
        width: "100%", 
        marginTop: 10,
    },
    input: {
        width: "100%", 
        backgroundColor:"white", 
        padding:5
    }
})

export default Freesound