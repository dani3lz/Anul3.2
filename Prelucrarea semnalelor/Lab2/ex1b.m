n=1:20;
y=zeros(size(n-5));
y(9)=0.9;
figure
stem(n,y);
xlabel('N');
ylabel('Y(N)');