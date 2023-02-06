
1) Run python project to create houses.json

2) Create mongoDB user:
```
use HomeMongo
db.createUser({
  user: "kamil1",
  pwd: "Qwerty123!",
  roles: [
    { role: 'root', db: 'admin' },
    { role: "readWrite", db: "test" }
  ]
});
```
3) Update path in HomeService getJSON() (Java)
