function [out1,out2,out3] = ex13b(r)
z = real(r);
media = 0;
for i=z
    media = media + i;
end
out1 = media;
out2 = r.^2;
out3 = r.*(r');

fprintf('Media:\n')
fprintf('%d\n',out1)
fprintf('\n\n\n')
fprintf('Patrat:\n')
fprintf('%d\n',out2)
fprintf('\n\n\n')
fprintf('Transpusa:\n')
fprintf('%d\n',out3)
end

