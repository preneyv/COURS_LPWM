import React from 'react';
import {useData} from './Reusable/ownHooks'
import { TextInput, Text, View, Button, Image } from 'react-native';
import {form} from "../style";

const Connect = ({navigation}) => {
    const [pseudo, setPseudo] = useData()
    const [password, setPassword] = useData()

    const handlePseudo = pseudo => {
        let isValid = pseudo.length > 0
        setPseudo(pseudo, isValid )
    }

    const handlePassword = pass => {
        let isValid = pass.length > 0 && pass === "Test"
        setPassword(pass, isValid )
    }

    const isFormValid = () => {
        let tempArray = [pseudo, password]
        return Object.entries(tempArray).every((key, val) => {
            return key[1].isValid
        })
    }

    return (
        <View style={form.main}>
            <View style={[form.ctn, {justifyContent:"center"}]}>
                <View style={form.header}><Image style={form.icon} source={require("../assets/pop.png")}/></View>
                <View>
                    <Text style={form.label}>Pseudo</Text>
                    <TextInput style={form.input} value={pseudo.value} onChangeText={handlePseudo}/>
                    {(!pseudo.isValid && pseudo.value === "") && <Text>Veuillez saisir un pseudo tout de même</Text>}
                </View>
                <View>
                    <Text style={form.label}>Mot de passe :</Text>
                    <TextInput style={form.input} value={password.value} onChangeText={handlePassword} secureTextEntry={true}/>
                    {(!password.isValid && password.value === "") && <Text>Veuillez saisir un mot de passe correct ( c'est "Test" - oui, pas de sécurité trop poussée, j'avoue :) )</Text>}
                </View>
                {isFormValid() && <View style={form.addBtn}><Button   color="#0020C2" title="Aller" onPress={() => navigation.navigate("Films")}/></View>}
            </View>
        </View>
    )
}

export default Connect