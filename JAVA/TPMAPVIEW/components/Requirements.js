import * as Contacts from 'expo-contacts';
import * as MediaLibrary from 'expo-media-library';

const requirements = {
    contactRqr : async () => {
        const { status } = await Contacts.requestPermissionsAsync();
        if (status === 'granted') {
            const {data} = await Contacts.getContactsAsync({
                fields: [Contacts.Fields],
            });

            if (data.length > 0) {
                const contact = data.map(item => {
                    if (item.firstName && item.firstName !== "null") {
                        console.log(item)
                        return {name: item.firstName, id:item.id}
                    }
                })

                return contact
            }
        }
    },
    pictureRqr: async () => {
        const { status } = await MediaLibrary.requestPermissionsAsync();
        if (status === 'granted') {
            const {assets} = await MediaLibrary.getAssetsAsync()
            if (assets.length > 0) {
                const images = assets.map(item => {
                    return {link: item.uri, id:item.id, name:item.filename}

                })
                return images
            }
        }
    }
}

export default requirements
