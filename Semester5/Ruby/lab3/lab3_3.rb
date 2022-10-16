#1
sum = 1; x = 2
for i in 1..10 do
  sum += ((-1) ** i) * (i + 1.0) / (i + 2.0) * (x ** i)
end
print(sum)
print("\n")
#2
result = 1
for i in 1..8
  result += 1.0 / (3 ** i)
end
print(result)
