t=0:0.1:256;
f=1/50;
faza=pi/3;
A=1;
omega0=2*pi*f;
x=A*cos(omega0*t-faza);
plot(t,x);
axis([0 256 -1 1 ])
grid;