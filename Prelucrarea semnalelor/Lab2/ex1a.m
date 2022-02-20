n=-15:15;
y=zeros(size(n));
y(5)=0.8;
figure
stem(n,y);
xlabel('N');
ylabel('Y(N)');