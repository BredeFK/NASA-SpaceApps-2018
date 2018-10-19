package main

import (
	"github.com/JohanAanesen/NASA_SpaceApps_2018/web/gofiles"
	"net/http"
	"os"
)

func main() {

	http.HandleFunc("/", gofiles.HandleMain)
	http.HandleFunc("/api", gofiles.HandleAPI)

	port := os.Getenv("PORT")
	http.ListenAndServe(":"+port, nil)
	//http.ListenAndServe(":8080", nil)
}