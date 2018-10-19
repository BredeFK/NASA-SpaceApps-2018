package gofiles

type Launches struct{
	Launch 		[]Launch 	`json:"launches"`
}


type Launch struct {
	//Id 			int 		`json:"id"`
	Name 		string 		`json:"name"`
	Location 	Location 	`json:"location"`
	Net			string		`json:"net"`
	Pads 		[]Pads 		`json:"pads"`
	Rocket		Rocket		`json:"rocket"`
	Missions	[]Missions	`json:"missions"`
}

type Rocket struct {
	Name 		string		`json:"name"`
	Familyname 	string		`json:"familyname"`
	Wikiurl		string		`json:"wikiurl"`
	Imageurl	string		`json:"imageurl"`
}

type Missions struct {
	Name 		string		`json:"name"`
	Description string		`json:"description"`
}

type Location struct {
	Name 		string 		`json:"name"`
}

type Pads struct {
	Name 		string 		`json:"name"`
	Wikiurl		string		`json:"wikiurl"`
}