import React, { useEffect, useState } from "react";
import { Button, Text, TouchableOpacity, View } from "react-native";
import { Audio } from "expo-av";


const PlaySong = ({children,song, goToSample}) => {
  const [sound, setSound] = useState();

  const songToPlay = song.type === "DEFAULT" ? song.req : {uri:song.req} 


  const playSound = async () => {
    console.log(song);
    console.log(songToPlay)
    try{
      const { sound } = await Audio.Sound.createAsync(songToPlay);
      setSound(sound);
      await sound.playAsync();
    }catch(err){
      console.error(err)
    }
    
    
  };

  useEffect(() => {
    return () => {
      if (sound) {
        sound.unloadAsync();
      }
    };
  }, [sound]);

  return (


    <TouchableOpacity onPress={playSound} onLongPress={goToSample ? goToSample : undefined}> 
        {children}
    </TouchableOpacity>

  );
};

export default PlaySong;