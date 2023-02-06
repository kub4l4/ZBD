import pandas as pd
import glob
import os
import csv
import json


def prepare_test_csv():
	# df = pd.read_csv(r'archive\(15).csv')
	df.to_csv(r'temp.csv')


def prepare_prod_csv():
	joined_files = os.path.join("archive", "*.csv")
	joined_list = glob.glob(joined_files)
	df = pd.concat(map(pd.read_csv, joined_list), ignore_index=True)

	# df['balcony']=df['balcony'].fillna(0).astype(int)
	# df['bath_num']=df['bath_num'].fillna(0).astype(int)
	# df['built_in_wardrobe']=df['built_in_wardrobe'].fillna(0).astype(int)
	# df['chimney']=df['chimney'].fillna(0).astype(int)
	# df['air_conditioner']=df['air_conditioner'].fillna(0).astype(int)
	# df['garden']=df['garden'].fillna(0).astype(int)
	# df['ground_size']=df['ground_size'].fillna(0).astype(int)
	# df['construct_date']=df['construct_date'].fillna(0).astype(int)
	# df['lift']=df['lift'].fillna(0).astype(int)
	# df['kitchen']=df['kitchen'].fillna(0).astype(int)
	# df['swimming_pool']=df['swimming_pool'].fillna(0).astype(int)
	# df['storage_room']=df['storage_room'].fillna(0).astype(int)
	# df['terrace']=df['terrace'].fillna(0).astype(int)
	# df['storage_room']=df['storage_room'].fillna(0).astype(int)
	df.to_csv(r'temp.csv')


def create_nesting():
	output = []
	with open('temp.csv', "r", encoding="utf-8") as csv_file:
		for ad in csv.DictReader(csv_file):
			house_information = {
				'house_type': ad['house_type'],
				'balcony': ad['balcony'],
				'bath_num': ad['bath_num'],
				'built_in_wardrobe': ad['built_in_wardrobe'],
				'chimney': ad['chimney'],
				'floor': ad['floor'],
				'garage': ad['garage'],
				'garden': ad['garden'],
				'ground_size': ad['ground_size'],
				'condition': ad['condition'],
				'construct_date': ad['construct_date'],
				'energetic_certif': ad['energetic_certif'],
				'room_num': ad['room_num'],
				'storage_room': ad['storage_room'],
				'swimming_pool': ad['swimming_pool'],
				'terrace': ad['terrace'],
				'orientation': ad['orientation'],
				'reduced_mobility': ad['reduced_mobility'],
				'kitchen': ad['kitchen'],
				'lift': ad['lift'],
				'obtention_date': ad['obtention_date'],
			}
			facilities = {
				'air_conditioner': ad['air_conditioner'],
				'heating': ad['heating'],
				'unfurnished': ad['unfurnished'],
			}
			location = {
				'loc_city': ad['loc_city'],
				'loc_district': ad['loc_district'],
				'loc_full': ad['loc_full'],
				'loc_neigh': ad['loc_neigh'],
				'loc_street': ad['loc_street'],
				'loc_zone': ad['loc_zone'],
			}
			quantity = {
				'm2_real': ad['m2_real'],
				'm2_useful': ad['m2_useful'],
			}

			output.append({
				'ad_description': ad['ad_description'],
				'ad_last_update': ad['ad_last_update'],
				'house_id': ad['house_id'],
				'price': ad['price'],
				'house_information': house_information,
				'facilities': facilities,
				'location': location,
				'quantity': quantity
			})

		output_json = json.dumps(output)
	with open('json_data1.json', 'w') as outfile:
		outfile.write(output_json)


def test():
	df = pd.read_json('json_data1.json')
	df.to_json(r'houses.json', orient='records', lines=True)


if __name__ == '__main__':
	# prepare_test_csv()
	prepare_prod_csv()
	create_nesting()
	test()
