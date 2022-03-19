Vari = input('Setati testcase:');
if Vari == 1
    Ts=0.01;
    T=5;
    A=0.45;
    w=0.6;
    %500
else if Vari == 2  
    Ts=0.05;
    T=15;
    A=0.25;
    w=0.4;
    %300
    else if Vari == 3    
    Ts=0.1;
    T=10;
    A=0.15;
    w=0.2;
    %100
        else fprintf('ceva')
        end
     end
end

N=T/Ts;t=-T/2:Ts:T/2;
y=A*rectpuls(t,w);
figure
subplot(311); plot(t,y); grid;
title('Impuls unitar dreptunghiular');
xlabel('Timpul,sec.');
%Aplicarea procedurii fft
x=fft(y)/N; df=1/T; Fm=1/Ts;
a=abs(x);f=0:df:Fm;
subplot(312); plot(f,a);grid;
title('Functia de densitate spectrala (procedura fft)');
xlabel('Frecventa,Hz');
ylabel('Modulul')
%Aplicarea procedurii fftshift
xp=fftshift(x);
a=abs(xp);f1=-Fm/2:df:Fm/2;
subplot(313);plot(f1,a),grid;
title('Functia de densitate spectrala (procedura fftshift)');
xlabel('Frecventa,Hz');
ylabel('Modulul')
