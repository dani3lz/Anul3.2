Vari = input('Setati testcase:');
if Vari == 1
    Ts=0.01;T=10; 
else if Vari == 2      
    Ts=0.05;T=15;
    else if Vari == 3  
    Ts=0.1;T=20;
        else fprintf('ceva')
        end
     end
end

t=0:Ts:T; 
y=2*cos(2*pi*t)+sin(3*pi*t)+3*cos(4*pi*t);
figure
 subplot(211); plot(t,y); grid
df=1/T; Fm=1/Ts; len=length(t); 
f=-Fm/2:df:Fm/2;
x=fft(y)/len;
xs=fftshift(x);
A=abs(xs);
s1=len/2-50; s2=len/2+50;
subplot(212); stem(f(s1:s2), A(s1:s2));
grid; 
xlabel('frecventa(Hz)'); 
ylabel('Modulul')