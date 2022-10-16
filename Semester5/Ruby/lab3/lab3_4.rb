def factorial(n)
  res = 1
  i=2
  while i < n
    res*=i
    i+=1
  end
  return res
end

#1

def sum1(value,n)
  e = 0.00001
  result=0.0
  while(value.abs> e)
    value=((factorial(n-1)*1.0)/(factorial(n+1)))**(n*(n+1))
    result+=value
    n+=1
  end

  return result
end
print("3.4_1 = ",sum1(1,2))
print("\n")
#2
print("cos(x)= ",Math.cos(1))
print("\n")
def sum2(value,n)
  x = 1
  e = 0.00001
  number = value + (((-1.0)**n)*((x**(2.0*n))/(factorial(2.0*n))))
  result = number
  if((number - value).abs > 0.00001)
    result = sum2(number,n+1)
  end
  return result
end
print("3.4_2 = ",sum2(1,1))
print("\n")
#3
def sum3(value,n)
  number = value + ((factorial(4.0*n-1.0)/((factorial(4.0*n-1.0))*(3.0**(2.0*n))*(factorial(2.0*n)))))
  result = number
  if((number - value).abs > 0.00001)
    result = sum3(number,n+1)
  end

  return result
end
print("3.4_3 = ",sum3(0,1))