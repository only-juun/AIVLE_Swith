
it = 1;
annotation = sprintf('%s', 'backlay');

while(it <= 100) 

    %ARR_OUT = strings([4900,1]);
    
    strt = sprintf('%s%s%d%s', 'C:\MyPaperWork_YH\src\matlab_converter\20230620_KT\20230620_data\20230620_data\2306_backlay_yr\2306_backlay_yr\annotation\annotation_', annotation, it,'.csv');
    fid = fopen(strt,'wt');
    k = 1;
    while(k < 4901)
        fprintf(fid,'%s\n',annotation);
        
        k = k + 1;
    end
    
    it = it + 1;
    fclose(fid);
end


