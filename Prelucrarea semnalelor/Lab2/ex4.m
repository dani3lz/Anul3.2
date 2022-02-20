t=0:0.01:2;
d=[0:0.5:2];
y=-2*pulstran(t,d,'tripuls',0.5)+1;
plot(t,y),grid
axis([0 2 -1 1])