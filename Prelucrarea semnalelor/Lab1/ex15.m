F=50;
t=0:0.01:0.2;
s=2*sin(2*pi*F*t);
plot(t,s,'.-'),xlabel('TIMP [s]'),grid;

% ------------------------------------

figure
t=0:0.0002:0.2;
s=2*sin(2*pi*F*t);
plot(t,s,'.-'),xlabel('TIMP [s]'),grid;

% ------------------------------------

figure
F=20;
t=0:0.0002:0.2;
s=2*sin(2*pi*F*t);
c=2*cos(2*pi*F*t);
plot(t,s,'.-b',t,c,'.-r');