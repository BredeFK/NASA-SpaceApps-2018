package gofiles

type Launches struct{
	Launch 		[]Launch 	`json:"launches"`
}


type Launch struct {
	Id 			int 		`json:"id"`
	Name 		string 		`json:"name"`
	Location 	Location 	`json:"location"`
	Net			string		`json:"net"`
	Rocket		Rocket		`json:"rocket"`
	Missions	[]Missions	`json:"missions"`
	//Lsp			Lsp			`json:"lsp"`
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
	Wikiurl		string		`json:"wikiurl"`
}

type Location struct {
	Name 		string 		`json:"name"`
	Countrycode string		`json:"countrycode"`
	Pads 		[]Pads 		`json:"pads"`
}

type Pads struct {
	Name 		string 		`json:"name"`
	Wikiurl		string		`json:"wikiurl"`
}
/*
type Lsp struct {
	Name		string		`json:"name"`
	Wikiurl		string		`json:"wikiurl"`
}*/