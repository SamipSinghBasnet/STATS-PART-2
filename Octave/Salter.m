% Salter.m
function Salter()
    data = loadData('function_data.csv');
    x = data(:,1);
    y = data(:,2);

    salted = y + (rand(size(y)) * 2 - 1) * 1.0;  % range = 1.0
    saveData([x salted], 'salted_data1.csv');     % Changed to salted_data1.csv
    printf("Data salted and saved to salted_data1.csv\n");

    salted = y + (rand(size(y)) * 2 - 1) * 2.0;  % range = 2.0
    saveData([x salted], 'salted_data2.csv');
    printf("Data salted and saved to salted_data2.csv\n");
    salted = y + (rand(size(y)) * 2 - 1) * 3.0;  % range = 3.0
    saveData([x salted], 'salted_data3.csv');
    printf("Data salted and saved to salted_data3.csv\n");
end

function data = loadData(filename)
    data = dlmread(filename, ',', 1, 0);  % Skip header row
end

function saveData(data, filename)
    fid = fopen(filename, 'w');
    fprintf(fid, 'x,y\n');
    fprintf(fid, '%f,%f\n', data');
    fclose(fid);
end
