import {View, Text, FlatList} from "react-native"
import {RadioButton} from 'react-native-paper'
import React, { useState } from 'react'
import {useDispatch, useSelector} from "react-redux"
import Ionicons from "react-native-vector-icons/Ionicons"
import { filteredLibrairieSelector ,librairieSelector} from '../../reducers/librairieSlice'
import PlaySong from '../../services/playSong'
import Filter from "../Filter/filterComp"

import listSon from '../../style/listSon'

const ItemSong = ({item, idSong, color, editIdSon}) => {
    const {R, G, B} = color
    return (
        <View style={listSon.songItem}>
            <View style={listSon.panelNameSong}><Text style={{color:"white", fontSize:15}}>{item.name}</Text></View>
            <View style={listSon.btns}>
                <PlaySong song={item}>
                    <Ionicons name="caret-forward-outline" size={25} color={`rgb(${R},${G},${B})`} style={{textShadowColor:`rgba(${R},${G},${B},0.7)`, textShadowRadius: 16.00, elevation:20}} />
                </PlaySong>
                <RadioButton onPress={()=>editIdSon(item.id)} value={item.id} status={idSong === item.id ? 'checked' : 'unchecked'} size={25} uncheckedColor={`rgb(${R},${G},${B})`} color={`rgb(${R},${G},${B})`} style={{textShadowColor:`rgba(${R},${G},${B},0.7)`, textShadowRadius: 16.00, elevation:20}} />
            </View>
        </View>
    )
}

const Locallib = ({selectedSong,selectedColor, handleChangeSon}) => {
    const lib = useSelector(filteredLibrairieSelector)
    console.log(lib)

    return (
        <View style={listSon.container}>
            <Filter />
            <View style={{flex:1}}>
                <FlatList 
                    renderItem={({item}) => <ItemSong item={item} idSong={selectedSong} color={selectedColor} editIdSon={handleChangeSon}/> }
                    keyExtractor={(item) => item.id}
                    data={lib}
                    style={{width:"100%"}}
                />
            </View>
        </View>
    )
}

export default Locallib