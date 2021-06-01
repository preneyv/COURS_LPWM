import { Audio } from 'expo-av';

const getAudioPermission = async () => {
    await Audio.requestPermissionsAsync()
    await Audio.setAudioModeAsync({
        allowsRecordingIOS: true,
        playsInSilentModeIOS: true,
        
    })
}


export {getAudioPermission}