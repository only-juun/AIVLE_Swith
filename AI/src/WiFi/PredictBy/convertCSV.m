
it = 1; % start index for iterator
name =  sprintf('%s', "backlay");
while(it <= 100) % setting number of CSI files
    
ARR_1 = zeros(1,30);
ARR_2 = zeros(1,30);
ARR_3 = zeros(1,30);
ARR_OUT = zeros(4900,90);

k = 1;
t = 1; 
%C:\MyPaperWork_YH\src\matlab_converter\Sinario1\
% File address Setting

csvAddress = sprintf('%s%s%d', 'C:\MyPaperWork_YH\src\matlab_converter\20230620_KT\20230620_data\20230620_data\2306_backlay_yr\2306_backlay_yr\',name,it);
strt = sprintf('%s%s%d', 'C:\MyPaperWork_YH\src\matlab_converter\20230620_KT\20230620_data\20230620_data\2306_backlay_yr\2306_backlay_yr\',name,it);

csi_trace = read_bf_file(strt);
disp(strt);
while(k <= 4900)

csi_entry = csi_trace{t};
csi = get_scaled_csi(csi_entry);

A = abs(csi);

i = 1;
while(i<=30)
   
    ARR_1(i) = A(:,1,i); % 1¹øÂ° 
    ARR_2(i) = A(:,2,i);
    ARR_3(i) = A(:,3,i);
    i = i + 1;
    
end

ARR_FINAL = [ARR_1,ARR_2,ARR_3];
ARR_OUT(k,:) = ARR_FINAL;

k = k + 1;
t = t + 1;
end

% Convert .dat to .csv
string = sprintf('%s%s', csvAddress, '.csv');
csvwrite(string , ARR_OUT);
it = it + 1;

end


