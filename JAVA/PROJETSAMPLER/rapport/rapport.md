# Main screen 
<div><h3>Welcome Page / Soundboard</h3><img src="./assets/soundboard.jpg" alt="Soundboard page" width="200" height="300"></div><div><h3>Edit Button SoundBoard / Local Library</h3><img src="./assets/localLib.jpg" alt="Soundboard page" width="200" height="300"></div><div><h3>Edit Button SoundBoard / Record sound</h3><img src="./assets/micro.jpg" alt="Soundboard page" width="200" height="300"></div><div><h3>Edit Button SoundBoard / Download freesound</h3><img src="./assets/freesound.jpg" alt="Soundboard page" width="200" height="300"></div>





# The Project Structure
    ## components
    (This Folder contains all usefull components of the app)
        ### edit
            * Freesound.js
            * Locallib.js
            * Micro.js
            * Sample.js
        ### filter
            * filterComp.js
        ### reusable
        (This folder contains JS objects or JS methods that can be used anywhere in the app)
            * color.js
            * defaultBtn.js
            * defaultSong.js
            * freesoundAPI.js
            * songInfo.js
        * Soundboard.js
    ## reducers
        * btnSlice.js
        * filterSlice.js
        * librairieSlice.js
    ## services
        * downloadSong.js
        * playSong.js
        * requestPermission.js
    ## store
        * store.js
    ## style
        * communs.js
        * listSon.js
    * App.js