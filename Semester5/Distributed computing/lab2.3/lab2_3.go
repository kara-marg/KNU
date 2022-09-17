package main

import (
	"fmt"
)

func main() {
	tsi := []int{3, 11, 23, 69, 1, 24, 5, 10}
	for i := 0; i < len(tsi); i++ {
		fmt.Print(tsi[i], " ")
	}
	fmt.Println()
	var chanWithRes = make(chan int, 1)
	battle(0, len(tsi)-1, tsi, chanWithRes)

	var winnerIndex = <-chanWithRes
	if winnerIndex%2 == 0 {
		fmt.Print("Index of winner ", winnerIndex, ", his thi ", tsi[winnerIndex], " his school Huan-In")
	} else {
		fmt.Print("Index of winner ", winnerIndex, ", his thi ", tsi[winnerIndex], " his school Huan-Yan")
	}
}

func battle(firstIndex, lastIndex int, tsy []int, result chan<- int) {
	if (lastIndex - firstIndex) >= 2 {
		midl := (firstIndex + lastIndex) / 2
		var channel2 = make(chan int, 2)
		go battle(firstIndex, midl, tsy, channel2)
		battle(midl+1, lastIndex, tsy, channel2)
		firstIndex = <-channel2
		lastIndex = <-channel2
	}

	if tsy[firstIndex] < tsy[lastIndex] {
		fmt.Println("res", lastIndex)
		result <- lastIndex
	} else {
		fmt.Println("res", firstIndex)
		result <- firstIndex
	}
}
