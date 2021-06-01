import {createSlice} from "@reduxjs/toolkit"
import getAllDefaultSong from '../components/reusable/defaultSong'


const librairieSlice = createSlice({
    name: "librairieLocale",
    initialState: getAllDefaultSong,
    reducers: {
        add: (state, action) => {
            return [
                ...state, 
                {
                    name:action.payload.name,
                    type:action.payload.type,
                    req:action.payload.req,
                    id: state.length ? `${parseInt(state[state.length -1].id) + 1}` : "0"
                }
            ]
        },
        update: (state, action) => {
            return state.map((item) => {
                if(item.id === action.payload.id) {
                    return {...item, ...action.payload}
                }else {
                    return item
                }
            })
        }
    }
})

export const librairieSelector = (state) =>  state.librairieLocale
export const {add, remove} = librairieSlice.actions
export default librairieSlice.reducer

export const filteredLibrairieSelector = (state) => {

    switch(state.filterLocalLib.filter) {
        case "all" : return state.librairieLocale
        case "default" : return state.librairieLocale.filter((item) => item.type === "DEFAULT")
        case "download" : return state.librairieLocale.filter((item) => item.type === "DOWLOAD")
        case "recorded" : return state.librairieLocale.filter((item) => item.type === "RECORDED")
    }
}