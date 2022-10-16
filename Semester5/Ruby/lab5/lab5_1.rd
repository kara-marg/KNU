
def prm (a, b, f, n)
  res = 0.0
  h=(b-a)/n.to_f
  for i in (a..b).step(h)
    res+=method(f).call((i-(h/2)))
  end
  return res*h
end

def trp (a, b, f, n)
  res = (method(f).call((a))+method(f).call((b)))/2
  h=(b-a)/n.to_f
  for i in ((a+h)..(b-h)).step(h)
    res+=method(f).call((i))
  end
  return res*h
end

def f1(x)
  return x*x*x*Math.exp(2*x)
end
def f2(x)
  return Math.tan(5.0/x +( 3.1415/4))**(2.5)
end
n = 10000

print prm(0, 0.8, :f1, n),"\n"
print trp(0, 0.8, :f1, n), "\n"
print prm(0.0, 3.1415/8, :f2, n), "\n"
print trp(0.0, 3.1415/8, :f2, n),"\n"
