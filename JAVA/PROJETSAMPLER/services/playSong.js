import React, { useEffect, useState } from "react";
import { TouchableOpacity } from "react-native";
import { Audio } from "expo-av";


const PlaySong = ({children,song, goToSample, limitPlay}) => {
  const [sound, setSound] = useState();
  const [isPlaying, setIsPlaying ] = useState(false)

  const songToPlay = song.type === "DEFAULT" ? song.req : {uri:song.req} 
  


  const playSound = async () => {

    try{

      let stateSound;
      const sound = new Audio.Sound()
      await sound.loadAsync(songToPlay);
      setSound(sound);
      const {durationMillis} = await sound.getStatusAsync()
      
      if(limitPlay) {
        const start = (limitPlay.start * durationMillis) /100
        stateSound =  await sound.playFromPositionAsync(start)
      }else{
        stateSound = await sound.playAsync();
      }
      setIsPlaying(!isPlaying)
  
    }catch(err){
      console.error(err)
    }

  };

  const stopSound = async () => {
    await sound.stopAsync()
    setIsPlaying(false)
  }

  useEffect(() => {
    setIsPlaying(false)
    return () => {
      
      if (sound) {
        sound.unloadAsync();
        
      }
    };
  }, [sound]);

  return (


    <TouchableOpacity onPress={ isPlaying ? stopSound : playSound} onLongPress={goToSample ? goToSample : undefined}> 
        {children}
    </TouchableOpacity>

  );
};

export default PlaySong;