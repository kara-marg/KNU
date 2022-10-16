require 'matrix'



def task(m, v, n)
  result = Array.new(n){Vector.zero(n+1)}
  (0..n - 1).each do |i|
    (0..n - 1).each do |j|
      result[i][j] = m[i][j]
    end
  end
  (0..n - 1).each do |j|
    result[j][n]=v[j].to_f
  end
  visit = Array.new(n, false)
  (0..n - 1).each do |i|
    (0..n - 1).each do |j|
      if result[j][i]!=0 && !visit[j]
        divider=result[j][i]
        result[j]=result[j]/divider
        (0..n - 1).each do |k|
          if k!=j
            result[k]-=(result[j]*result[k][i])
          end
        end
        visit[j]=true
        break
      end
    end
  end
  return result
end

def printMatrix(m)
  (0..m.size() - 1).each do |i|
    print m[i],"\n"
  end
end

n=8
a = Array.new(n) { Array.new(n) }
b = Vector.zero(n)
(0..n - 1).each do |i|
  (0..n - 1).each do |j|
    a[i][j] = (rand 10).to_f

  end
  b[i] = (rand 10).to_f
end

(0..a.size() - 1).each do |i|
  print a[i],"\n"
end
print b,"\n"
res = task(a, b, n)
print "\n"
(0..res.size() - 1).each do |i|
  print res[i],"\n"
end
