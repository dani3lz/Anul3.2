t=-10:0.1:10;
x=exp((-0.1+1j*0.3)*t);
figure
subplot(2,1,1);
plot(t,real(x));
grid

subplot(2,1,2);
plot(t,imag(x));
grid