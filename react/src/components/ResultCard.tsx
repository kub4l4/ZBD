import React from 'react';
import '../styles/components.css';


export interface ResultCardProps {
	title: string,
	value: number,
}

const ResultCard = ({ title, value }: ResultCardProps) => {

	return (
		<div className='result-card card'>
			<p className='result-title'>{title}</p>
			<p className='result-content'>{isNaN(value) ? '-' : `${value} sekund`}</p>
		</div>
	);
};

export default ResultCard;
