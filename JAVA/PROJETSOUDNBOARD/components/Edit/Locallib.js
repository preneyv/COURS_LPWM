import {View, Text, FlatList} from "react-native"
import {RadioButton} from 'react-native-paper'
import React, { useState } from 'react'
import {useDispatch, useSelector} from "react-redux"
import Ionicons from "react-native-vector-icons/Ionicons"
import {librairieSelector} from '../../reducers/librairieSlice'
import PlaySong from '../../services/playSong'

import listSon from '../../style/listSon'

const ItemSong = ({item, idSong, color, editIdSon}) => {
    const {R, G, B} = color
    console.log(item)
    return (
        <View style={[listSon.songItem, {borderBottomColor: `rgba(${R},${G},${B},0.7)`}]}>
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
    const lib = useSelector(librairieSelector)

    return (
        <View style={listSon.container}>
            <FlatList 
                renderItem={({item}) => <ItemSong item={item} idSong={selectedSong} color={selectedColor} editIdSon={handleChangeSon}/> }
                keyExtractor={(item) => item.id}
                data={lib}
                style={{width:"100%"}}
            />
        </View>
    )
}

export default Locallib