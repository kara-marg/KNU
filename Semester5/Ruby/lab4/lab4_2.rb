
require 'matrix'

def outerProduct(a, b)
  result = Vector.zero(3)
  result[0]=a[2]*b[2]-a[2]*b[1]
  result[1]=a[2]*b[0]-a[0]*b[2]
  result[2]=a[0]*b[1]-a[1]*b[0]
  return result
end

def createVector(n)
  v = Vector.zero(n)
  (0..n - 1).each do |i|
    v[i]=rand 10
  end
  return v
end

a=createVector(3)
b=createVector(3)
res=outerProduct(a, b)
print a,"\n",b,"\n",res
