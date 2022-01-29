c=[1 13 -7 1 -1];
dc=polyder(c);
p=[-2 -1.3 0.1 2.45];
del=polyval(dc,p);
fprintf('Derivata:\n')
fprintf('%d\n',del);
fprintf('\n\n\n')

% Grafic
x=-2:0.1:3;
df=polyval(dc,x);
plot(x,df)