t=-10:0.1:10;
omega=pi/4;
duty=30;
x=square(omega*t, duty);
plot(t,x);
axis([-10 10 -1.2 1.2 ]);
grid