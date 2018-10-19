package main

import (
	"github.com/JohanAanesen/NASA_SpaceApps_2018/gofiles"
	"net/http"
	"os"
)

func main() {

	http.HandleFunc("/", )
	//	http.HandleFunc("/addlatest", HandleNew)

	port := os.Getenv("PORT")
	http.ListenAndServe(":"+port, nil)
	//	http.ListenAndServe(":8080", nil)
}