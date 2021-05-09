import {Picker, ScrollView, Text, TextInput, View} from "react-native";
import React, {useState, useEffect, useRef} from 'react';
import {form, styles} from "../../style";
import { RadioButton } from 'react-native-paper';

const ListFilm = ({navigation, list, handleChangeMovie, handleFilterList}) => {

    const [checked, setChecked] = useState("Desc")
    const [search, setSearch] = useState("")
    const [picker, setPicker] = useState("Note")


    const handleMovie = (movie) => {
        handleChangeMovie(movie)
        //navigation.navigate("ShowFilm")
    }

    const handleSearch = search => {
        setSearch(search)
    }
    const handleSelect = value => {
        setPicker(value)
    }
    const sortList =() => {

        let res = list.sort((itemA, itemB) =>  {
            let back
            switch (picker) {
                case "Date" : back = checked === "Desc" ?  itemA.date - itemB.date : itemB.date - itemA.date
                              break
                case "Title" : back = checked === "Desc" ?  itemA.title - itemB.title : itemB.title - itemA.title
                    break
                case "Note" : back = checked === "Desc" ?  itemA.note - itemB.note : itemB.note - itemA.note
                    break
            }

            return back
        })

        return res
    }
    useEffect(() => {
        handleFilterList(sortList())
    }, [picker, checked])


    return (
        <View style={styles.container}>
            <View style={styles.sortAndFilter}>
                <View class={styles.filter}>
                    <Text style={form.label}>Filtrer la liste</Text>
                    <TextInput style={form.input} value={search} onChangeText={handleSearch}/>
                </View>
                <View >
                    <Text style={form.label}>Trier la liste</Text>
                    <View style={styles.sort}>
                        <Picker onValueChange={handleSelect} selectedValue={picker} style={{flex:1}}>
                            <Picker.Item value="Date" label="Date" />
                            <Picker.Item value="Titre" label="Titre" />
                            <Picker.Item value="Note" label="Note" />
                        </Picker>
                        <RadioButton
                            value="Asc"
                            status={ checked === 'Asc' ? 'checked' : 'unchecked' }
                            onPress={() => setChecked('Asc')}
                        /><Text>Asc</Text>
                        <RadioButton
                            value="Desc"
                            status={ checked === 'Desc' ? 'checked' : 'unchecked' }
                            onPress={() => setChecked('Desc')}
                        /><Text>Desc</Text>
                    </View>
                </View>
            </View>
            <ScrollView style={styles.container}>
                {
                    list.map((el, key) => {
                        if(el.titre.toLowerCase().startsWith(search.toLowerCase()) || search === "")
                        {
                            return (
                                <View style={styles.unitFilm} key={key} onStartShouldSetResponder={() => handleMovie(el)}>
                                    <Text style={styles.titreFilm}>{el.titre}</Text>
                                    <View style={styles.ctnBtm}>
                                        <Text style={styles.date}>{el.date}</Text>
                                        <Text style={styles.note}>{el.note}</Text>
                                    </View>
                                </View>
                            )
                        }
                        return null
                    })
                }
            </ScrollView>
        </View>
    );
}

export default ListFilm
