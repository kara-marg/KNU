#2.1
x = [514, 515, 506, 492, 386, 314, 283, 205, 134, 78, 68, 67, 82, 90, 92,
     94, 107, 155, 199, 224, 245, 279, 306, 342, 365, 397, 443, 468, 488]
y = [19, 56, 107, 154, 186, 185, 234, 233, 220, 202, 179, 148, 118, 94,
     59, 42, 29, 32, 60, 51, 37, 31, 32, 28, 22, 16, 26, 39, 32]

v = 0.0

for i in 0..(x.length - 2)
  v += (x[i] + x[i + 1]) * (y[i] - y[i + 1])
end
v += (x[0] + x[x.length-1]) * (y[0] - y[y.length-1])
v = (v.abs) / 2
puts "v = #{v}"

#2.2
P = 4.to_f
r = 8.to_f
t = 16.to_f
puts ((P**r)*(1-P**(-t)))

#2.3
y = "1111100011001"
x = Integer("0b"+y)
puts "x = #{x}"

#2.4
puts "x = #{332.to_s(2)}"
