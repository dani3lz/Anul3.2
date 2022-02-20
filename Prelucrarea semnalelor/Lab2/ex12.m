t=-10:0.1:10;
x=exp((-0.1+1j*0.3)*t);
plot(t,real(x));
hold
plot(t,imag(x));
grid