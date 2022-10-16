A = Array.new(14){rand(-10..10)}
C = Array.new(14){rand(-10..10)}
B = Array.new

for x in A do
  for y in C do
    if(x==y)
      B.append(x)
    end
  end
end
print A, "\n",C , "\n", B
