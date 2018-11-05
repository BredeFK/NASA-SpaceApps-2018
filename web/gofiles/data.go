package gofiles

type Launches struct{
	Launch 		[]Launch 	`json:"launches" bson:"launches"`
}

type Launch struct {
	Id 			int 		`json:"id" bson:"id"`
	Name 		string 		`json:"name" bson:"name"`
	Location 	Location 	`json:"location" bson:"location"`
	Net			string		`json:"net" bson:"net"`
	Vidurls		[]string 	`json:"vidURLs" bson:"vidURLs"`
	Rocket		Rocket		`json:"rocket" bson:"rocket"`
	Missions	[]Missions	`json:"missions" bson:"missions"`
	Lsp			Lsp			`json:"lsp" bson:"lsp"`
}

type Rocket struct {
	Name 		string		`json:"name" bson:"name"`
	Familyname 	string		`json:"familyname" bson:"familyname"`
	Wikiurl		string		`json:"wikiurl" bson:"wikiurl"`
	Imageurl	string		`json:"imageurl" bson:"imageurl"`
}

type Missions struct {
	Name 		string		`json:"name" bson:"name"`
	Description string		`json:"description" bson:"description"`
	Wikiurl		string		`json:"wikiurl" bson:"wikiurl"`
	Agencies 	[]Agencies 	`json:"agencies" bson:"agencies"`
}

type Location struct {
	Name 		string 		`json:"name" bson:"name"`
	Countrycode string		`json:"countrycode" bson:"countrycode"`
	Pads 		[]Pads 		`json:"pads" bson:"pads"`
}

type Pads struct {
	Name 		string 		`json:"name" bson:"name"`
	Wikiurl		string		`json:"wikiurl" bson:"wikiurl"`
}

type Lsp struct {
	Name		string		`json:"name" bson:"name"`
	Wikiurl		string		`json:"wikiurl" bson:"wikiurl"`
}

type Agencies struct {
	Name 		string		`json:"name" bson:"name"`
	Wikiurl		string		`json:"wikiURL" bson:"wikiURL"`
}
