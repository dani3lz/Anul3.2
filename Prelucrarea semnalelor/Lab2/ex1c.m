n=300:350;
y=zeros(size(n-333));
y(12)=1.5;
figure
stem(n,y);
xlabel('N');
ylabel('Y(N)');