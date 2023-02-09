import csv
import glob
import json
import os

import pandas as pd


def longest_string(column):
	column = column.astype(str)
	return column.str.len().max()


def get_information(df):
	print(df.apply(longest_string))
	print(df.info())
	print(df.head(5))


def prepare_prod_csv():
	joined_files = os.path.join("data", "*.csv")
	joined_list = glob.glob(joined_files)
	df = pd.concat(map(pd.read_csv, joined_list), ignore_index=True)
	# get_information(df)
	df = df.drop(df[df.balcony == "balcony"].index)

	df['price'] = df['price'].fillna(0).astype(int)
	df['m2_real'] = df['m2_real'].fillna(0).astype(float).astype(int)
	df['m2_useful'] = df['m2_useful'].fillna(0).astype(float).astype(int)
	df['air_conditioner'] = df['air_conditioner'].fillna(0).astype(int)
	df['balcony'] = df['balcony'].fillna(0).astype(int)
	df['built_in_wardrobe'] = df['built_in_wardrobe'].fillna(0).astype(int)
	df['chimney'] = df['chimney'].fillna(0).astype(int)
	df['garden'] = df['garden'].fillna(0).astype(int)
	df['ground_size'] = df['ground_size'].fillna(0).astype(int)
	df['construct_date'] = df['construct_date'].fillna(0).astype(float).astype(int)
	df['storage_room'] = df['storage_room'].fillna(0).astype(int)
	df['storage_room'] = df['storage_room'].fillna(0).astype(int)
	df['terrace'] = df['terrace'].fillna(0).astype(int)
	df['kitchen'] = df['kitchen'].fillna(0).astype(float).astype(int)
	df['lift'] = df['lift'].fillna(0).astype(float).astype(int)
	df['swimming_pool'] = df['swimming_pool'].fillna(0).astype(int)
	df.to_csv(r'temp.csv')


def create_nesting():
	output = []
	with open('temp.csv', "r", encoding="utf-8") as csv_file:
		for ad in csv.DictReader(csv_file):
			house_information = {
				'houseType': ad['house_type'],
				'balcony': ad['balcony'],
				'bathNum': ad['bath_num'],
				'builtInWardrobe': ad['built_in_wardrobe'],
				'chimney': ad['chimney'],
				'floor': ad['floor'],
				'garage': ad['garage'],
				'garden': ad['garden'],
				'groundSize': ad['ground_size'],
				'condition': ad['condition'],
				'constructDate': ad['construct_date'],
				'energeticCertif': ad['energetic_certif'],
				'roomNum': ad['room_num'],
				'storageRoom': ad['storage_room'],
				'swimmingPool': ad['swimming_pool'],
				'terrace': ad['terrace'],
				'orientation': ad['orientation'],
				'reducedMobility': ad['reduced_mobility'],
				'kitchen': ad['kitchen'],
				'lift': ad['lift'],
				'obtentionDate': ad['obtention_date'],
			}
			facilities = {
				'airConditioner': ad['air_conditioner'],
				'heating': ad['heating'],
				'unfurnished': ad['unfurnished'],
			}
			location = {
				'locCity': ad['loc_city'],
				'locDistrict': ad['loc_district'],
				'locFull': ad['loc_full'],
				'locNeigh': ad['loc_neigh'],
				'locStreet': ad['loc_street'],
				'locZone': ad['loc_zone'],
			}
			quantity = {
				'm2Real': ad['m2_real'],
				'm2Useful': ad['m2_useful'],
			}

			output.append({
				'adDescription': ad['ad_description'],
				'adLastUpdate': ad['ad_last_update'],
				'houseId': ad['house_id'],
				'price': ad['price'],
				'houseInformation': house_information,
				'facilities': facilities,
				'location': location,
				'quantity': quantity
			})

		output_json = json.dumps(output)
	with open('temp.json', 'w') as outfile:
		outfile.write(output_json)


def save_json():
	file_path = os.path.join(os.path.dirname(os.getcwd()), "java\\assets\\houses.json")
	df = pd.read_json('temp.json')
	df.to_json(file_path, orient='records', lines=True)


def clean():
	if os.path.exists('temp.json'):
		os.remove('temp.json')
	if os.path.exists('temp.csv'):
		os.remove('temp.csv')


if __name__ == '__main__':
	prepare_prod_csv()
	create_nesting()
	save_json()
	clean()
