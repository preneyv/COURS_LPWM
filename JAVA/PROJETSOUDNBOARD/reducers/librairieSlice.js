import {createSlice} from "@reduxjs/toolkit"
import getAllDefaultSong from '../components/reusable/defaultSong'


const librairieSlice = createSlice({
    name: "librairieLocale",
    initialState: getAllDefaultSong,
    reducers: {
        add: (state, action) => {
            console.log(state)
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

export const librairieSelector = (state) => {console.log(state); return state.librairieLocale}
export const {add, remove} = librairieSlice.actions
export default librairieSlice.reducer