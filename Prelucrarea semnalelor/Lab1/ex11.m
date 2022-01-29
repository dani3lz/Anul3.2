a=0:0.1:2;
b=ones(21,1);
x = a*b;
y = b*a;
fprintf('a*b:\n');
fprintf('%d\n',x);
fprintf('\n\n\n');
%fprintf('b*a:\n');
%fprintf('%d\n',y);
%fprintf('\n\n\n');
fprintf('whos:\n');
whos