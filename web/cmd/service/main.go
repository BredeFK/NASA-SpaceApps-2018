package main

import (
	"github.com/JohanAanesen/NASA_SpaceApps_2018/web/gofiles"
	"os"
)

func main() {
	gofiles.UpdateDatabase()
	os.Exit(0)
}
