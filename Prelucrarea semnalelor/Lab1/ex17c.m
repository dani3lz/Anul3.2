function ex17c(x,y)
dx=diff(x);
dy=diff(y);
k=length(dx);
df=(dy(1:k-1)+dy(2:k))./(dx(1:k-1)+dx(2:k));
n=length(x);
plot(x(2:n-1),df)
end

