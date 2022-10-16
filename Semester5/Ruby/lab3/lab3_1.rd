a = true; b = false; c = true;
x = 22; y = 30; z = 50;

#a
puts (!(a || b) && (a && !b))
#b
puts((z.object_id != y.object_id).object_id <= (6 >= y).object_id && a || b && c && x.object_id >= 1.5.object_id)
#c
puts((8 - x * 2 <= z) && (x ** 2 <= y ** 2) || (z >= 15))
#d
puts((x > 0 && y < 0 || z).object_id >= ((x * y + (-y / x)) + (-z) / 3).object_id)
#e
puts(!(a || b && !(c || (!a || b))))
#p
puts((x ** 2 + y ** 2 >= 1) && (x >= 0) && (y >= 0))
#g
puts((a && ((c && b) != (b || a)) || c) && b)

#2.1
puts
n = 1; p = true;
puts ((Math.exp(n * Math.log(2)) < Math.sqrt(n)) || !(p || !p))
