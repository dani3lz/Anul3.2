function ex17a(x,y)
df=diff(y)./diff(x);
p=[-2 0 2.5];
for i=1:length(p)
    disp(['f''(' num2str(p(i)) ') = ' num2str(df(x==p(i)))]);
end
end

