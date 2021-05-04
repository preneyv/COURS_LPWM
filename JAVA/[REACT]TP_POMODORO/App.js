import { StatusBar } from 'expo-status-bar';
import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import {inline} from "react-native-web/dist/exports/StyleSheet/compile";

export default class App extends React.Component {

  constructor(props) {
    super(props)
    var timer
    this.state = {minutes: 1, secondes: 0, working: true}
  }

  majTime = () => {
    console.log(this.timer)
    if(this.state.secondes === 0) {
      if(this.state.minutes === 0) {
        this.majStateofWork()
        this.resetMin()
        this.majMinute()
      }else {
        this.majMinute()
      }
    }
    this.majSeconde()
  }

  majSeconde = () => {
    this.setState((prevState) => {
      if(prevState.secondes === 0)
      {
        return {secondes : 59}
      }
      else{
        return {secondes : prevState.secondes - 1}
      }
    })
  }

  majMinute = () => {
    this.setState({minutes: this.state.minutes - 1})
  }

  majStateofWork = () => {
    this.setState(prevState => ({
      working: !prevState.working
    }))
  }

  start = () => {
    if(!this.timer)this.timer = setInterval(this.majTime, 1000)
    console.log(this.timer)
  }

  resetSec = () => {
    this.setState({secondes: 0})
  }

  resetMin = () => {
    if(this.state.working)
      this.setState({minutes: 3})
    if(!this.state.working)
      this.setState({minutes: 2})
  }

  reset = () => {
    this.resetMin()
    this.resetSec()
  }

  stop = () => {
    clearInterval(this.timer)
    this.timer = null
    console.log(this.timer)
  }

  componentWillUnmount() {
    clearInterval(this.timer)
  }

  render(){
    return (
        <View style={styles.container}>
          <Text style={styles.h1}>Pomodoro</Text>
              <View>
                <Text style={this.state.secondes <=20 &&  this.state.minutes ===0 ? styles.inRed : styles.h1 }>{this.state.minutes} : {this.state.secondes}</Text>
              </View>
          <View label="flexDirection" selectedValue="row" style={styles.listBtn}>
            <Button style={styles.button} onPress={this.start} title="Start"/>
            <Button style={styles.button} onPress={this.reset} title="Reset"/>
            <Button style={styles.button} onPress={this.stop} title="Stop"/>
          </View>
          <Text style={styles.h1}>{this.state.working ? "Au travail !" : "Pause"}</Text>
        </View>
    );
  }

};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center',
    justifyContent: 'center',
  },
  listBtn: {
    flexDirection: "row",
    gap:10,
  },
  button: {
    paddingTop: 10,
    paddingBottom: 10,
    paddingLeft: 40,
    paddingRight: 40,
    marginTop: 0,
    marginBottom: 0,
    marginLeft: 40,
    marginRight: 40,
    backgroundColor: 'coral',
    borderWidth: 0,
    borderRadius: 6,
  },
  sec: {
    fontWeight: 'bold',
    fontSize: 24,
  },
  h1: {
    fontSize: 60,
  },
  inRed: {
    color: 'red',
    fontSize: 60,
  }
});
