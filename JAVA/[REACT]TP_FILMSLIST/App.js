import React from 'react';
import {ImageBackground } from 'react-native';
import {api, testResApi} from "./api.js"

import { form} from "./style";

import Acceuil from "./components/Accueil";
import ShowFilm from "./components/ShowMovie";
import AddFilm from "./components/FormView";


export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {vue: null, listFilm:[], movieToShow:null}
    this.controllerSetView = this.controllerSetView.bind(this)
    this.handleAddFilm = this.handleAddFilm.bind(this)
  }
  async componentDidMount() {
    let film = await api.getFilms()
    this.setState(prev => ({
      listFilm : [...this.state.listFilm, ...film]
    }))
    this.controllerSetView(0)
  }

  handleAddFilm(film) {
    this.setState({
      listFilm : [...this.state.listFilm, film],
      movieToShow: film
    }, () => {
      this.controllerSetView(2)
    })
  }

  controllerSetView(numVue) {
    switch (numVue) {
      case 0 :
        this.setView(<Acceuil handleSwitchView = {this.controllerSetView} listFilm={this.state.listFilm}/> )
        break
      case 1 :
        this.setView(<AddFilm handleSwitchView = {this.controllerSetView} handleActionAddFilm = {this.handleAddFilm}/>)
        break
      case 2 :
        this.setView(<ShowFilm handleSwitchView = {this.controllerSetView} movie={this.state.movieToShow !== null ? this.state.movieToShow : undefined } />)
    }
  }

  setView(cmp) {
    this.setState(prev => ({
      vue : cmp
    }))
  }

  render(){
    return (
        <ImageBackground source={{uri : require("./assets/fe.jpg")}} style={form.bg}>
          {this.state.vue}
        </ImageBackground>)
  }


}
