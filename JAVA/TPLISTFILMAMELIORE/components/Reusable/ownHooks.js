import React, {useState} from 'react';

const useData = () => {
    const [val, setVal] = useState(undefined)
    const [isValid, setIsValid] = useState( false)

    const setData = (val, isValid) => {
        setVal(val)
        setIsValid(isValid)
    }

    return [{value : val, isValid: isValid}, setData]
}

export {useData}