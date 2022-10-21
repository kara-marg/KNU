def F(x, a, b, c)
  if x > 5 && c == 0
    return (x.to_f - a.to_f) / x.to_f
  end
  if x < 5 && c != 0
    return (-1 * a * x * x) - b
  end
  return -1.0 * x.to_f / c.to_f
end



print "enter a, b, c, xBegin, xEnd, dx by enter\n"
a = gets.chomp.to_f
b = gets.chomp.to_f
c = gets.chomp.to_f
xBegin = gets.chomp.to_f
xEnd = gets.chomp.to_f
dx = gets.chomp.to_f

if !(((a.truncate || b.truncate) + (a.truncate || c.truncate)) % 2)
  a = a.truncate
  b = b.truncate
  c = c.truncate
  xBegin = xBegin.truncate
  xEnd = xEnd.truncate
  dx = dx.truncate
end

for i in (xBegin..xEnd).step(dx)

  print i, " ", F(i, a, b, c), "\n"
end
