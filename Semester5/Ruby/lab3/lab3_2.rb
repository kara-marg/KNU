print("Enter x  ")
x = gets.chop().to_f
y = 0.0
if (x >= -4 && x <= 0) then
  y = (((x - 2).abs / (x ** 2 * Math.cos(x))) ** (1.0 / 7))
else
  if (x > 0 && x <= 12) then
    y = (1 / (Math.tan(x + 1 / Math.exp(x)) / (Math.sin(x) ** 2)) ** (2.0 / 7))
  else
    if (x < -4 || x > 12) then
      y = (1 / (1 + (x / (1 + (x / (1 + x))))))
    end
  end
end
print y,"\n"
    case x
    when -4..0 then print(((x - 2).abs / (x ** 2 * Math.cos(x))) ** (1.0 / 7))
    when 1..12 then print(1 / (Math.tan(x + 1 / Math.exp(x)) / (Math.sin(x) ** 2)) ** (2.0 / 7))
    else y = (1 / (1 + (x / (1 + (x / (1 + x))))))
    end
print y