package gofiles

import (
	"fmt"
	"gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"
)

var dbURL = "mongodb://johan:johan123@ds237373.mlab.com:37373/nasaspaceapps2018"

//DatabaseCon connects to database and returns session
func DatabaseCon() *mgo.Session {
	session, err := mgo.Dial(dbURL)
	if err != nil {
		panic(err)
	}
	session.SetMode(mgo.Monotonic, true)

	return session
}

//SaveData stores data in database
func SaveData(data Launch) {
	db := DatabaseCon()
	defer db.Close()
	c := db.DB("nasaspaceapps2018").C("launches")

	err := c.Insert(data)
	if err != nil {
		fmt.Printf("something went wrong writing to mongodb: %s", err)
	}
}

func getAllFromDB() Launches{
	var data []Launch
	var launches Launches

	db := DatabaseCon()
	defer db.Close()
	c := db.DB("nasaspaceapps2018").C("launches")

	err := c.Find(nil).All(&data)
	if err != nil{
		fmt.Printf("Something went to sheit: %s", err)
	}

	launches.Launch = data

	return launches
}

func getLaunchFromDB(id int) Launch{
	db := DatabaseCon()
	defer db.Close()
	c := db.DB("nasaspaceapps2018").C("launches")

	var data Launch

	err := c.Find(bson.M{"id": id}).One(&data)
	if err != nil{
		fmt.Println("something went to sheit " + err.Error())

	}

	return data
}
