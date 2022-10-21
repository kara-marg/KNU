class Phone

  def initialize(id, lastName, name, fathersName, address, cardNumber, debit, credit, timeCity, timeCountry)
    @id, @lastName, @name, @fathersName,@address, @cardNumber, @debit, @credit, @timeCity, @timeCountry = id, lastName, name, fathersName, address, cardNumber, debit, credit, timeCity, timeCountry
  end

  attr_accessor :id, :lastName, :name, :fathersName, :address,
                :cardNumber, :debit, :credit, :timeCity, :timeCountry

  def to_s
    @id.to_s + " " + @lastName.to_s + " " + @name.to_s + " " + @fathersName.to_s + " " + @debit.to_s + " " + @credit.to_s + " " + @timeCity.to_s + " " + @timeCountry.to_s+"\n"
  end

end

class Phones
  @@phones = Array.new

  def initialize(setNewPhones)
    setNewPhones.each { |s| @@phones << s }
  end

  def taskA(time)
    @@phones.select { |p| p.timeCity > time }.each { |p| print p.to_s }
  end

  def taskB()
    @@phones.select { |p| p.timeCountry.to_i > 0 }.each { |p| print p.to_s }
  end

  def taskC()
    @@phones.sort_by{|p| [p.lastName, p.name, p.fathersName]}.each { |p| print p.to_s }
  end
end


listOfPhones = Phones.new([
                            Phone.new(1, "Margaryan", "Karina", "W", "Melitopol", 1234, 12, 0, 10,100),
                            Phone.new(2, "Anup", "Wil", "S", "Kyiv", 1234, 121, 10, 34,5),
                            Phone.new(3, "Volkan", "Kate", "A", "Melitopol", 1234, 12, 0, 50,0),
                            Phone.new(4, "Anup", "Lilit", "K", "Kyiv", 1234, 12, 0, 34,0),
                            Phone.new(5, "Santa", "Klaus", "L", "Melitopol", 1234, 12, 0, 100,0),
                            Phone.new(6, "Qwerty", "Mark", "Bob", "Kyiv", 1234, 12, 0, 33,0),
                          ])
print "taskA\n"
listOfPhones.taskA(35)
print "taskB\n"
listOfPhones.taskB
print "taskC\n"
listOfPhones.taskC
