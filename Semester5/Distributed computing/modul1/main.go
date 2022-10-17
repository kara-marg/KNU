package main

import (
	"fmt"
)

type Customer struct {
	name         string
	currentQueue int
}

func queue(queue1 chan Customer, queue2 chan Customer, currentQueue int, exit chan bool) {

	for len(queue1) > 0 || len(queue2) > 0 {
		customer := <-queue1
		fmt.Println(customer.name, "in", currentQueue, "queue")
		fmt.Println(currentQueue, "queue has", len(queue1), "customers")
		if (len(queue1) - len(queue2)) > 1 {
			customer := <-queue1
			queue2 <- customer
			fmt.Println(customer.name, "left", currentQueue, "queue")
		}
	}
	exit <- true
}

func main() {
	customers := []Customer{
		Customer{name: "Customer1", currentQueue: 1},
		Customer{name: "Customer2", currentQueue: 1},
		Customer{name: "Customer3", currentQueue: 1},
		Customer{name: "Customer4", currentQueue: 2},
		Customer{name: "Customer5", currentQueue: 2}}

	queue1 := make(chan Customer, 6)
	queue2 := make(chan Customer, 6)
	exit := make(chan bool, 1)

	for i := 0; i <= len(customers)/2; i++ {
		queue1 <- customers[i]
	}

	for i := (len(customers) / 2) + 1; i < len(customers); i++ {
		queue2 <- customers[i]
	}

	go queue(queue1, queue2, 1, exit)
	queue(queue2, queue1, 2, exit)
	<-exit
}
