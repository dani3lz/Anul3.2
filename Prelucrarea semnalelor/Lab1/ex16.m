x=1:100;
y=2:2:200;
stem(x,y);

% --------------

figure
plot(x,y)

% --------------

figure
plot(x,y,'*-r')
hold on
stem(x,y,'b')

% --------------

figure
subplot(2,1,1)
plot(x,y)
subplot(2,1,2)
stem(x,y)