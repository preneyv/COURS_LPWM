import {
    combineReducers,
    configureStore,
    getDefaultMiddleware,
} from "@reduxjs/toolkit"
import librairieReducer from "../reducers/librairieSlice"
import sbdReducer from "../reducers/btnSlice"
import AsyncStorage from "@react-native-async-storage/async-storage"
import {
    persistReducer,
    FLUSH,
    REHYDRATE,
    PAUSE,
    PERSIST,
    PURGE,
    REGISTER,
} from "redux-persist";

const reducers = combineReducers({ 
    librairieLocale : librairieReducer,
    btnSoundBoard :  sbdReducer
});

const persistedReducer = persistReducer(
    { key: "root", storage: AsyncStorage },
    reducers
);

export default configureStore({
    reducer: persistedReducer,
    middleware: getDefaultMiddleware({
        serializableCheck: {
            ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
        },
    }),
});
