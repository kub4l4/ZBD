import React, {useState} from 'react';
import {createTheme, MuiThemeProvider} from "@material-ui/core";
import RadioConfigGroup from "./components/RadioConfigGroup";
import {RadioType} from "./objects/enums/RadioType";
import ResultCard from "./components/ResultCard";
import MaterialButton from "./components/MaterialButton";
import {OperationName} from "./objects/enums/OperationName";
import {useEndpoints} from "./api/Endpoints";
import {takeUntil, timer} from "rxjs";

const theme = createTheme({
    typography: {
        fontFamily: ['Roboto', 'sans-serif'].join(',')
    },
    palette: {
        primary: {main: '#420088'}, // Purple and green play nicely together.
        secondary: {main: '#124800'}, // This is just green.A700 as hex.
    },

});

function App() {

    const [operationName, setOperationName] = useState("0");
    const [operationType, setOperationType] = useState("0");
    const [operationQuantity, setOperationQuantity] = useState("10");

    const [relationalTime, setRelationalTime] = useState("0.01")
    const [nonRelationalTime, setNonRelationalTime] = useState("0.05")
    const timer$ = timer(5000);

    const database = useEndpoints();

    const buttonAction = () => {
        switch (Number(operationName)) {
            case OperationName.add:
                database.getAddingTime(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe(

                )
                break;
            case OperationName.update:
                //do update stuff
                break;
            case OperationName.delete:
                //do delete stuff
                break;
            case OperationName.get:
            default:
                //do get stuff
                break;
        }
    }

    return (
        <MuiThemeProvider theme={theme}>
            <div className="application">
                <header className="header">
                    <h1 className="title">Projekt ZTBD - Kamil Kubala, Maciej Spólnik</h1>
                </header>
                <div className="container card">
                    <div className="radios">
                        <RadioConfigGroup value={operationName} setValue={setOperationName}
                                          radioType={RadioType.OPERATION}/>
                        {operationName !== "2" && <RadioConfigGroup value={operationType} setValue={setOperationType}
                                                                    radioType={RadioType.TYPE}/>}
                        <RadioConfigGroup value={operationQuantity} setValue={setOperationQuantity}
                                          radioType={RadioType.QUANTITY}/>
                    </div>
                    <MaterialButton onPress={buttonAction}/>
                    <h2 className="result">Wynik</h2>
                    <ResultCard title="PostgrSQL" value={`${relationalTime}sek`}/>
                    <div style={{height: '3vh'}}/>
                    <ResultCard title="MongoDB" value={`${nonRelationalTime}sek`} />
                </div>
            </div>
        </MuiThemeProvider>
);
}

export default App;
