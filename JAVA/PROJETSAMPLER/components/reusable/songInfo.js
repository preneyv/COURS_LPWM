import { Audio } from "expo-av";

const getInfosSong = async (song) => {

    const songToPlay = song.type === "DEFAULT" ? song.req : {uri:song.req} 
    try{
        const mediaObject = new Audio.Sound()
        await mediaObject.loadAsync(songToPlay);
        const {durationMillis} = await mediaObject.getStatusAsync()
        return durationMillis

      }catch(err){
        console.error(err)
      }
}

export default getInfosSong