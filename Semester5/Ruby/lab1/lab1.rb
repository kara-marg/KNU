p "enter f"
 f = gets.chomp.to_f
p "enter a"
 a = gets.chomp.to_f
p "enter b"
 b = gets.chomp.to_f
p "enter x"
 x = gets.chomp.to_f

k = (x * ((f - a)**(0.25))) + (((Math.log(Math.sin(b).abs)**3) + Math.tan(x)**(1.0/3)) / (2.3 * Math.log10(x.abs)))
print "k = "+k.to_s,"\n"
