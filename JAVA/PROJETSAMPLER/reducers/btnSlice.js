import {createSlice} from "@reduxjs/toolkit"
import defaultBtn from '../components/reusable/defaultBtn'

const btnSoundBoardSlice = createSlice({
    name:"btnSoundBoard",
    initialState: defaultBtn,
    reducers : {
        editColor: (state, action) => {
            return state.map((item) => {
                if(item.idBtn === action.payload.idBtn) {
                    return {...item, idColor:action.payload.idClr}
                }else {
                    return item
                }
            })
        }, 
        editSound: (state, action) => {
            return state.map((item) => {
                if(item.idBtn === action.payload.idBtn) {
                    return {...item, idSound:action.payload.idNewSound}
                }else {
                    return item
                }
            })
        },
        editStartAt: (state, action) => {

            return state.map((item) => {
                if(item.idBtn === action.payload.idBtn) {
                    return {...item, startAt: action.payload.newStartAt}
                }else{
                    return item
                }
            })
        },
        editFinishAt: (state, action) => {

            return state.map((item) => {
                if(item.idBtn === action.payload.idBtn) {
                    return {...item, finishAt: action.payload.newFinishAt}
                }else{
                    return item
                }
            })
        }
    }
    
})


export const sdbSelector = (state) => state.btnSoundBoard
export const {editColor, editSound, editStartAt, editFinishAt } = btnSoundBoardSlice.actions
export default btnSoundBoardSlice.reducer