z=[0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];
n=0:20;
subplot(3,3,1);
stem(n,z);
m=-5:15;
subplot(3,3,2);
stem(m,z);
a=0:20;
t=abs(10-a);
subplot(3,3,3);
stem(a,t);

n1=-15:25;
x1=sin(pi/17*n1);
subplot(3,3,4);

n2=0:50;
x2=cos(pi/sqrt(23)*n2);
plot(n1,x1,n2,x2);
subplot(3,3,5);
plot(n1,x1);
subplot(3,3,8);
plot(n2,x2);
subplot(3,3,9);
stem(n2,x2);