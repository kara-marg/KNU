require 'date'

class Singer
  def initialize(name, age)
    @name = name
    @age = age
  end
  attr_accessor :name, :age
end

class Location

  def initialize(name_location, address, seats_count)
    @name_location = name_location
    @address = address
    @seats_count = seats_count
  end

  attr_accessor :name_location, :name, :address
end

class Concert
  def initialize(date, genre, location, ticket_price, singer)
    @date = date
    @genre = genre
    @location = location
    @ticket_price = ticket_price
    @singer = singer
    @hall = [0]*@location.name_location
  end
  attr_accessor :date, :genre, :location, :ticket_price, :artists, :hall, :singer

  def book(site)
    if site > @location.name_location or @hall[site] == 1
      puts "You cant book! Seat is engaged or wrong number. Count of seats = " + @location.name_location.to_s
    else
      @hall[site] = 1
    end
  end
end

class ConcertManager
  def initialize(concerts)
    @concerts = concerts
  end

  def sort_by_genre
    @concerts.sort_by {|concert| concert.genre}
  end

  def sort_by_date
    @concerts.sort_by {|concert| concert.date}
  end

  def sort_by_price
    @concerts.sort_by {|concert| concert.ticket_price}
  end

  def sort_by_location
    @concerts.sort_by {|concert| concert.location}
  end

  def get_by_genre(genre)
    res = []
    @concerts.each {|concert| concert.genre == genre ? res.push(concert) : ''}
    res
  end

  def get_by_date(date)
    res = []
    @concerts.each {|concert| concert.date == date ? res.push(concert) : ''}
    res
  end

  def get_by_price(price)
    res = []
    @concerts.each {|concert| concert.ticket_price == price ? res.push(concert) : ''}
    res
  end

  def get_by_singer(singer)
    res = []
    @concerts.each {|concert| concert.singer.name == singer.name ? res.push(concert) : ''}
    res
  end

  def get_by_location(location)
    res = []
    @concerts.each {|concert| concert.location.name == location.name ? res.push(concert) : ''}
    res
  end
end

michael_jackson = Singer.new("Michael Jackson",30)
elvis_presley = Singer.new("Elvis Presley", 67)
madonna = Singer.new("Madonna", 56)

albert_hall = Location.new("Albert Hall", "London", 1000)
sydney_opera_house = Location.new("Sydney Opera House", "Sydney", 3000)
grand_opera = Location.new("Grand Opéra", "Paris", 2200)

concerts = [7]
concerts[0] = Concert.new(Date.new(2029,01,06), "rock", albert_hall, 5000, michael_jackson)
concerts[1] = Concert.new(Date.new(2028,08,04), "Rock and roll", sydney_opera_house, 3500, elvis_presley)
concerts[2] = Concert.new(Date.new(3022,03,25), "rock", sydney_opera_house, 5000, michael_jackson)
concerts[3] = Concert.new(Date.new(2092,04,15), "pop", grand_opera, 2900, madonna)
concerts[4] = Concert.new(Date.new(2052,09,22), "pop", grand_opera, 2900, madonna)
concerts[5] = Concert.new(Date.new(2027,07,05), "Rock and roll", albert_hall, 3500, elvis_presley)
concerts[6] = Concert.new(Date.new(2028,03,11), "rock", grand_opera, 5000, michael_jackson)

cm = ConcertManager.new(concerts)

puts cm.get_by_singer(michael_jackson)
