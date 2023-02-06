import {FormControlLabel, Radio, RadioGroup} from "@material-ui/core";
import React, {useState} from "react";
import '../styles/components.css';
import {RadioType} from "../objects/enums/RadioType";
import {getTextsByType, getTitleByType} from "../helpers/uiHelper";


export interface RadioConfigGroupProps {
    radioType: RadioType,
    value: string,
    setValue: (arg: any) => void,
}

const RadioConfigGroup = ({radioType, value, setValue}: RadioConfigGroupProps) => {
    const [texts] = useState<string[]>(getTextsByType(radioType));

    return (
        <div>
            <p className='radio-title'>{getTitleByType(radioType)}</p>
            <RadioGroup value={value} onChange={(event) => {
                event.persist();
                setValue(event.target.value)
            }}>
                <FormControlLabel value={radioType !== RadioType.QUANTITY ? '0' : '10'} control={<Radio/>}
                                  label={texts[0]}/>
                <FormControlLabel value={radioType !== RadioType.QUANTITY ? '1' : '100'} control={<Radio/>}
                                  label={texts[1]}/>
                <FormControlLabel value={radioType !== RadioType.QUANTITY ? '2' : '1000'} control={<Radio/>}
                                  label={texts[2]}/>
                <FormControlLabel value={radioType !== RadioType.QUANTITY ? '3' : '10000'} control={<Radio/>}
                                  label={texts[3]}/>
            </RadioGroup>
        </div>
    );
}

export default RadioConfigGroup;