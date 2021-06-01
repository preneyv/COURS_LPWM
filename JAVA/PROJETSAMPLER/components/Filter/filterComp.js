import {useDispatch, useSelector} from "react-redux"
import React, { useState } from 'react'
import { Button, Touchable, TouchableOpacity, View, Text, StyleSheet } from "react-native"
import {setFilter, filterSelector} from "../../reducers/filterSlice"


const Filter = () => {
    const {filter} = useSelector(filterSelector)
    const dispacth = useDispatch()
    return (
        <View style={{flexDirection:"row", justifyContent:"space-between", paddingTop:8, paddingLeft:4, paddingRight:4}}>
            <TouchableOpacity onPress={()=> dispacth(setFilter("all"))} >
                <View style={[Styles.btn_ctn, filter==="all" ? {backgroundColor:"white"} : {backgroundColor:"black"}]}>
                    <Text style={[Styles.btn_txt, filter==="all" ? {color:"black"} : {color:"white"}]}>Tout</Text>
                </View>
            </TouchableOpacity>
            <TouchableOpacity onPress={()=> dispacth(setFilter("default"))}>
                <View style={[Styles.btn_ctn, filter==="default" ? {backgroundColor:"white"} : {backgroundColor:"black"}]}>
                    <Text style={[Styles.btn_txt, filter==="default" ? {color:"black"} : {color:"white"}]}>Défauts</Text>
                </View>
            </TouchableOpacity>
            <TouchableOpacity onPress={()=> dispacth(setFilter("recorded"))}>
                <View style={[Styles.btn_ctn, filter==="recorded" ? {backgroundColor:"white"} : {backgroundColor:"black"}]}>
                    <Text style={[Styles.btn_txt, filter==="recorded" ? {color:"black"} : {color:"white"}]}>Enregistrés</Text>
                </View>
            </TouchableOpacity>
            <TouchableOpacity onPress={()=> dispacth(setFilter("download"))}>
                <View style={[Styles.btn_ctn, filter==="download" ? {backgroundColor:"white"} : {backgroundColor:"black"}]}>
                    <Text style={[Styles.btn_txt, filter==="download" ? {color:"black"} : {color:"white"}]}>Téléchargés</Text>
                </View>
            </TouchableOpacity>
        </View>
    )
}

const Styles = StyleSheet.create({
    btn_ctn: {
        borderColor:"white",
        borderWidth:1,
        borderRadius: 3,
        

    },
    btn_txt: {
        fontSize: 12,
        padding: 5
    }
})

export default Filter