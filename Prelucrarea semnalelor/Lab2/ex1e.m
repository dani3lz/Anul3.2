n=-10:0.002:10;
u=[zeros(1,2501),ones(1,7500)];
plot(n,u);
xlabel('T,S');
ylabel('Amplitudinea');
title('Treapta unitara');
axis([-10 10 0 10]);