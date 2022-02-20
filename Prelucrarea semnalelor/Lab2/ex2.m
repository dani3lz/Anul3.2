t=0:0.001:2;
faza=1/0.25;
A=2;
omega0=2*pi*faza;
x=A*cos(omega0*t)+5;
plot(t,x);
axis([0 2 3 7]);
grid
title('Sinusoida');
xlabel('Timpul t');
ylabel('Amplitudinea');