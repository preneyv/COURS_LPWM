import {createSlice} from "@reduxjs/toolkit"

const filterSlice = createSlice({
    name:"filterLocalLib",
    initialState: {filter: "all"},
    reducers: {
        setFilter: (state, action) => {
            state.filter = action.payload
        }
    }

})

export const { setFilter } = filterSlice.actions
export const filterSelector = (state) => state.filterLocalLib
export default filterSlice.reducer




