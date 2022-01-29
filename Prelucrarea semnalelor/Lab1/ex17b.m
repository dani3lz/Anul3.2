function ex17b(x,y)
df=diff(y)./diff(x);
p=[-1.5 0 3];
for i=1:length(p)
    k=find(x==p(i));
    disp(['f''(' num2str(p(i)) ') = ' num2str(df(k-1))]);
end
end

