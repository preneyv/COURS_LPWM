const formatData = (data) => {
    return data.map((item) => {
        return {id:`${item.id}`, type:"FREESOUND", name:item.name}
    })
}

const getListFreesound = async  (value) => {
    const API_KEY = "HYt7NS5XGhS4XgcroYpp9FByYPkrgDcY0M4RHPNd"
    let res = await fetch(`https://freesound.org/apiv2/search/text/?query=${value}&token=${API_KEY}`, {method: "GET"})
    let data = await res.json()
    return formatData(data.results)

}

const getSound = async (songId) => {
    const API_KEY = "HYt7NS5XGhS4XgcroYpp9FByYPkrgDcY0M4RHPNd"
    let res = await fetch(`https://freesound.org/apiv2/sounds/${songId}/?token=${API_KEY}`, {method: "GET"})
    let data = await res.json()
    console.log(data)
    return data.previews['preview-hq-mp3']
}

export  {getListFreesound, getSound}