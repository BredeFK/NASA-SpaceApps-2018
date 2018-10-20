package gofiles

import (
	"fmt"
	"gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"
	"os"
)

var dbURL = os.Getenv("MONGODB")

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

	err := c.Find(nil).Sort("_id").All(&data)
	if err != nil{
		fmt.Printf("Something went to sheit: %s", err)
	}

	launches.Launch = data

	return launches
}

func DeleteLaunchesFromDB(){
	db := DatabaseCon()
	defer db.Close()
	c := db.DB("nasaspaceapps2018").C("launches")
	_, err := c.RemoveAll(nil)
	if err != nil{
		fmt.Println("something went to sheit " + err.Error())
	}

}

func getLaunchFromDB(id int) (Launch, error){
	db := DatabaseCon()
	defer db.Close()
	c := db.DB("nasaspaceapps2018").C("launches")

	var data Launch

	err := c.Find(bson.M{"id": id}).One(&data)
	if err != nil{
		fmt.Println("something went to sheit " + err.Error())

	}

	return data, err
}

func updateLaunchFromDB(id int, launch Launch){
	db := DatabaseCon()
	defer db.Close()
	c := db.DB("nasaspaceapps2018").C("launches")

	err := c.Update(bson.M{"id": id}, bson.M{"$set": bson.M{"name": launch.Name, "location": launch.Location, "net": launch.Net, "rocket": launch.Rocket, "missions": launch.Missions}})
	if err != nil{
		fmt.Printf("Something went to sheit: %s", err)
	}else{
		fmt.Printf("Launch with id: %d has been updated.", id)
	}


}