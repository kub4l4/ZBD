print('Start #################################################################');

db = db.getSiblingDB('HouseMongo');
db.createUser({
	user: "ProjectAdmin",
	pwd: "Qwerty123!",
	roles: [
		{ role: 'root', db: 'admin' },
		{ role: "readWrite", db: "test" }
	]
});

print('END #################################################################');
