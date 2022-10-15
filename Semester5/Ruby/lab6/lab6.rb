class Pupil
  attr_reader :name,
              :institution,
              :isEntered
  public
  def initialize(name, institution, isEntered)
    @name, @institution, @isEntered=name.to_s, institution.to_s, isEntered
  end
end

class Pupils
  @@pupils=Array.new
  def initialize(setNewPupils)
    setNewPupils.each{|s| @@pupils << s}
  end
  def numberOfPupilsWhoWantedToSchool
    return @@pupils.select { |p| p.institution=="school" }.count()
  end
  def numberOfPupilsWhoWantedToTechnicalOrBursa
    return @@pupils.select { |p| p.institution=="technical" || p.institution=="bursa"}.count()
  end
  def numberOfPupilsWhoEnteredToTechnicalOrBursa
    return @@pupils.select { |p| (p.institution=="technical" || p.institution=="bursa")&&p.isEntered==true}.count()
  end
  def numberOfPupilsWhoEnteredToSchool
    return @@pupils.select { |p| p.institution=="school" &&p.isEntered==true}.count()
  end
  def howManyClassesCreate
    numberOfEnteredToSchoolPupils=numberOfPupilsWhoEnteredToSchool
    classes = Array.new(numberOfEnteredToSchoolPupils/15, 15)
    numberOfEnteredToSchoolPupils%=15
    if classes.size==0
      print "not enough pupils for new 10th grade class"
      return;
    end
    for i in 0..numberOfEnteredToSchoolPupils do
      classes[i]+=1
    end
    classes.each { |t| print t, " "}
    print " "
  end
end

listOfPupils = Pupils.new([
                            Pupil.new("Kris", "school", true),
                            Pupil.new("Kara", "college", true),
                            Pupil.new("Mane", "school", true),
                            Pupil.new("Laura", "school", false),
                            Pupil.new("Mark", "technical", false)
                          ])
print listOfPupils.numberOfPupilsWhoWantedToSchool
print"\n\n"
print listOfPupils.numberOfPupilsWhoWantedToTechnicalOrBursa
print"\n\n"
print listOfPupils.numberOfPupilsWhoEnteredToTechnicalOrBursa
print"\n\n"
print listOfPupils.numberOfPupilsWhoEnteredToSchool
print"\n\n"
listOfPupils.howManyClassesCreate
