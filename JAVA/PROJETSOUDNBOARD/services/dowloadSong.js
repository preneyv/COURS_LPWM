import * as fs from 'expo-file-system'

const downloadSongFromFreesound = async (sound, name) => {
    console.log(name)
    console.log(sound)
    const dw = fs.createDownloadResumable(sound, fs.documentDirectory+name, {}, (p) => {})
    const {uri}  = await dw.downloadAsync()
    return uri
}

export default downloadSongFromFreesound