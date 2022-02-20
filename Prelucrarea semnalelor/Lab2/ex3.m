w=12.5;
duty=50;
t=0:0.001:2;
y=2*square(w*t, duty);
plot(t,y);
axis([min(t),max(t),-2.3 2.3]),grid