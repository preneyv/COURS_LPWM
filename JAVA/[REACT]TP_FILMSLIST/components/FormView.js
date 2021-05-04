import {Button, Image, Text, TextInput, View} from "react-native";
import React from 'react';
import {form} from "../style";

export default class AddFilm extends  React.Component{
    constructor(props) {
        super(props);
        this.state = {
            /* //A décommenter pour insérer directement dans les inputs, rendre le formulaire valide pour passer directement à l'étape suivante, et commenter le reste du state
            title:{value:"The Lord of the Rings: The Fellowship of the Ring", isValid:true},
            note:{value:8.8, isValid:true},
            comm:{value:"Au Top", isValid:true},
            plot:{value:"A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron", isValid:true},
            id:{value:"tt0120737", isValid:true},
            date:{value:"19/12/2001", isValid:true},
            poster:{value:"https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_SX300.jpg", isValid:true},
            */
            title:{value:undefined, isValid:false},
            note:{value:undefined, isValid:false},
            comm:{value:undefined, isValid:false},
            plot:{value:undefined, isValid:false},
            id:{value:undefined, isValid:false},
            date:{value:undefined, isValid:false},
            poster:{value:undefined, isValid:false},

        }
    }

    showNewFilm() {
        this.props.handleSwitchView(0)
    }

    handleTitle = title => {
        this.setState( this.setState( prev => ({
            title : {value:title, isValid: false}
        }), () => {
            if (this.state.title.value.length > 0) {
                this.setState( prev => ({
                    title : {value:this.state.title.value, isValid: true}
                }))
            }
        }))
    }

    handlePlot= plot => {
        this.setState( this.setState( prev => ({
            plot : {value:plot, isValid: false}
        }), () => {
            if (this.state.plot.value.length > 0) {
                this.setState( prev => ({
                    plot : {value:this.state.plot.value, isValid: true}
                }))
            }
        }))
    }

    handleCom= comm => {
        this.setState( this.setState( prev => ({
            comm : {value:comm, isValid: false}
        }), () => {
            if (this.state.comm.value.length > 0) {
                this.setState( prev => ({
                    comm : {value:this.state.comm.value, isValid: true}
                }))
            }
        }))
    }

    handleNote= (note) => {
        if(note<=10.0 && typeof +note === 'number') {
            this.setState( prev => ({
                note : {value:note, isValid: false}
            }), () => {
                if (this.state.note.value.length > 0) {
                    this.setState( prev => ({
                        note : {value:this.state.note.value, isValid: true}
                    }))
                }
            })
        }

    }

    handleId = id => {
        if(id.length <= 10) {
            this.setState( this.setState( prev => ({
                    id : {value:id, isValid: false}
                }),
                () => {
                    if (this.state.id.value.length > 0) {
                        this.setState( prev => ({
                            id : {value:this.state.id.value, isValid: true}
                        }))
                    }
                }))
        }

    }

    handleDate = date => {
        if(date.length <= 11) {
            this.setState( this.setState( prev => ({
                    date : {value:date, isValid: false}
                }),
                () => {
                const regex1 = new RegExp("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$")
                    if (regex1.exec(date)) {
                        this.setState( prev => ({
                            date : {value:this.state.date.value, isValid: true}
                        }))
                    }
                }))
        }

    }

    handlePoster = lien => {
            this.setState( this.setState( prev => ({
                    poster : {value:lien, isValid: false}
                }),
                () => {
                const regex1 = new RegExp("(https?|ftp|ssh|mailto):\\/\\/[a-z0-9\\/:%_+.,#?!@&=-]+")
                    if (regex1.exec(lien)) {
                        this.setState( prev => ({
                            poster : {value:this.state.poster.value, isValid: true}
                        }))
                    }
                }))


    }

    isFormValid = () => {
        return Object.entries(this.state).every((key, val) => {
                return key[1].isValid
        })
    }

    validateForm = ()=> {
        let movie = {
            id:this.state.id.value,
            titre: this.state.title.value,
            plot: this.state.plot.value,
            img:this.state.poster.value,
            date: this.state.date.value,
            note:this.state.note.value,
            com:this.state.comm.value
        }
        this.props.handleActionAddFilm(movie)
    }
    render() {
        return (
            <View style={form.main}>
                <View style={form.ctn}>
                    <View style={form.header}><Image style={form.icon} source={require("../assets/pop.png")}/><Text style={form.headerText}>Ajouter un film</Text></View>
                    <View>
                        <Text style={form.label}>Titre du film :</Text>
                        <TextInput style={form.input} value={this.state.title.value} onChangeText={this.handleTitle}/>
                        {!this.state.title.isValid && this.state.title.value !== undefined && <Text>Veuillez saisir un titre valable</Text>}
                    </View>
                    <View>
                        <Text style={form.label}>Résumé :</Text>
                        <TextInput style={form.input} value={this.state.plot.value} onChangeText={this.handlePlot.bind(this)}/>
                        {!this.state.plot.isValid && this.state.plot.value !== undefined && <Text>Veuillez saisir un résumé</Text>}
                    </View>
                    <View>
                        <Text style={form.label}>Commentaires :</Text>
                        <TextInput style={form.input} value={this.state.comm.value} onChangeText={this.handleCom.bind(this)} />
                        {!this.state.comm.isValid && this.state.comm.value !== undefined && <Text>Veuillez laisser un commentaire</Text>}
                    </View>
                    <View>
                        <Text style={form.label}>Note :</Text>
                        <TextInput style={form.input} value={this.state.note.value} onChangeText={this.handleNote.bind(this)} keyBoardType="numeric"/>
                        {!this.state.note.isValid && this.state.note.value !== undefined && <Text>Veuillez saisir une note valable</Text>}
                    </View>
                    <View>
                        <Text style={form.label}>Id Imdb :</Text>
                        <TextInput style={form.input} value={this.state.id.value} onChangeText={this.handleId.bind(this)}/>
                        {!this.state.id.isValid && this.state.id.value !== undefined && <Text>Veuillez saisir l'identifiant</Text>}
                    </View>
                    <View>
                        <Text style={form.label}>Date de sortie :</Text>
                        <TextInput style={form.input} value={this.state.date.value} onChangeText={this.handleDate.bind(this)}/>
                        {!this.state.date.isValid && this.state.date.value !== undefined && <Text>Veuillez saisir une date correcte. Ex : 15/12/2020</Text>}
                    </View>
                    <View>
                        <Text style={form.label}>Lien du poster du film :</Text>
                        <TextInput style={form.input} value={this.state.poster.value} onChangeText={this.handlePoster.bind(this)}/>
                        {!this.state.poster.isValid && this.state.poster.value !== undefined && <Text>Veuillez récuperer le lien sur Imdb</Text>}
                    </View>
                    {this.isFormValid() && <View style={form.addBtn}><Button   color="#0020C2" title="Ajouter" onPress={this.validateForm}/></View>}
                </View>
            </View>
        )
    }

}