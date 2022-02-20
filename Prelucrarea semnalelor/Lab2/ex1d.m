n=-10:0;
y=zeros(size(n+7));
y(13)=4.9;
figure
stem(n,y);
xlabel('N');
ylabel('Y(N)');