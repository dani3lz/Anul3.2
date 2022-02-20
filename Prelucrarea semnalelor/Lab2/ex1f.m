n=-5:0.002:20;
u=[zeros(1,2501),ones(1,10000)];
plot(n-7,u);
xlabel('T,S');
ylabel('Amplitudinea');
title('Treapta unitara');
axis([-10 8 -4 8]);