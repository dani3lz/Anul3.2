t=-0.5:0.002:0.5;
x=2*rectpuls(t-1,2);
plot(t, x-1);
axis([-0.5 0.5 -1.5 1.5])
grid