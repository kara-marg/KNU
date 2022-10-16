def factorial(n)
  res = 1
  i=2
  while i < n
    res*=i
    i+=1
  end
  return res
end

def f(x, i)
  return ((-1)**i) * (x**(2*i))/(factorial(2*i))
end

def infinite(x)

  e = 0.0001
  i = 0
  res = 0
  step=1
  while (step.abs>e)
    step=f(x, i)
    res+=step
    i+=1

  end
  return res
end

def Series(x, n)
  res = 0.to_f
  for i in 0..n
    res += f(x, i)
  end
  return res
end

x=0.8
print infinite(x),"\n"
print Series(x, 40),"\n"
print Series(x, 58),"\n"

