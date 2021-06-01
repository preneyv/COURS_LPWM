import React, { useEffect, useState } from "react";
import { TouchableOpacity } from "react-native";
import { Audio } from "expo-av";


const PlaySong = ({children,song, goToSample, limitPlay}) => {
  const [sound, setSound] = useState();

  const songToPlay = song.type === "DEFAULT" ? song.req : {uri:song.req} 
  


  const playSound = async () => {

    try{
      console.log(songToPlay)
      const sound = new Audio.Sound()
      await sound.loadAsync(songToPlay);
      setSound(sound);
      
      if(limitPlay) {
        const {durationMillis} = await sound.getStatusAsync()
        const start = (limitPlay.start * durationMillis) /100
        sound.playFromPositionAsync(start)
      }else{
        await sound.playAsync();
      }
    
      

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