import {Button, Image, Text, TextInput, View} from "react-native";
import React, {useState, useEffect} from 'react';
import {form} from "../../style";

import {useData} from "../Reusable/ownHooks"

const AddFilm = ({navigation, handleActionAddFilm}) => {
    const [title, setTitle] = useData()
    const [note, setNote] = useData()
    const [comm, setComm] = useData()
    const [plot, setPlot] = useData()
    const [id, setId] = useData()
    const [date, setDate] = useData()
    const [poster, setPoster] = useData()

    const handleTitle = title => {
        let isValid = title.length > 0
        setTitle(title, isValid )
    }

    const handleNote = note => {
        if(note<=10.0) {
            let isValid = note.length > 0
            setNote(note, isValid )
        }
        
    }

    const handleComm = comm => {
        let isValid = comm.length > 0
        setComm(comm, isValid )
    }

    const handlePlot = plot => {
        let isValid = plot.length > 0
        setPlot(plot, isValid )
    }

    const handleId = id => {
        if(id.length <= 10) {
            let isValid = id.length > 0 
            setId(id, isValid )
        }

    }

    const handleDate = date => {
        const regex1 = new RegExp("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")
        let isValid = date.length > 0 && regex1.exec(date)
        setDate(date, isValid )
    }

    const handlePoster = poster =>{
        const regex1 = new RegExp("(https?|ftp|ssh|mailto):\\/\\/[a-z0-9\\/:%_+.,#?!@&=-]+")
        let isValid = poster.length > 0 && regex1.exec(poster)
        setPoster(poster, isValid )
    }

    const isFormValid = () => {
        let tempArray = [title, note, comm, date, id, poster, plot]
        return Object.entries(tempArray).every((key, val) => {
            return key[1].isValid
        })
    }

    const validateForm = ()=> {
        let movie = {
            id:id.value,
            titre: title.value,
            plot: plot.value,
            img:poster.value,
            date: date.value,
            note:note.value,
            com:comm.value
        }

        handleActionAddFilm(movie)
        navigation.navigate("ShowFilm")

    }

    return (
        <View style={form.main}>
            <View style={form.ctn}>
                <View style={form.header}><Image style={form.icon} source={require("../../assets/pop.png")}/><Text style={form.headerText}>Ajouter un film</Text></View>
                <View>
                    <Text style={form.label}>Titre du film :</Text>
                    <TextInput style={form.input} value={title.value} onChangeText={handleTitle}/>
                    {!title.isValid && title.value === "" &&<Text>Veuillez saisir un titre valable</Text>}
                </View>
                <View>
                    <Text style={form.label}>Résumé :</Text>
                    <TextInput style={form.input} value={plot.value} onChangeText={handlePlot}/>
                    {!plot.isValid && plot.value === "" && <Text>Veuillez saisir un résumé</Text>}
                </View>
                <View>
                    <Text style={form.label}>Commentaires :</Text>
                    <TextInput style={form.input} value={comm.value} onChangeText={handleComm} />
                    {!comm.isValid  && comm.value === "" &&<Text>Veuillez laisser un commentaire</Text>}
                </View>
                <View>
                    <Text style={form.label}>Note :</Text>
                    <TextInput style={form.input} value={note.value} onChangeText={handleNote} keyBoardType="numeric"/>
                    {!note.isValid && note.value === "" && <Text>Veuillez saisir une note valable</Text>}
                </View>
                <View>
                    <Text style={form.label}>Id Imdb :</Text>
                    <TextInput style={form.input} value={id.value} onChangeText={handleId}/>
                    {!id.isValid  && id.value === "" && <Text>Veuillez saisir l'identifiant</Text>}
                </View>
                <View>
                    <Text style={form.label}>Date de sortie :</Text>
                    <TextInput style={form.input} value={date.value} onChangeText={handleDate}/>
                    {!date.isValid && date.value === "" && <Text>Veuillez saisir une date correcte. Ex : 15/12/2020</Text>}
                </View>
                <View>
                    <Text style={form.label}>Lien du poster du film :</Text>
                    <TextInput style={form.input} value={poster.value} onChangeText={handlePoster}/>
                    {!poster.isValid && poster.value === "" && <Text>Veuillez récuperer le lien sur Imdb</Text>}
                </View>
                {isFormValid() && <View style={form.addBtn}><Button   color="#0020C2" title="Ajouter" onPress={validateForm}/></View>}
            </View>
        </View>
    )

}

export default AddFilm