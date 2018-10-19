package gofiles

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
)

func HandleMain(w http.ResponseWriter, r *http.Request){
	fmt.Fprint(w, "Fuck off m8")
}

func HandleAPI(w http.ResponseWriter, r *http.Request){
	resp, err := http.Get("https://launchlibrary.net/1.4/launch/next/50?fields=name,id,location,net,pads,rocket,missions,lsp")
	if err != nil {
		log.Fatal(err)
	}

	var data Launches

	err = json.NewDecoder(resp.Body).Decode(&data)
	if err != nil {
		fmt.Printf("Error with json decoder: %s", err)
	}

	http.Header.Add(w.Header(), "content-type", "application/json")

	json.NewEncoder(w).Encode(data)

}