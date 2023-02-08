import React, { useState } from 'react';
import { createTheme, MuiThemeProvider } from '@material-ui/core';
import RadioConfigGroup from './components/RadioConfigGroup';
import { RadioType } from './objects/enums/RadioType';
import ResultCard from './components/ResultCard';
import MaterialButton from './components/MaterialButton';
import { OperationName } from './objects/enums/OperationName';
import { useEndpoints } from './api/Endpoints';
import { Observable, takeUntil, timer } from 'rxjs';
import { ExecutionTimeDto } from './api/model/ExecutionTimeDto';

const theme = createTheme({
	typography: {
		fontFamily: ['Roboto', 'sans-serif'].join(',')
	},
	palette: {
		primary: { main: '#420088' }, // Purple and green play nicely together.
		secondary: { main: '#124800' } // This is just green.A700 as hex.
	}

});

function App() {

	const [operationName, setOperationName] = useState('0');
	const [operationType, setOperationType] = useState('0');
	const [operationQuantity, setOperationQuantity] = useState('100');

	const [relationalTime, setRelationalTime] = useState<number>(NaN);
	const [nonRelationalTime, setNonRelationalTime] = useState<number>(NaN);
	const [loading, isLoading] = useState<boolean>(false);
	const timer$ = timer(1000 * 1000);
	const database = useEndpoints();
	const onSuccess = (result: ExecutionTimeDto) => {
		console.log(`<-- RESPONSE [postgres: ${result.postgres}, mongo: ${result.mongo}]`);
		setRelationalTime(result.postgres / 1000);
		setNonRelationalTime(result.mongo / 1000);
		isLoading(false)
	}
	const buttonAction = () => {
			switch (Number(operationName)) {
				case OperationName.add:
					database.addSimple(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
						onSuccess(result)
					});
					break;
				case OperationName.update:
					if (operationType === '0') {
						database.updateSimple(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
							onSuccess(result)
						});
					} else {
					database.updateCondition(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
						onSuccess(result)
					});}
					break;
				case OperationName.delete:
					if (operationType === '0') {
						database.deleteSimple(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
							onSuccess(result)

						});
					} else {
					database.getSimple(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
						onSuccess(result)

					});}
					break;
				case OperationName.get:
				default:

					if (operationType === '0') {
						database.getSimple(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
							onSuccess(result)

						});
					} else if (operationType === '1') {
						database.getCondition(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
							onSuccess(result)
						});
					} else if (operationType === '2') {
						database.getAvgConditioned(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
							onSuccess(result)

						});

					} else {
						database.getConditionedOrdered(Number(operationQuantity)).pipe(takeUntil(timer$)).subscribe((result) => {
							onSuccess(result)
						});
					}
					break;
			}
		}
	;

	return (
		<MuiThemeProvider theme={theme}>
			<div className='application'>
				<header className='header'>
					<h1 className='title'>Projekt ZTBD - Kamil Kubala, Maciej Spólnik</h1>
				</header>
				<div className='container card'>
					<div className='radios'>
						<RadioConfigGroup value={operationName} setValue={setOperationName}
															radioType={RadioType.OPERATION} />
						<RadioConfigGroup value={operationType} setValue={setOperationType} operationName={Number(operationName)}
															radioType={RadioType.TYPE} />
						<RadioConfigGroup value={operationQuantity} setValue={setOperationQuantity}
															radioType={RadioType.QUANTITY} />
					</div>
					<MaterialButton loading={loading} onPress={buttonAction} />
					<h2 className='result'>Wynik</h2>
					<div className='result-cards'>
						<ResultCard title='PostgreSQL' value={relationalTime} />
						<div className='result-separator' />
						<ResultCard title='MongoDB' value={nonRelationalTime} />
					</div>
				</div>
			</div>
		</MuiThemeProvider>
	);
}

export default App;
